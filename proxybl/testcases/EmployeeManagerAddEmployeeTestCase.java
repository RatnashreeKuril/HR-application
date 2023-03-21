import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerAddEmployeeTestCase
{
public static void main(String gg[])
{
try
{
String name="Ritu Maheshwari";
DesignationInterface designation=new Designation();
designation.setCode(1);
Date dateOfBirth=null;
boolean isIndian=true; 
BigDecimal basicSalary=new BigDecimal("30000");
String panNumber="HJ234334";
String aadharCardNumber="32423234";
EmployeeInterface employee=new Employee();
employee.setName(name);
employee.setDesignation(designation);
try
{
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
dateOfBirth=sdf.parse("20/08/1985");
}catch(ParseException parseException)
{
System.out.println(parseException.getMessage());
}
employee.setDateOfBirth(dateOfBirth);
employee.setGender(GENDER.FEMALE);
employee.setIsIndian(isIndian);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.addEmployee(employee);
System.out.println("added");
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