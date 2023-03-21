import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.enums.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeeAddTestCase
{
public static void main(String gg[])
{
try
{
String employeeId;
String name=gg[0];
String designationCodeString=gg[1];
int designationCode=Integer.parseInt(designationCodeString);
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
Date dateOfBirth=simpleDateFormat.parse(gg[2]);
char gender=gg[3].charAt(0);
boolean isIndian=Boolean.parseBoolean(gg[4]);
BigDecimal basicSalary=new BigDecimal(gg[5]);
String panNumber=gg[6];
String aadharCardNumber=gg[7];
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
if(gender=='F') 
{
employeeDTO.setGender(GENDER.FEMALE);
}
else
{
employeeDTO.setGender(GENDER.MALE);
}
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employeeDAO.add(employeeDTO);
System.out.println(employeeDTO.getName()+" added with ID "+employeeDTO.getEmployeeId());
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}