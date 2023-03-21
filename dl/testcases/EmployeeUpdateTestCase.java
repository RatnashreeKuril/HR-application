import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.enums.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeeUpdateTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=gg[0];
String name=gg[1];
String designationCodeString=gg[2];
int designationCode=Integer.parseInt(designationCodeString);
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
Date dateOfBirth=simpleDateFormat.parse(gg[3]);
char gender=gg[4].charAt(0);
boolean isIndian=Boolean.parseBoolean(gg[5]);
BigDecimal basicSalary=new BigDecimal(gg[6]);
String panNumber=gg[7];
String aadharCardNumber=gg[8];
EmployeeDTOInterface employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
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
employeeDAO.update(employeeDTO);
System.out.println("Employee : "+employeeId+" updated." );
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