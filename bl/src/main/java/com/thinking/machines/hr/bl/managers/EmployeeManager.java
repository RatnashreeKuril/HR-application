package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.enums.*;
import java.math.*;
import java.util.*;
import java.text.*;
public class EmployeeManager implements EmployeeManagerInterface
{
private Map<String, EmployeeInterface>employeeIdWiseEmployeesMap;
private Map<String, EmployeeInterface>panNumberWiseEmployeesMap;
private Map<String, EmployeeInterface>aadharCardNumberWiseEmployeesMap;
private Set<EmployeeInterface> employeesSet;
private Map<Integer,Set<EmployeeInterface>> designationCodeWiseEmployeesMap;
private static EmployeeManagerInterface employeeManager=null;
private EmployeeManager() throws BLException
{
populateDataStructures();
}
private void populateDataStructures() throws BLException
{
this.employeeIdWiseEmployeesMap=new HashMap<>();
this.panNumberWiseEmployeesMap=new HashMap<>();
this.aadharCardNumberWiseEmployeesMap=new HashMap<>();
this.designationCodeWiseEmployeesMap=new HashMap<>();
this.employeesSet=new TreeSet<>();
try
{
Set<EmployeeDTOInterface>dlEmployees;
EmployeeInterface employee;
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
dlEmployees=employeeDAO.getAll();
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation;
for(EmployeeDTOInterface dlEmployee:dlEmployees)
{
employee=new Employee();
employee.setEmployeeId(dlEmployee.getEmployeeId());
employee.setName(dlEmployee.getName());
designation=designationManager.getDesignationByCode(dlEmployee.getDesignationCode());
employee.setDesignation(designation);
employee.setDateOfBirth(dlEmployee.getDateOfBirth());
employee.setGender((dlEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(dlEmployee.getIsIndian());
employee.setBasicSalary(dlEmployee.getBasicSalary());
employee.setPANNumber(dlEmployee.getPANNumber());
employee.setAadharCardNumber(dlEmployee.getAadharCardNumber());
this.employeeIdWiseEmployeesMap.put(employee.getEmployeeId().toUpperCase(),employee);
this.panNumberWiseEmployeesMap.put(employee.getPANNumber().toUpperCase(),employee);
this.aadharCardNumberWiseEmployeesMap.put(employee.getAadharCardNumber().toUpperCase(),employee);
this.employeesSet.add(employee);
Set<EmployeeInterface> e=designationCodeWiseEmployeesMap.get(designation.getCode());
if(e==null)
{
e=new TreeSet<>();
e.add(employee);
this.designationCodeWiseEmployeesMap.put(designation.getCode(),e);
}
else
{
e.add(employee);
}
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public static EmployeeManagerInterface getEmployeeManager() throws BLException
{
if(employeeManager==null) employeeManager=new EmployeeManager();
return employeeManager;
}
public void addEmployee(EmployeeInterface employee) throws BLException
{
BLException blException=new BLException();
if(employee==null)
{
blException.setGenericException("Employee details are required");
throw blException;
}
String employeeId=employee.getEmployeeId();
String name=employee.getName();
DesignationInterface designation=employee.getDesignation();
int designationCode=0;
Date dateOfBirth=employee.getDateOfBirth();
char gender=employee.getGender();
boolean isIndian=employee.getIsIndian();
BigDecimal basicSalary=employee.getBasicSalary();
String panNumber=employee.getPANNumber();
String aadharCardNumber=employee.getAadharCardNumber();
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
DesignationInterface dsDesignation=null;
if(employeeId!=null)
{
employeeId=employeeId.trim();
if(employeeId.length()>0)
{
blException.addException("employeeId","Employee Id. should be nil/empty");
}
}
if(name==null) blException.addException("name","Name required");
else
{
name=name.trim();
if(name.length()==0) blException.addException("name","Name required");
}
if(designation==null) blException.addException("designation","Designation required");
else
{
designationCode=designation.getCode();
dsDesignation=((DesignationManager)designationManager).getDSDesignationByCode(designationCode);
if(dsDesignation==null) blException.addException("designation","Invalid designation");
}
if(dateOfBirth==null) blException.addException("dateOfBirth","Date of birth required");
if(gender==' ') blException.addException("gender","Gender reqiured");
if(basicSalary==null) blException.addException("basicSalary","Basic salary required");
else
{
if(basicSalary.signum()==-1) blException.addException("basicSalary","Basic salary cannot be negetive");
}
if(panNumber==null) blException.addException("panNumber","PAN number required");
else
{
panNumber=panNumber.trim();
if(panNumber.length()==0) blException.addException("panNumber","PAN number required");
}
if(aadharCardNumber==null) blException.addException("aadharCardNumber","Aadhar card number required");
else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) blException.addException("aadharCardNumber","Aadhar card number required");
}
if(panNumber!=null && panNumber.length()>0)
{
if(panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase())==true) blException.addException("panNumber","PAN number exists");
}
if(aadharCardNumber!=null && aadharCardNumber.length()>0)
{
if(aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase())==true) blException.addException("aadharCardNumber","Aadhar card number exists");
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employeeDAO.add(employeeDTO);
employee.setEmployeeId(employeeDTO.getEmployeeId());
EmployeeInterface dsEmployee=new Employee();
dsEmployee.setEmployeeId(employeeDTO.getEmployeeId());
dsEmployee.setName(employeeDTO.getName());
dsEmployee.setDesignation(((DesignationManager)designationManager).getDSDesignationByCode(dsDesignation.getCode()));
dsEmployee.setDateOfBirth((Date)dateOfBirth.clone());
dsEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dsEmployee.setIsIndian(isIndian);
dsEmployee.setBasicSalary(basicSalary);
dsEmployee.setPANNumber(panNumber);
dsEmployee.setAadharCardNumber(aadharCardNumber);
this.employeeIdWiseEmployeesMap.put(dsEmployee.getEmployeeId().toUpperCase(),dsEmployee);
this.panNumberWiseEmployeesMap.put(dsEmployee.getPANNumber().toUpperCase(),dsEmployee);
this.aadharCardNumberWiseEmployeesMap.put(dsEmployee.getAadharCardNumber().toUpperCase(),dsEmployee);
this.employeesSet.add(dsEmployee);
Set<EmployeeInterface> ets=this.designationCodeWiseEmployeesMap.get(dsEmployee.getDesignation().getCode());
if(ets==null)
{
ets=new TreeSet<>();
ets.add(dsEmployee);
this.designationCodeWiseEmployeesMap.put(dsEmployee.getDesignation().getCode(),ets);
}
else
{
ets.add(dsEmployee);
}
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void updateEmployee(EmployeeInterface employee) throws BLException
{
BLException blException=new BLException();
if(employee==null)
{
blException.setGenericException("Employee details are required");
throw blException;
}
String employeeId=employee.getEmployeeId();
String name=employee.getName();
DesignationInterface designation=employee.getDesignation();
int designationCode=0;
Date dateOfBirth=employee.getDateOfBirth();
char gender=employee.getGender();
boolean isIndian=employee.getIsIndian();
BigDecimal basicSalary=employee.getBasicSalary();
String panNumber=employee.getPANNumber();
String aadharCardNumber=employee.getAadharCardNumber();
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
DesignationInterface dsDesignation=null;
if(employeeId==null)
{
blException.addException("employeeId","Employee Id. required");
throw blException;
}
else
{
employeeId=employeeId.trim();
if(employeeId.length()==0)
{
blException.addException("employeeId","Id. required");
}
else
{
if(employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase())==false)
{
blException.addException("employeeId","Invalid employee Id. : "+employeeId);
throw blException;
}
}
}
if(name==null) blException.addException("name","Name required");
else
{
name=name.trim();
if(name.length()==0) blException.addException("name","Name required");
}
if(designation==null) blException.addException("designation","Designation required");
else
{
designationCode=designation.getCode();
dsDesignation=((DesignationManager)designationManager).getDSDesignationByCode(designationCode);
if(dsDesignation==null) blException.addException("designation","Invalid designation");
}
if(dateOfBirth==null) blException.addException("dateOfBirth","Date of birth required");
if(gender==' ') blException.addException("gender","Gender reqiured");
if(basicSalary==null) blException.addException("basicSalary","Basic salary required");
else
{
if(basicSalary.signum()==-1) blException.addException("basicSalary","Basic salary cannot be negetive");
}
if(panNumber==null) blException.addException("panNumber","PAN number required");
else
{
panNumber=panNumber.trim();
if(panNumber.length()==0) blException.addException("panNumber","PAN number required");
}
if(aadharCardNumber==null) blException.addException("aadharCardNumber","Aadhar card number required");
else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) blException.addException("aadharCardNumber","Aadhar card number required");
}

if(panNumber!=null && panNumber.length()>0)
{
EmployeeInterface e=panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
if(e!=null && e.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
blException.addException("panNumber","PAN number "+panNumber+" exists");
}
}
if(aadharCardNumber!=null && aadharCardNumber.length()>0)
{
EmployeeInterface e=aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
if(e!=null && e.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
blException.addException("aadharCardNumber","Aadhar card number "+aadharCardNumber+" exists");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
EmployeeInterface dsEmployee;
dsEmployee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
String oldPANNumber=dsEmployee.getPANNumber();
String oldAadharCardNumber=dsEmployee.getAadharCardNumber();
int oldDesignationCode=dsEmployee.getDesignation().getCode();
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(dsEmployee.getEmployeeId());
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employeeDAO.update(employeeDTO);
dsEmployee.setEmployeeId(employeeId);
dsEmployee.setName(name);
dsEmployee.setDesignation(dsDesignation);
dsEmployee.setDateOfBirth((Date)dateOfBirth.clone());
dsEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dsEmployee.setIsIndian(isIndian);
dsEmployee.setBasicSalary(basicSalary);
dsEmployee.setPANNumber(panNumber);
dsEmployee.setAadharCardNumber(aadharCardNumber);
this.employeesSet.remove(dsEmployee);
this.employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
this.panNumberWiseEmployeesMap.remove(oldPANNumber.toUpperCase());
this.aadharCardNumberWiseEmployeesMap.remove(oldAadharCardNumber.toUpperCase());
this.employeesSet.add(dsEmployee);
this.employeeIdWiseEmployeesMap.put(dsEmployee.getEmployeeId().toUpperCase(),dsEmployee);
this.panNumberWiseEmployeesMap.put(panNumber.toUpperCase(),dsEmployee);
this.aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase(),dsEmployee);
if(oldDesignationCode!=dsEmployee.getDesignation().getCode())
{
Set<EmployeeInterface> ets=this.designationCodeWiseEmployeesMap.get(dsEmployee.getDesignation().getCode());
ets.remove(dsEmployee);
ets=this.designationCodeWiseEmployeesMap.get(dsEmployee.getDesignation().getCode());
if(ets==null)
{
ets=new TreeSet<>();
ets.add(dsEmployee);
this.designationCodeWiseEmployeesMap.put(dsEmployee.getDesignation().getCode(),ets);
}
else
{
ets.add(dsEmployee);
}
}
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void removeEmployee(String employeeId) throws BLException
{
if(employeeId==null)
{
BLException blException=new BLException();
blException.addException("employeeId","Employee Id. required");
throw blException;
}
else
{
employeeId=employeeId.trim();
if(employeeId.length()==0)
{
BLException blException=new BLException();
blException.addException("employeeId","Id. required");
throw blException;
}
else
{
if(employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase())==false)
{
BLException blException=new BLException();
blException.addException("employeeId","Invalid employee Id. : "+employeeId);
throw blException;
}
}
}
try
{
EmployeeInterface dsEmployee;
dsEmployee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employeeDAO.delete(dsEmployee.getEmployeeId());
this.employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
this.panNumberWiseEmployeesMap.remove(dsEmployee.getPANNumber().toUpperCase());
this.aadharCardNumberWiseEmployeesMap.remove(dsEmployee.getAadharCardNumber().toUpperCase());
this.employeesSet.remove(dsEmployee);
Set<EmployeeInterface> ets=this.designationCodeWiseEmployeesMap.get(dsEmployee.getDesignation().getCode());
ets.remove(dsEmployee);
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}

}
public EmployeeInterface getEmployeeByEmployeeId(String employeeId) throws BLException
{
EmployeeInterface dsEmployee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
if(dsEmployee==null)
{
BLException blException=new BLException();
blException.addException("employeeId","Invalid employee Id. : "+employeeId);
throw blException;
}
EmployeeInterface e=new Employee();
e.setEmployeeId(dsEmployee.getEmployeeId());
e.setName(dsEmployee.getName());
DesignationInterface designation=new Designation();
designation.setTitle(dsEmployee.getDesignation().getTitle());
designation.setCode(dsEmployee.getDesignation().getCode());
e.setDesignation(designation);
e.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
e.setGender((dsEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
e.setIsIndian(dsEmployee.getIsIndian());
e.setBasicSalary(dsEmployee.getBasicSalary());
e.setPANNumber(dsEmployee.getPANNumber());
e.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return e;
}
public EmployeeInterface getEmployeeByPANNumber(String panNumber) throws BLException
{
EmployeeInterface dsEmployee=this.panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
if(dsEmployee==null)
{
BLException blException=new BLException();
blException.addException("panNumber","Invalid PAN Number : "+panNumber);
throw blException;
}
EmployeeInterface e=new Employee();
e.setEmployeeId(dsEmployee.getEmployeeId());
e.setName(dsEmployee.getName());
DesignationInterface designation=new Designation();
designation.setTitle(dsEmployee.getDesignation().getTitle());
designation.setCode(dsEmployee.getDesignation().getCode());
e.setDesignation(designation);
e.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
e.setGender((dsEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
e.setIsIndian(dsEmployee.getIsIndian());
e.setBasicSalary(dsEmployee.getBasicSalary());
e.setPANNumber(dsEmployee.getPANNumber());
e.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return e;
}
public EmployeeInterface getEmployeeByAadharCardNumber(String aadharCardNumber) throws BLException
{
EmployeeInterface dsEmployee=this.aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
if(dsEmployee==null)
{
BLException blException=new BLException();
blException.addException("aadharCardNumber","Invalid aadhar card Number : "+aadharCardNumber);
throw blException;
}
EmployeeInterface e=new Employee();
e.setEmployeeId(dsEmployee.getEmployeeId());
e.setName(dsEmployee.getName());
DesignationInterface designation=new Designation();
designation.setTitle(dsEmployee.getDesignation().getTitle());
designation.setCode(dsEmployee.getDesignation().getCode());
e.setDesignation(designation);
e.setDateOfBirth((Date)dsEmployee.getDateOfBirth().clone());
e.setGender((dsEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
e.setIsIndian(dsEmployee.getIsIndian());
e.setBasicSalary(dsEmployee.getBasicSalary());
e.setPANNumber(dsEmployee.getPANNumber());
e.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return e;
}
public int getEmployeeCount()
{
return this.employeesSet.size();
}
public Set<EmployeeInterface> getEmployees()
{
Set<EmployeeInterface> employees=new TreeSet<>();
EmployeeInterface employee;
DesignationInterface designation;
for(EmployeeInterface e:employeesSet)
{
employee=new Employee();
employee.setEmployeeId(e.getEmployeeId());
employee.setName(e.getName());
designation=new Designation();
designation.setCode(e.getDesignation().getCode());
designation.setTitle(e.getDesignation().getTitle());
employee.setDesignation(designation);
employee.setDateOfBirth((Date)e.getDateOfBirth().clone());
employee.setGender((e.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(e.getIsIndian());
employee.setBasicSalary(e.getBasicSalary());
employee.setPANNumber(e.getPANNumber());
employee.setAadharCardNumber(e.getAadharCardNumber());
employees.add(employee);
}
return employees;
}
public boolean employeeIdExists(String employeeId)
{
return this.employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase());
}
public boolean employeePanNumberExists(String panNumber)
{
return this.panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase());
}
public boolean employeeAadharCardNumberExists(String aadharCardNumber)
{
return this.aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase());
}
public Set<EmployeeInterface> getEmployeesByDesignationCode(int code) throws BLException
{
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
if(designationManager.designationCodeExists(code)==false)
{
BLException blException=new BLException();
blException.setGenericException("Invalid designation code : "+code);
throw blException;
}
Set<EmployeeInterface> employees=new TreeSet<>();
Set<EmployeeInterface> dsEmployees=this.designationCodeWiseEmployeesMap.get(code);
if(dsEmployees==null)
{
return employees;
}
EmployeeInterface employee;
DesignationInterface designation;
for(EmployeeInterface e:dsEmployees)
{
employee=new Employee();
employee.setEmployeeId(e.getEmployeeId());
employee.setName(e.getName());
designation=new Designation();
designation.setCode(e.getDesignation().getCode());
designation.setTitle(e.getDesignation().getTitle());
employee.setDesignation(designation);
employee.setDateOfBirth((Date)e.getDateOfBirth().clone());
employee.setGender((e.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(e.getIsIndian());
employee.setBasicSalary(e.getBasicSalary());
employee.setPANNumber(e.getPANNumber());
employee.setAadharCardNumber(e.getAadharCardNumber());
employees.add(employee);
}
return employees;
}
public int getEmployeeCountByDesignationCode(int designationCode) throws BLException
{
Set<EmployeeInterface> ets=this.designationCodeWiseEmployeesMap.get(designationCode);
if(ets==null)
{
return 0;
}
return ets.size();
}
public boolean designationAlloted(int designationCode) throws BLException
{
return this.designationCodeWiseEmployeesMap.containsKey(designationCode);
}
}