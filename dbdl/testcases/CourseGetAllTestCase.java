import java.util.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class CourseGetAllTestCase
{
public static void main(String gg[])
{
Set<CourseDTOInterface> courses;
courses=new TreeSet<>();
try
{
CourseDAOInterface courseDAO=new CourseDAO();
courses=courseDAO.getAll();
courses.forEach((courseDTO)->{
System.out.println("Name : "+courseDTO.getName()+" Code : "+courseDTO.getCode());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}