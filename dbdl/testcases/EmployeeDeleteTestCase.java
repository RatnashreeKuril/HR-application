import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class EmployeeDeleteTestCase
{
public static void main(String gg[])
{
try
{
String employeeId=gg[0];
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employeeDAO.delete(employeeId);
System.out.println("Employee : "+employeeId+" deleted." );
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}