package com.thinking.machines.network.server;
import java.io.*;
import java.net.*;
import com.thinking.machines.network.common.*;
import com.thinking.machines.network.common.exceptions.*;
class RequestProcessor extends Thread
{
private Socket socket;
private RequestHandlerInterface requestHandler;
RequestProcessor(Socket socket, RequestHandlerInterface requestHandler)
{
this.socket=socket;
this.requestHandler=requestHandler;
start();
}
public void run()
{

try
{
InputStream is=socket.getInputStream();
OutputStream os=socket.getOutputStream();
byte header[]=new byte[1024];
byte []tmp=new byte[1024];
int x=0;
int y;
int i=0;
int readCount,j;
while(x<1024)
{
readCount=is.read(tmp);
if(readCount==-1) continue;
for(y=0;y<readCount;y++)
{
header[i]=tmp[y];
i++;
}
x=x+readCount;
}
System.out.println("Header received");
byte ack[]=new byte[1];
ack[0]=1;
os.write(ack,0,1);
os.flush();
int requestLength=0;
i=1023;
j=1;
while(i>=0)
{
requestLength=requestLength+(header[i]*j);
j=j*10;
i--;
}
x=0;
i=0;
byte requestBytes[]=new byte[requestLength];
while(x<requestLength)
{
readCount=is.read(tmp);
if(readCount==-1) continue;
for(y=0;y<readCount;y++)
{
requestBytes[i]=tmp[y];
i++;
}
x=x+readCount;
}
ByteArrayInputStream bais=new ByteArrayInputStream(requestBytes);
ObjectInputStream ois=new ObjectInputStream(bais);
Request request=(Request)ois.readObject();
Response response=requestHandler.process(request);
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ObjectOutputStream oos=new ObjectOutputStream(baos);
oos.writeObject(response);
oos.flush();
byte objectBytes[]=baos.toByteArray();
int responseLength=objectBytes.length;
header=new byte[1024];
int num=responseLength;
i=1023;
while(num>0)
{
header[i]=(byte)(num%10);
num=num/10;
i--;
}
os.write(header,0,1024);
os.flush();
while(true)
{
readCount=is.read(ack);
if(readCount==-1) continue;
break;
}
x=0;
int bytesToWrite=responseLength;
int chunkSize=1024;
while(x<bytesToWrite)
{
if((bytesToWrite-x)<chunkSize) chunkSize=bytesToWrite-x;
os.write(objectBytes,x,chunkSize);
x=x+chunkSize;
}
while(true)
{
readCount=is.read(ack);
if(readCount==-1) continue;
break;
}





}catch(Exception e)
{
System.out.println(e);
}



}
}