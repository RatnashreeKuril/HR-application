import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
public class DesignationManagerAddTestCase
{
public static void main(String gg[])
{
String title=gg[0];
DesignationInterface designation=new Designation();
designation.setTitle(title);
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
designationManager.addDesignation(designation);
System.out.println("Designation added with code "+designation.getCode());
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
List<String>properties=blException.getExceptions();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}


}

}