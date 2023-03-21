import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class DesignationUpdateTestCase
{
public static void main(String gg[])
{
DesignationDTOInterface designationDTO=new DesignationDTO();
int code=Integer.parseInt(gg[0]);
designationDTO.setCode(code);
designationDTO.setTitle(gg[1]);
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
designationDAO.update(designationDTO);
System.out.println("Designation updated");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}