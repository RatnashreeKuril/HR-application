package com.thinking.machines.hr.server;
import com.thinking.machines.network.server.*;
import com.thinking.machines.network.common.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
public class RequestHandler implements RequestHandlerInterface
{
private DesignationManagerInterface designationManager;
public RequestHandler()
{
try
{
designationManager=DesignationManager.getDesignationManager();
}catch(BLException blException)
{

}
}
public Response process(Request request)
{
String manager=request.getManager();
String action=request.getAction();
Object [] arguments=request.getArguments();
Response response=new Response();
if(manager.equals("DesignationManager"))
{
if(designationManager==null)
{

}
if(action.equals("addDesignation"))
{
DesignationInterface designation=(Designation)arguments[0];
try
{
designationManager.addDesignation(designation);
response.setSuccess(true);
response.setException(null);
response.setResult(designation);
}catch(BLException blException)
{
response.setSuccess(false);
response.setException(blException);
response.setResult(null);
return response;
}
}

if(action.equals("updateDesignation"))
{
DesignationInterface designation=(Designation)arguments[0];
try
{
designationManager.updateDesignation(designation);
response.setSuccess(true);
response.setException(null);
}catch(BLException blException)
{
response.setSuccess(false);
response.setException(blException);
return response;
}
}


if(action.equals("removeDesignation"))
{
int code=(int)arguments[0];
try
{
designationManager.removeDesignation(code);
response.setSuccess(true);
response.setException(null);
}catch(BLException blException)
{
response.setSuccess(false);
response.setException(blException);
return response;
}
}


if(action.equals("getDesignations"))
{
Object result=designationManager.getDesignations();
response.setSuccess(true);
response.setException(null);
response.setResult(result);

}


if(action.equals("getDesignationByCode"))
{
int code=(int)arguments[0];
try
{
DesignationInterface designation=designationManager.getDesignationByCode(code);
response.setSuccess(true);
response.setException(null);
response.setResult(designation);
}catch(BLException blException)
{
response.setSuccess(false);
response.setException(blException);
response.setResult(null);
return response;
}
}

if(action.equals("getDesignationByTitle"))
{
String title=(String)arguments[0];
try
{
DesignationInterface designation=designationManager.getDesignationByTitle(title);
response.setSuccess(true);
response.setException(null);
response.setResult(designation);
}catch(BLException blException)
{
response.setSuccess(false);
response.setException(blException);
response.setResult(null);
return response;
}
}

if(action.equals("getDesignationCount"))
{
int count=0;
count=designationManager.getDesignationCount();
response.setSuccess(true);
response.setException(null);
response.setResult(count);
}

if(action.equals("designationCodeExists"))
{
int code=(int)arguments[0];
boolean codeExists=false;
codeExists=designationManager.designationCodeExists(code);
response.setSuccess(true);
response.setException(null);
response.setResult(codeExists);
}

if(action.equals("designationTitleExists"))
{
String title=(String)arguments[0];
boolean titleExists=false;
titleExists=designationManager.designationTitleExists(title);
response.setSuccess(true);
response.setException(null);
response.setResult(titleExists);
}

}// DesignationManager part ends here
return response;
}
}