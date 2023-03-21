import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.enums.*;
import java.util.*;
class EmployeeManagerRemoveTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
try
{
EmployeeManagerInterface employeeManager=EmployeeManager.getEmployeeManager();
employeeManager.removeEmployee(employeeId);
System.out.println("Removed");
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