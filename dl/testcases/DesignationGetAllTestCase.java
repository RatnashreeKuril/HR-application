import java.util.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class DesignationGetAllTestCase
{
public static void main(String gg[])
{
Set<DesignationDTOInterface> designations;
designations=new TreeSet<>();
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
designations=designationDAO.getAll();
designations.forEach((designationDTO)->{
System.out.println("Title : "+designationDTO.getTitle()+" Code : "+designationDTO.getCode());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}