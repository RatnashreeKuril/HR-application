import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerUpdateTestCase
{
public static void main(String gg[])
{
try
{
String employeeId="A10000001";
String name="   Rahul Sharma     ";
DesignationInterface designation=new Designation();
designation.setCode(2);
Date dateOfBirth=null;
boolean isIndian=true; 
BigDecimal basicSalary=new BigDecimal("66000");
String panNumber="SK87878";
String aadharCardNumber="2342342";
EmployeeInterface employee=new Employee();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignation(designation);
try
{
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=sdf.parse("14/11/1972");
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}
employee.setDateOfBirth(dateOfBirth);
employee.setGender(GENDER.MALE);
employee.setIsIndian(false);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.updateEmployee(employee);
System.out.println("Updated");
}catch(BLException blException)
{
if(blException.hasGenericException()) System.out.println(blException.getGenericException());
List<String>properties=blException.getExceptions();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}

}
}