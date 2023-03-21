import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeGetByDesignationCodeTestCase
{
public static void main(String gg[])
{
int vDesignationCode=Integer.parseInt(gg[0]);
Set<EmployeeDTOInterface> employes;
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
employes=employeeDAO.getByDesignationCode(vDesignationCode);
employes.forEach((employeeDTO)->{
System.out.println(employeeDTO.getName());
System.out.println(employeeDTO.getDesignationCode());
System.out.println(employeeDTO.getDateOfBirth());
System.out.println(employeeDTO.getGender());
System.out.println(employeeDTO.getIsIndian());
System.out.println(employeeDTO.getBasicSalary());
System.out.println(employeeDTO.getPANNumber());
System.out.println(employeeDTO.getAadharCardNumber());
System.out.println("Hi");
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}