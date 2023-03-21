import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CourseCodeExistsTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
CourseDAOInterface courseDAO=new CourseDAO();
System.out.println(code+" exists : "+courseDAO.codeExists(code));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}