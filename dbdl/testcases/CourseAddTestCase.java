import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class CourseAddTestCase
{
public static void main(String gg[])
{
CourseDTOInterface courseDTO=new CourseDTO();
courseDTO.setName(gg[0]);
try
{
CourseDAOInterface courseDAO=new CourseDAO();
courseDAO.add(courseDTO);
System.out.println(courseDTO.getName()+" added with code "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}