package com.thinking.machines.hr.bl.exceptions;
import java.util.*;
public class BLException extends Exception
{
private String genericException;
private Map<String,String> exceptions;
public BLException()
{
this.genericException=null;
this.exceptions=new HashMap<>();
}
public void setGenericException(String exception)
{
this.genericException=exception;
}
public String getGenericException()
{
if(this.genericException==null) return "";
return this.genericException;
}
public void addException(String property, String exception)
{
this.exceptions.put(property,exception);
}
public String getException(String property)
{
return this.exceptions.get(property);
}
public void removeException(String property)
{
this.exceptions.remove(property);
}
public int getExceptionsCount()
{
if(this.genericException!=null) return this.exceptions.size()+1;
return this.exceptions.size();
}
public boolean hasException(String property)
{
return this.exceptions.containsKey(property);
}
public boolean hasExceptions()
{
return this.getExceptionsCount()>0;
}
public boolean hasGenericException()
{
return this.genericException!=null;
}
public List<String> getExceptions()
{
List<String> list=new ArrayList<>();
this.exceptions.forEach((k,v)->{
list.add(k);
});
return list;
}
public String getMessage()
{
if(this.genericException==null) return "";
return this.genericException;
}
}