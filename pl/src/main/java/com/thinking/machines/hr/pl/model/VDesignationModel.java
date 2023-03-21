package com.thinking.machines.hr.pl.model;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import javax.swing.table.*;
import java.util.*;
public class VDesignationModel extends AbstractTableModel
{
private List<DesignationInterface> designations;
private String title[];
private DesignationManagerInterface designationManager;
public VDesignationModel()
{
populateDataStructure();
}


private void populateDataStructure()
{
title=new String[2];
title[0]="S.No.";
title[1]="Designation";
try
{
designationManager=DesignationManager.getDesignationManager();

}catch(BLException blException)
{

}
Set<DesignationInterface> blDesignations=designationManager.getDesignations();
designations=new LinkedList<>();
for(DesignationInterface designation:blDesignations)
{
designations.add(designation);
}
Collections.sort(designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left, DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
}
public int getRowCount()
{
return designations.size();
}
public int getColumnCount()
{
return title.length;
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;
return String.class;
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public boolean isCellEditable(int rowIndex, int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex, int columnIndex) 
{
if(columnIndex==0) return rowIndex+1;
return designations.get(rowIndex).getTitle();
}
}