import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class DesignationCodeExistsTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
try
{
DesignationDAOInterface designationDAO=new DesignationDAO();
if(designationDAO.codeExists(code)) System.out.println(code+" exists.");
else System.out.println(code+" does not exists.");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}