import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
boolean aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
System.out.println(aadharCardNumber+" exists : "+aadharCardNumberExists);
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}