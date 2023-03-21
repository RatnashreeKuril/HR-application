import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
class StudentTableModel extends AbstractTableModel
{
private String title[];
int e;
private Set<DesignationDTOInterface> designations;
private Object designationsArray[];
StudentTableModel()
{
populateDataStructure();
e=0;
}
private void populateDataStructure()
{
System.out.println("Populate data structure");
title=new String[3];
title[0]="S.No.";
title[1]="Designation code";
title[2]="Designation";
System.out.println("1 Populate data structure");
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
try
{
System.out.println("2 Populate data structure");
designations=designationDAO.getAll();
System.out.println("2 Populate data structure");
designations.forEach((designationDTO)->{
System.out.println("Title : "+designationDTO.getTitle()+" Code : "+designationDTO.getCode());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
designationsArray=designations.toArray();
}
public int getRowCount()
{
return designations.size();
}
public int getColumnCount()
{
return title.length;
}
public boolean isCellEditable(int rowIndex, int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex, int columnIndex)
{
if(columnIndex==0)
{
e++;
return e;
}
DesignationDTOInterface designationDTO;
designationDTO=(DesignationDTOInterface)designationsArray[rowIndex];
if(columnIndex==1)
{
return designationDTO.getCode();
}
if(columnIndex==2)
{
return designationDTO.getTitle();
}
return null;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
}
class dlUI extends JFrame
{
private StudentTableModel studentTableModel;
private JTable table;
private JScrollPane jsp;
private Container container;
dlUI()
{
studentTableModel=new StudentTableModel();
table=new JTable(studentTableModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new BorderLayout());
container.add(jsp);
int width=600;
int height=600;
setSize(width,height);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int x=(d.width/2)-(width/2);
int y=(d.height/2)-(height/2);
setLocation(x,y);
setVisible(true);

}


}
class dlUIpsp
{
public static void main(String gg[])
{
dlUI dlui=new dlUI();
}
}