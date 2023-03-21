import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerGetEmployeeByDesignationCodeTestCase
{
public static void main(String gg[])
{
int designationCode=Integer.parseInt(gg[0]);
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
Set<EmployeeInterface> employees=employeeManager.getEmployeesByDesignationCode(designationCode);
for(EmployeeInterface employee:employees)
{
System.out.println(employee.getEmployeeId());
System.out.println(employee.getName());
System.out.println(employee.getDesignation().getCode());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
System.out.println(sdf.format(employee.getDateOfBirth()));
System.out.println(employee.getGender());
System.out.println(employee.getIsIndian());
System.out.println(employee.getBasicSalary().toPlainString());
System.out.println(employee.getPANNumber());
System.out.println(employee.getAadharCardNumber());
System.out.println("****************************************************************");
}
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