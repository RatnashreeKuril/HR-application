import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CourseNameExistsTestCase
{
public static void main(String gg[])
{
String name=gg[0];
try
{
CourseDAOInterface courseDAO=new CourseDAO();
System.out.println(name+" exists : "+courseDAO.nameExists(name));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}