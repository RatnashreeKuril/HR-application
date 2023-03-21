import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.text.*;
class EmployeeManagerGetEmployeeByPANNumberTestCase
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
EmployeeInterface employee=employeeManager.getEmployeeByPANNumber(panNumber);
System.out.println(employee.getEmployeeId());
System.out.println(employee.getName());
System.out.println((employee.getDesignation()).getCode());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyy");
String dateOfBirth=simpleDateFormat.format(employee.getDateOfBirth());
System.out.println(dateOfBirth);
System.out.println(employee.getGender());
System.out.println(employee.getIsIndian());
String basicSalary=employee.getBasicSalary().toPlainString();
System.out.println(basicSalary);
System.out.println(employee.getPANNumber());
System.out.println(employee.getAadharCardNumber());
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