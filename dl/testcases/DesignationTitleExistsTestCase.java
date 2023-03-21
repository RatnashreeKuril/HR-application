import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class DesignationTitleExistsTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationDAO.titleExists(title)) System.out.println(title+" exists.");
else System.out.println(title+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}