import java.awt.*;
import javax.swing.*;
import com.thinking.machines.hr.pl.model.*;
public class DesignationModelTestCase extends JFrame
{
private DesignationModel designationModel;
private JTable table;
private JScrollPane jsp;
private Container container;
public DesignationModelTestCase()
{
designationModel=new DesignationModel();
table=new JTable(designationModel);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
 container.add(jsp);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int width=600;
int height=600;
int x=(d.width/2)-(width/2);
int y=(d.height/2)-(height/2);
setLocation(x,y);
setSize(width,height);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);

}

public static void main(String gg[])
{
DesignationModelTestCase dmtc=new DesignationModelTestCase();
}


}

