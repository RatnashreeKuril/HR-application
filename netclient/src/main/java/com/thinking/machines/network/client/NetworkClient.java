package com.thinking.machines.network.client;
import com.thinking.machines.network.common.*;
import com.thinking.machines.network.common.exceptions.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class NetworkClient
{
public Response send(Request request) throws NetworkException
{
try
{
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ObjectOutputStream oos=new ObjectOutputStream(baos);
oos.writeObject(request);
oos.flush();
byte []objectBytes=baos.toByteArray();
byte header[]=new byte[1024];
int requestLength=objectBytes.length;
int i=1023;
int num=requestLength;
while(num>0)
{
header[i]=(byte)(num%10);
i--;
num=num/10;
}
Socket socket=new Socket(Configuration.getHost(),Configuration.getPort());
OutputStream os=socket.getOutputStream();
os.write(header,0,1024);
os.flush();
InputStream is=socket.getInputStream();
int readCount;
byte ack[]=new byte[1];
while(true)
{
readCount=is.read(ack);
if(readCount==-1) continue;
break;
}
i=0;
int chunkSize=1024;
int bytesToSend=chunkSize;
while(i<requestLength)
{
if((requestLength-i)<chunkSize) bytesToSend=requestLength-i;
os.write(objectBytes,i,bytesToSend);
os.flush();
i=i+bytesToSend;
}

header=new byte[1024];
byte []tmp=new byte[1024];
i=0;

int f=0;
while(i<1024)
{
readCount=is.read(tmp);
if(readCount==-1) continue;
for(int k=0;k<readCount;k++)
{
header[f]=tmp[k];
f++;
}
i=i+readCount;
}
i=1023;
int responseLength=0;
int e=1;
while(i>0)
{
responseLength=responseLength+(header[i]*e);
i--;
e=e*10;
}
ack[0]=1;
os.write(ack,0,1);
os.flush();
byte responseArray[]=new byte[responseLength];
int m=0;
i=0;
while(m<responseLength)
{
readCount=is.read(tmp);
if(readCount==-1) continue;
for(int k=0;k<readCount;k++)
{
responseArray[i]=tmp[k];
i++;
}
m=m+readCount;
}
ack[0]=1;
os.write(ack,0,1);
os.flush();
socket.close();
ByteArrayInputStream bais=new ByteArrayInputStream(responseArray);
ObjectInputStream ois=new ObjectInputStream(bais);
Response response=(Response)ois.readObject();
System.out.println("Response received");
System.out.println(response.getSuccess());
if(response.hasException())
{
System.out.println("Something went wrong");
BLException blException=(BLException)response.getException();
System.out.println(blException.getException("title"));
}
System.out.println("Every thing is ok");
return response;
}catch(Exception exception)
{
throw new NetworkException(exception.getMessage());
}

}
}
