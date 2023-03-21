import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class DesignationAddTestCase
{
public static void main(String gg[])
{
DesignationDTOInterface designationDTO=new DesignationDTO();
designationDTO.setTitle(gg[0]);
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
designationDAO.add(designationDTO);
System.out.println(designationDTO.getTitle()+" added with code "+designationDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}