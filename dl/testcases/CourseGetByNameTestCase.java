import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CourseGetByNameTestCase
{
public static void main(String gg[])
{
String name=gg[0];
try
{
CourseDTOInterface courseDTO;
CourseDAOInterface courseDAO=new CourseDAO();
courseDTO=courseDAO.getByName(name);
System.out.println("Name : "+courseDTO.getName()+" Code : "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}