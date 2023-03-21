package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.io.*;
import java.text.*;
public class EmployeeDAO implements EmployeeDAOInterface
{
private static final String FILE_NAME="employee.data";
public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Employee is null");
String employeeId;
String name=employeeDTO.getName();
if(name==null) throw new DAOException("Name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("Length of name zero");
int designationCode=employeeDTO.getDesignationCode();
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationCode<=0) throw new DAOException("Invalid designation code : "+designationCode);
if(designationDAO.codeExists(designationCode)==false) throw new DAOException("Invalid designation code : "+designationCode);
Date dateOfBirth=employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("Date of birth is null");
char gender=employeeDTO.getGender();
if(gender==' ') throw new DAOException("Gender not set to Male/Female");
boolean isIndian=employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Basic salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Basic salary is negative");
String panNumber=employeeDTO.getPANNumber();
if(panNumber==null) throw new DAOException("PAN number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of PAN number is zero");
String aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("Aadhar card number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Length of aadhar card number is zero");
try
{
int lastGeneratedEmployeeId=10000000;
String lastGeneratedEmployeeIdString=" ";
int recordCount=0;
String recordCountString=" ";
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
lastGeneratedEmployeeIdString=String.format("%-10s",10000000);
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCountString=String.format("%-10s","0");
randomAccessFile.writeBytes(recordCountString+"\n");
}
else
{
lastGeneratedEmployeeId=Integer.parseInt(randomAccessFile.readLine().trim());
recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
}
String fPanNumber;
String fAadharCardNumber;
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int e=1;e<=7;e++) randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(panNumberExists==false && fPanNumber.equalsIgnoreCase(panNumber)) panNumberExists=true;
if(aadharCardNumberExists==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)) aadharCardNumberExists=true;
if(aadharCardNumberExists==true && panNumberExists==true) break;
}
if(aadharCardNumberExists==true && panNumberExists==true)
{
randomAccessFile.close();
throw new DAOException("Pan number ("+panNumber+") and aadhar card number ("+aadharCardNumber+") already exists");
}
if(panNumberExists==true)
{
randomAccessFile.close();
throw new DAOException("Pan number ("+panNumber+") already exists");
}
if(aadharCardNumberExists==true)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number ("+aadharCardNumber+") already exists");
}
lastGeneratedEmployeeId++;
employeeId="A"+lastGeneratedEmployeeId;
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
employeeDTO.setEmployeeId("A"+lastGeneratedEmployeeId);
randomAccessFile.seek(0);
lastGeneratedEmployeeIdString=String.format("%-10d",lastGeneratedEmployeeId);
randomAccessFile.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCount++;
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void update(EmployeeDTOInterface employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("Employee is null");
String employeeId=employeeDTO.getEmployeeId();
if(employeeId==null) throw new DAOException("Employee Id. is null");
if(employeeId.length()==0) throw new DAOException("Length of employee Id. is zero");
String name=employeeDTO.getName();
if(name==null) throw new DAOException("Name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("Length of name zero");
int designationCode=employeeDTO.getDesignationCode();
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationCode<=0) throw new DAOException("Invalid designation code : "+designationCode);
if(designationDAO.codeExists(designationCode)==false) throw new DAOException("Invalid designation code : "+designationCode);
Date dateOfBirth=employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("Date of birth is null");
char gender=employeeDTO.getGender();
if(gender==' ') throw new DAOException("Gender not set to Male/Female");
boolean isIndian=employeeDTO.getIsIndian();
BigDecimal basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("Basic salary is null");
if(basicSalary.signum()==-1) throw new DAOException("Basic salary is negative");
String panNumber=employeeDTO.getPANNumber();
if(panNumber==null) throw new DAOException("PAN number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Length of PAN number is zero");
String aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("Aadhar card number is null");
aadharCardNumber=aadharCardNumber.trim();
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid employee Id. : "+employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
String fEmployeeId="";
String fPanNumber;
String fAadharCardNumber;
boolean employeeIdFound=false;
boolean panNumberFound=false;
boolean aadharCardNumberFound=false;
String panNumberFoundAgainstEmployeeId;
String aadharCardNumberFoundAgainstEmployeeId;
int x;
long foundAt=0;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
if(employeeIdFound==false) foundAt=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine();
for(x=1;x<=6;x++) randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(employeeIdFound==false && fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeIdFound=true;
}
if(panNumberFound==false && fPanNumber.equalsIgnoreCase(panNumber))
{
panNumberFound=true;
panNumberFoundAgainstEmployeeId=fEmployeeId;
}
if(aadharCardNumberFound==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
{
aadharCardNumberFound=true;
aadharCardNumberFoundAgainstEmployeeId=fEmployeeId;
}
if(employeeIdFound && panNumberFound && aadharCardNumberFound) break;
}
if(employeeIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
boolean panNumberExists=false;
if(panNumberFound==true && fEmployeeId.equalsIgnoreCase(employeeId)==false)
{
panNumberExists=true;
}
boolean aadharCardNumberExists=false;
if(aadharCardNumberFound==true && fEmployeeId.equalsIgnoreCase(employeeId)==false)
{
aadharCardNumberExists=true;
}
if(aadharCardNumberExists==true && panNumberExists==true)
{
randomAccessFile.close();
throw new DAOException("Pan number ("+panNumber+") and aadhar card number ("+aadharCardNumber+") already exists");
}
if(panNumberExists==true)
{
randomAccessFile.close();
throw new DAOException("Pan number ("+panNumber+") already exists");
}
if(aadharCardNumberExists==true)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number ("+aadharCardNumber+") already exists");
}
randomAccessFile.seek(foundAt);
for(x=1;x<=9;x++) randomAccessFile.readLine();
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
randomAccessFile.seek(foundAt);
randomAccessFile.writeBytes(employeeId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(designationCode+"\n");
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(basicSalary.toPlainString()+"\n");
randomAccessFile.writeBytes(panNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public void delete(String employeeId) throws DAOException
{
if(employeeId==null) throw new DAOException("Employee Id. is null");
if(employeeId.length()==0) throw new DAOException("Length of employee Id. is zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid employee Id. : "+employeeId);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
String fEmployeeId="";
boolean employeeIdFound=false;
int x;
long foundAt=0;
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
foundAt=randomAccessFile.getFilePointer();
fEmployeeId=randomAccessFile.readLine();
for(x=1;x<=8;x++) randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId)==true)
{
employeeIdFound=true;
break;
}
}
if(employeeIdFound==false)
{
randomAccessFile.close();
throw new DAOException("Invalid employee Id. : "+employeeId);
}
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
randomAccessFile.seek(foundAt);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(randomAccessFile.getFilePointer());
recordCount--;
String recordCountString=String.format("%-10d",recordCount);
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.writeBytes(recordCountString+"\n");
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public Set<EmployeeDTOInterface> getAll() throws DAOException
{
Set<EmployeeDTOInterface>employees=new TreeSet<>();
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return employees;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0) return employees;
String employeeId;
String name;
int designationCode;
Date dateOfBirth;
char gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
GENDER vGender;
EmployeeDTOInterface employeeDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeId=randomAccessFile.readLine();
name=randomAccessFile.readLine();
designationCode=Integer.parseInt(randomAccessFile.readLine());
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
gender=randomAccessFile.readLine().charAt(0);
isIndian=Boolean.parseBoolean(randomAccessFile.readLine());
basicSalary=new BigDecimal(randomAccessFile.readLine());
panNumber=randomAccessFile.readLine();
aadharCardNumber=randomAccessFile.readLine();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
if(gender=='M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(gender=='F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employees.add(employeeDTO);
}
randomAccessFile.close();
return employees;
}catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException
{
Set<EmployeeDTOInterface>employees=new TreeSet<>();
if(designationCode<=0) return employees;
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationDAO.codeExists(designationCode)==false) return employees;
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return employees;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return employees;
}
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0) return employees;
String employeeId;
String name;
int fDesignationCode;
Date dateOfBirth;
char gender;
GENDER vGender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String aadharCardNumber;
EmployeeDTOInterface employeeDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeId=randomAccessFile.readLine();
name=randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode)
{
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
gender=randomAccessFile.readLine().charAt(0);
isIndian=Boolean.parseBoolean(randomAccessFile.readLine());
basicSalary=new BigDecimal(randomAccessFile.readLine());
panNumber=randomAccessFile.readLine();
aadharCardNumber=randomAccessFile.readLine();
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
if(gender=='M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(gender=='F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employees.add(employeeDTO);
}
else for(int e=1;e<=6;e++) randomAccessFile.readLine();
}
randomAccessFile.close();
return employees;
}catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public boolean isDesignationAlloted(int designationCode) throws DAOException
{
if(designationCode<=0) return false;
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationDAO.codeExists(designationCode)==false) return false;
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode)
{
return true;
}
else for(int e=1;e<=6;e++) randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
{
if(employeeId==null) throw new DAOException("Employee ID is null");
employeeId=employeeId.trim();
if(employeeId.length()==0) throw new DAOException("Length of employee ID is zero");
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) throw new DAOException("Invalid employee ID");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid employee ID");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fEmployeeId;
EmployeeDTOInterface employeeDTO;
Date dateOfBirth=null;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(fEmployeeId);
employeeDTO.setName(randomAccessFile.readLine());
int designationCode=Integer.parseInt(randomAccessFile.readLine());
employeeDTO.setDesignationCode(designationCode);
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
dateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
// do nothing
}
employeeDTO.setDateOfBirth(dateOfBirth);
char gender=randomAccessFile.readLine().charAt(0);
if(gender=='M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(gender=='F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
boolean isIndian=Boolean.parseBoolean(randomAccessFile.readLine());
employeeDTO.setIsIndian(isIndian);
BigDecimal basicSalary=new BigDecimal(randomAccessFile.readLine());
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(randomAccessFile.readLine());
employeeDTO.setAadharCardNumber(randomAccessFile.readLine());
randomAccessFile.close();
return employeeDTO;
}
else
{
for(int e=1;e<=8;e++) randomAccessFile.readLine();
}
}
randomAccessFile.close();
throw new DAOException("Invalid employee ID");
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
{
if(panNumber==null) throw new DAOException("Invalid PAN number : "+panNumber);
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("Invalid PAN number : PAN number is of zero length");
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) throw new DAOException("Invalid PAN number : "+panNumber);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid PAN number : "+panNumber);
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String employeeId;
String name;
int designationCode;
Date dateOfBirth=null;
char gender;
boolean isIndian;
BigDecimal basicSalary;
String fPanNumber;
String aadharCardNumber;
EmployeeDTOInterface employeeDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeId=randomAccessFile.readLine();
name=randomAccessFile.readLine();
designationCode=Integer.parseInt(randomAccessFile.readLine());
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try
{
dateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
}catch(ParseException parseException)
{
// do nothing
}
gender=randomAccessFile.readLine().charAt(0);
isIndian=Boolean.parseBoolean(randomAccessFile.readLine());
basicSalary=new BigDecimal(randomAccessFile.readLine());
fPanNumber=randomAccessFile.readLine();
aadharCardNumber=randomAccessFile.readLine();
if(fPanNumber.equalsIgnoreCase(panNumber)==true)
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(fPanNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid PAN number : "+panNumber);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber)throws DAOException
{
if(aadharCardNumber==null) throw new DAOException("Invalid aadhar card number");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Invalid aadhar card number");
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) throw new DAOException("Invalid aadhar card number");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid aadhar card number");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String employeeId;
String name;
int designationCode;
Date dateOfBirth;
char gender;
boolean isIndian;
BigDecimal basicSalary;
String panNumber;
String fAadharCardNumber;
EmployeeDTOInterface employeeDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
employeeId=randomAccessFile.readLine();
name=randomAccessFile.readLine();
designationCode=Integer.parseInt(randomAccessFile.readLine());
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=simpleDateFormat.parse(randomAccessFile.readLine());
gender=randomAccessFile.readLine().charAt(0);
isIndian=Boolean.parseBoolean(randomAccessFile.readLine());
basicSalary=new BigDecimal(randomAccessFile.readLine());
panNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)==true)
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
if(gender=='M')
{
employeeDTO.setGender(GENDER.MALE);
}
if(gender=='F')
{
employeeDTO.setGender(GENDER.FEMALE);
}
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
randomAccessFile.close();
return employeeDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid aadhar card number");
}catch(ParseException parseException)
{
throw new DAOException(parseException.getMessage());
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public boolean employeeIdExists(String employeeId) throws DAOException
{
if(employeeId==null) return false;
employeeId=employeeId.trim();
if(employeeId.length()==0) return false;
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return false;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fEmployeeId;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fEmployeeId=randomAccessFile.readLine();
if(fEmployeeId.equalsIgnoreCase(employeeId))
{
randomAccessFile.close();
return true;
}
else
{
for(int e=1;e<=8;e++) randomAccessFile.readLine();
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public boolean panNumberExists(String panNumber) throws DAOException
{
if(panNumber==null) return false;
panNumber=panNumber.trim();
if(panNumber.length()==0) return false;
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fPanNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int e=1;e<=7;e++) randomAccessFile.readLine();
fPanNumber=randomAccessFile.readLine();
if(fPanNumber.equalsIgnoreCase(panNumber)==true)
{
randomAccessFile.close();
return true;
}
randomAccessFile.readLine();
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public boolean aadharCardNumberExists(String aadharCardNumber)throws DAOException
{
if(aadharCardNumber==null) return false;
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) return false;
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return false;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
String fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int e=1;e<=8;e++) randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber)==true)
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}
public int getCount() throws DAOException
{
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return 0;
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) return 0;
randomAccessFile.readLine();
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
public int getCountByDesignation(int designationCode) throws DAOException
{
if(designationCode<=0) throw new DAOException("Invalid designation : "+designationCode);
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationDAO.codeExists(designationCode)==false) throw new DAOException("Invalid designation : "+designationCode);
try
{
File file=new File(FILE_NAME);
if(!(file.exists())) return 0;
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fDesignationCode;
int recordCount=0;
int e;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fDesignationCode=Integer.parseInt(randomAccessFile.readLine());
if(fDesignationCode==designationCode)
{
recordCount++;
}
for(e=1;e<=6;e++) randomAccessFile.readLine();
}
randomAccessFile.close();
return recordCount;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}
}