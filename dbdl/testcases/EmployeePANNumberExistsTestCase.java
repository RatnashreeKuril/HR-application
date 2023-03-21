import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class EmployeePANNumberExistsTestCase
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
boolean PANNumberExists=employeeDAO.panNumberExists(panNumber);
System.out.println(panNumber+" exists : "+PANNumberExists);
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}