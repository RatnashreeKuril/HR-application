package com.thinking.machines.hr.server.main;
import com.thinking.machines.network.server.*;
import com.thinking.machines.network.common.*;
import com.thinking.machines.network.common.exceptions.*;
import com.thinking.machines.hr.server.*;
public class Main
{
public static void main(String gg[])
{
try
{
RequestHandler requestHandler=new RequestHandler();
NetworkServer networkServer=new NetworkServer(requestHandler);
networkServer.start();
}catch(NetworkException networkException)
{
System.out.println(networkException.getMessage());
}
}
}