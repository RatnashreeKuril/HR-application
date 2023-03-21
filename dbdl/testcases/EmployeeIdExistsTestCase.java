import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class EmployeeIdExistsTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
boolean employeeIdExists=employeeDAO.employeeIdExists(employeeId);
System.out.println(employeeId+" exists : "+employeeIdExists);
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}