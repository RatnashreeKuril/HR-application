import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeGetAllTestCase
{
public static void main(String gg[])
{
Set<EmployeeDTOInterface> employes;
try
{
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employes=employeeDAO.getAll();
employes.forEach((employeeDTO)->{
System.out.println(employeeDTO.getEmployeeId());
System.out.println(employeeDTO.getName());
System.out.println(employeeDTO.getDesignationCode());
System.out.println(simpleDateFormat.format(employeeDTO.getDateOfBirth()));
System.out.println(employeeDTO.getGender());
System.out.println(employeeDTO.getIsIndian());
System.out.println(employeeDTO.getBasicSalary());
System.out.println(employeeDTO.getPANNumber());
System.out.println(employeeDTO.getAadharCardNumber());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}