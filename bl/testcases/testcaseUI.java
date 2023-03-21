import com.thinking.machines.hr.pl.exceptions.*;
import com.thinking.machines.hr.pl.*;
import java.awt.*;
import javax.swing.*;
class DesignationUI extends JFrame
{
private DesignationModel designationModel;
private JTable table;
private JScrollPane jsp;
private Container container;
public DesignationUI()
{
try
{
designationModel=new DesignationModel();
}catch(PLException plException)
{
System.out.println(plException.getMessage());
}
table=new JTable(designationModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.add(jsp);
int width=600;
int height=600;
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int x=(d.width/2)-(width/2);
int y=(d.height/2)-(height/2);
setLocation(x,y);
setSize(width,height);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}


}
class DesignationUIpsp
{
public static void main(String gg[])
{
DesignationUI designationUI=new DesignationUI();
}
}