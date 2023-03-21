import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class EmployeeGetCountByDesignationTestCase
{
public static void main(String gg[])
{
int designationCode=Integer.parseInt(gg[0]);
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
int recordCount=employeeDAO.getCountByDesignation(designationCode);
System.out.println("Record count : "+recordCount);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}