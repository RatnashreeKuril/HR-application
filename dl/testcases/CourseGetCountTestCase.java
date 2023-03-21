import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class CourseGetCountTestCase
{
public static void main(String gg[])
{
try
{
CourseDAOInterface courseDAO=new CourseDAO();
int recordCount=courseDAO.getCount();
System.out.println("Number of records : "+recordCount);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}