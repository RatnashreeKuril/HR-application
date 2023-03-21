import java.awt.*;
import javax.swing.*;
import com.thinking.machines.hr.pl.model.*;
public class VDesignationModelTestCase extends JFrame
{
private VDesignationModel designationModel;
private JTable table;
private JScrollPane jsp;
private Container container;
public VDesignationModelTestCase()
{
designationModel=new VDesignationModel();
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
VDesignationModelTestCase dmtc=new VDesignationModelTestCase();
}


}

