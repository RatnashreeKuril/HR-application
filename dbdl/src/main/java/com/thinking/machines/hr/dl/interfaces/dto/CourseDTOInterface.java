package com.thinking.machines.hr.dl.interfaces.dto;
public interface CourseDTOInterface extends Comparable<CourseDTOInterface>, java.io.Serializable
{
public void setCode(int code);
public int getCode();
public void setName(String courseName);
public String getName();
}