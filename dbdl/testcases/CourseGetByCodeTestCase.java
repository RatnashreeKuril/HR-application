import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class CourseGetByCodeTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
CourseDTOInterface courseDTO;
CourseDAOInterface courseDAO=new CourseDAO();
courseDTO=courseDAO.getByCode(code);
System.out.println("Name : "+courseDTO.getName()+" Code : "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}