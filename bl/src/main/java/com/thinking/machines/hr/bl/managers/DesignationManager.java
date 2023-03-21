package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import java.util.*;
public class DesignationManager implements DesignationManagerInterface
{
private Map<Integer, DesignationInterface> codeWiseDesignationsMap;
private Map<String, DesignationInterface> titleWiseDesignationsMap;
private Set<DesignationInterface> designationsSet;
static private DesignationManagerInterface designationManager=null;
private DesignationManager() throws BLException
{
populateDataStructures();
}
static public DesignationManagerInterface getDesignationManager() throws BLException
{
if(designationManager==null) designationManager=new DesignationManager();
return designationManager;
}
private void populateDataStructures() throws BLException
{
this.codeWiseDesignationsMap=new HashMap<>();
this.titleWiseDesignationsMap=new HashMap<>();
this.designationsSet=new TreeSet<>();
Set<DesignationDTOInterface> dlDesignations;
DesignationInterface blDesignation;
DesignationDAOInterface designationDAO=new DesignationDAO();
try
{
dlDesignations=designationDAO.getAll();
for(DesignationDTOInterface dlDesignation:dlDesignations)
{
blDesignation=new Designation();
blDesignation.setCode(dlDesignation.getCode());
blDesignation.setTitle(dlDesignation.getTitle());
this.codeWiseDesignationsMap.put(blDesignation.getCode(),blDesignation);
this.titleWiseDesignationsMap.put(blDesignation.getTitle().toUpperCase(),blDesignation);
this.designationsSet.add(blDesignation);
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void addDesignation(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null)
{
blException.setGenericException("Designation required");
throw blException;
}
int code=designation.getCode();
String title=designation.getTitle();
if(code!=0) blException.addException("code","Code should be zero");
if(title==null)
{
blException.addException("title","Designation title required");
title=" ";
}
else
{
title=title.trim();
if(title.length()==0) blException.addException("title","Designation title required");
}
if(title.length()>0)
{
if(this.titleWiseDesignationsMap.containsKey(title.toUpperCase())) blException.addException("title","Designation "+title+" exists");
}
if(blException.hasExceptions()) throw blException;
try
{
DesignationDTOInterface dlDesignation=new DesignationDTO();
dlDesignation.setTitle(title);
new DesignationDAO().add(dlDesignation);
designation.setCode(dlDesignation.getCode());
DesignationInterface blDesignation=new Designation();
blDesignation.setCode(dlDesignation.getCode());
blDesignation.setTitle(title);
this.codeWiseDesignationsMap.put(blDesignation.getCode(),blDesignation);
this.titleWiseDesignationsMap.put(blDesignation.getTitle().toUpperCase(),blDesignation);
this.designationsSet.add(blDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}

public void updateDesignation(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null)
{
blException.setGenericException("Designation required");
throw blException;
}
int code=designation.getCode();
String title=designation.getTitle();
if(code<=0)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
if(code>0)
{
if(this.codeWiseDesignationsMap.containsKey(code)==false)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
}
if(title==null)
{
blException.addException("title","Designation title required");
title=" ";
}
else
{
title=title.trim();
if(title.length()==0) blException.addException("title","Designation title required");
}
if(title.length()>0)
{
DesignationInterface d;
d=titleWiseDesignationsMap.get(title.toUpperCase());
if(d!=null && d.getCode()!=code)
{
blException.addException("title","Designation : "+title+" exists.");
}
}
if(blException.hasExceptions()) throw blException;
try
{
DesignationInterface dsDesignation=codeWiseDesignationsMap.get(code);
DesignationDTOInterface dlDesignation=new DesignationDTO();
dlDesignation.setCode(code);
dlDesignation.setTitle(title);
new DesignationDAO().update(dlDesignation);
this.codeWiseDesignationsMap.remove(code);
this.titleWiseDesignationsMap.remove(dsDesignation.getTitle().toUpperCase());
this.designationsSet.remove(dsDesignation);
dsDesignation.setTitle(title);
this.codeWiseDesignationsMap.put(code,dsDesignation);
this.titleWiseDesignationsMap.put(title.toUpperCase(),dsDesignation);
this.designationsSet.add(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}

}
public void removeDesignation(int code) throws BLException
{
BLException blException=new BLException();
if(code<=0)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
if(code>0)
{
if(this.codeWiseDesignationsMap.containsKey(code)==false)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
}
try
{
DesignationInterface d=this.codeWiseDesignationsMap.get(code);
new DesignationDAO().delete(code);
this.codeWiseDesignationsMap.remove(code);
this.titleWiseDesignationsMap.remove(d.getTitle().toUpperCase());
this.designationsSet.remove(d);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}

}
public DesignationInterface getDSDesignationByCode(int code)
{
DesignationInterface designation;
designation=this.codeWiseDesignationsMap.get(code);
return designation;
}
public DesignationInterface getDesignationByCode(int code) throws BLException
{
BLException blException=new BLException();
DesignationInterface dsDesignation=codeWiseDesignationsMap.get(code);
if(dsDesignation==null)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
DesignationInterface designation=new Designation();
designation.setTitle(dsDesignation.getTitle());
designation.setCode(dsDesignation.getCode());
return designation;
}
public DesignationInterface getDesignationByTitle(String title) throws BLException
{
BLException blException=new BLException();
if(title==null)
{
blException.addException("title","Designation required");
throw blException;
}
title=title.trim();
if(title.length()==0)
{
blException.addException("title","Designation required");
throw blException;
}
DesignationInterface dsDesignation=titleWiseDesignationsMap.get(title.toUpperCase());
if(dsDesignation==null)
{
blException.addException("title","Invalid designation : "+title);
throw blException;
}
DesignationInterface designation=new Designation();
designation.setTitle(dsDesignation.getTitle());
designation.setCode(dsDesignation.getCode());
return designation;
}
public Set<DesignationInterface> getDesignations()
{
Set<DesignationInterface> designations=new TreeSet<>();
this.designationsSet.forEach((designation)->{
DesignationInterface d=new Designation();
d.setCode(designation.getCode());
d.setTitle(designation.getTitle());
designations.add(d);
});
return designations;
}
public int getDesignationCount()
{
return this.designationsSet.size();
}
public boolean designationCodeExists(int code)
{
return this.codeWiseDesignationsMap.containsKey(code);
}
public boolean designationTitleExists(String title)
{
return this.titleWiseDesignationsMap.containsKey(title.toUpperCase());
}
}