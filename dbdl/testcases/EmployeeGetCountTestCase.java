import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeGetCountTestCase
{
public static void main(String gg[])
{
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
int recordCount=employeeDAO.getCount();
System.out.println("Record count : "+recordCount);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}