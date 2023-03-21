import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
class DesignationManagerGetDesignationByTitleTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface designation=new Designation();
designation=designationManager.getDesignationByTitle(title);
System.out.println("Code : "+designation.getCode()+" , Title : "+designation.getTitle());
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