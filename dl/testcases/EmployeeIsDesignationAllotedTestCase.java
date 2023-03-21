import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class EmployeeIsDesignationAllotedTestCase
{
public static void main(String gg[])
{
int vDesignationCode=Integer.parseInt(gg[0]);
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
boolean isDesignationAlloted=employeeDAO.isDesignationAlloted(vDesignationCode);
System.out.println(vDesignationCode+" is alloted : "+isDesignationAlloted);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}