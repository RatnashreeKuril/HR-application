package com.thinking.machines.hr.dl.dto;
import com.thinking.machines.hr.dl.interfaces.dto.*;
public class CourseDTO implements CourseDTOInterface
{
private int code;
private String name;
public CourseDTO()
{
this.code=0;
this.name="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public int compareTo(CourseDTOInterface courseDTO)
{
return this.code-courseDTO.getCode();
}
public boolean equals(Object other)
{
if(!(other instanceof CourseDTOInterface)) return false;
CourseDTOInterface d=(CourseDTOInterface)other;
return this.code==d.getCode();
}
public int hashCode()
{
return this.code;
}
}