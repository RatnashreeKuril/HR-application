import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeGetByAadharCardNumberTestCase
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
try
{
EmployeeDAOInterface employeeDAO=new EmployeeDAO();
EmployeeDTOInterface employeeDTO=employeeDAO.getByAadharCardNumber(aadharCardNumber);
System.out.println(employeeDTO.getEmployeeId());
System.out.println(employeeDTO.getName());
System.out.println(employeeDTO.getDesignationCode());
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String dateOfBirthString=simpleDateFormat.format(employeeDTO.getDateOfBirth());
System.out.println(dateOfBirthString);
System.out.println(employeeDTO.getGender());
System.out.println(employeeDTO.getIsIndian());
System.out.println(employeeDTO.getBasicSalary().toPlainString());
System.out.println(employeeDTO.getPANNumber());
System.out.println(employeeDTO.getAadharCardNumber());
}
catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}