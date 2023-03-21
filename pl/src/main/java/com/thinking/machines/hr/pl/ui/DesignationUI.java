package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.filechooser.*;
public class DesignationUI extends JFrame implements DocumentListener, ListSelectionListener
{
private JLabel titleLabel, searchLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JLabel errorLabel;
private DesignationModel designationModel;
private JTable designationTable;
private JScrollPane scrollPane;
private Container container;
private enum MODE{VIEW,ADD,EDIT,DELETE,EXPORT_TO_PDF};
private MODE mode;
private ImageIcon logoIcon;
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon deleteIcon;
private ImageIcon saveIcon;
private ImageIcon cancelIcon;
private ImageIcon pdfIcon;
private ImageIcon crossButtonIcon;
private DesignationPanel designationPanel;
public DesignationUI()
{
initComponents();
setAppearance();
addListeners();
setViewMode();
designationPanel.setViewMode();
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
private void initComponents()
{
logoIcon=new ImageIcon(this.getClass().getResource("/icons/logo.png"));
addIcon=new ImageIcon(this.getClass().getResource("/icons/add.png"));
editIcon=new ImageIcon(this.getClass().getResource("/icons/edit.png"));
saveIcon=new ImageIcon(this.getClass().getResource("/icons/save.png"));
deleteIcon=new ImageIcon(this.getClass().getResource("/icons/delete.png"));
pdfIcon=new ImageIcon(this.getClass().getResource("/icons/pdf.png"));
cancelIcon=new ImageIcon(this.getClass().getResource("/icons/cancel.png"));
crossButtonIcon=new ImageIcon(this.getClass().getResource("/icons/crossButton.png"));


titleLabel=new JLabel("Designations");
searchLabel=new JLabel("Search");
errorLabel=new JLabel("  ");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton(crossButtonIcon);
designationPanel=new DesignationPanel();
designationModel=new DesignationModel();
designationTable=new JTable(designationModel);
scrollPane=new JScrollPane(designationTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

setIconImage(logoIcon.getImage());
container=getContentPane();
}
private void setAppearance()
{
Font titleLabelFont=new Font("Verdana",Font.BOLD,18);
Font searchLabelFont=new Font("Verdana",Font.BOLD,16);
Font searchTextFieldFont=new Font("Verdana",Font.PLAIN,16);
Font errorLabelFont=new Font("Verdana",Font.PLAIN,12);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
Font columnHeaderFont=new Font("Verdana",Font.BOLD,16);
int lm=0;
int tm=0;
titleLabel.setBounds(lm+10,tm+10,200,40);
titleLabel.setFont(titleLabelFont);
searchLabel.setBounds(lm+10,tm+10+40+10,100,30);
searchLabel.setFont(searchLabelFont);
searchTextField.setBounds(lm+10+100+5,tm+10+10+30+10,400,30);
searchTextField.setFont(searchTextFieldFont);
clearSearchTextFieldButton.setBounds(lm+500+20,tm+10+10+30+10,30,30);
errorLabel.setBounds(lm+450,tm+30,100,30);
errorLabel.setFont(errorLabelFont);
errorLabel.setForeground(Color.red);
scrollPane.setBounds(lm+10,tm+10+10+30+10+40,550+10+5,300);
designationPanel.setBounds(lm+10,tm+10+40+10+30+10+300+10,565,200);
designationTable.setFont(dataFont);
designationTable.setRowHeight(35);
designationTable.setRowSelectionAllowed(true);
designationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
designationTable.getColumnModel().getColumn(0).setPreferredWidth(20);
designationTable.getColumnModel().getColumn(1).setPreferredWidth(400);
JTableHeader tableHeader=designationTable.getTableHeader();
tableHeader.setFont(columnHeaderFont);
tableHeader.setResizingAllowed(false);
tableHeader.setReorderingAllowed(false);
container.setLayout(null);
container.add(titleLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(clearSearchTextFieldButton);
container.add(errorLabel);
container.add(scrollPane);
container.add(designationPanel);
setIconImage(logoIcon.getImage());
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int w=600;
int h=660;
int x=(d.width/2)-(w/2);
int y=(d.height/2)-(h/2);
setLocation(x,y);
setSize(w,h);
}

private void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
clearSearchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
}
});
designationTable.getSelectionModel().addListSelectionListener(this);
}
private void searchTitle()
{
errorLabel.setText("");
String title=searchTextField.getText().trim();
if(title.length()==0) return;
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfTitle(title,true);
}catch(BLException blException)
{
errorLabel.setText("Not found");
return;
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
}
public void changedUpdate(DocumentEvent de)
{
searchTitle();
}
public void removeUpdate(DocumentEvent de)
{
searchTitle();
}
public void insertUpdate(DocumentEvent de)
{
searchTitle();
}
public void valueChanged(ListSelectionEvent e)
{
int rowIndex=this.designationTable.getSelectedRow();
try
{
DesignationInterface designation=this.designationModel.getDesignationAt(rowIndex);
this.designationPanel.setDesignation(designation);
}catch(BLException blException)
{
this.designationPanel.clearDesignation();
}

}
private void setViewMode()
{
mode=MODE.VIEW;
if(designationModel.getRowCount()==0)
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
designationTable.setEnabled(true);
}
}
private void setAddMode()
{
mode=MODE.ADD;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);

}
private void setEditMode()
{
mode=MODE.EDIT;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);

}
private void setDeleteMode()
{
mode=MODE.DELETE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);

}

private void setExportToPDFMode()
{
mode=MODE.EXPORT_TO_PDF;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}

// inner class starts 
private class DesignationPanel extends JPanel
{
private JLabel designationLabel;
private JLabel titleLabel;
private JTextField titleTextField;
private JButton clearTitleTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private DesignationInterface designation;
private JPanel buttonsPanel;
DesignationPanel()
{
setBorder(BorderFactory.createLineBorder(new Color(115,130,155)));
initComponents();
setAppearance();
addListeners();
}
private void initComponents()
{
designation=null;
designationLabel=new JLabel("Designation");
titleLabel=new JLabel("");
titleTextField=new JTextField();
clearTitleTextFieldButton=new JButton(crossButtonIcon);
addButton=new JButton(addIcon);
editButton=new JButton(editIcon);
cancelButton=new JButton(cancelIcon);
deleteButton=new JButton(deleteIcon);
exportToPDFButton=new JButton(pdfIcon);
buttonsPanel=new JPanel();
}
private void setAppearance()
{
Font designationLabelFont=new Font("Verdana",Font.BOLD,16);
Font titleLabelFont=new Font("Verdana",Font.PLAIN,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
designationLabel.setFont(designationLabelFont);
titleLabel.setFont(titleLabelFont);
titleTextField.setFont(dataFont);
int lm,tm;
lm=0;
tm=0;
designationLabel.setBounds(lm+10,tm+20,110,30);
titleLabel.setBounds(lm+110+30,tm+20,400,30);
titleTextField.setBounds(lm+10+110+5,tm+20,350,30);
clearTitleTextFieldButton.setBounds(lm+10+110+5+350+5,tm+20,30,30);
buttonsPanel.setLayout(null);
buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(115,130,155)));
buttonsPanel.setBounds(50,tm+40+10+20,465,75);
addButton.setBounds(70,12,50,50);
editButton.setBounds(70+50+20,12,50,50);
cancelButton.setBounds(70+50+20+50+20,12,50,50);
deleteButton.setBounds(70+50+20+50+20+50+20,12,50,50);
exportToPDFButton.setBounds(70+50+20+50+20+50+20+50+20,12,50,50);

buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(exportToPDFButton);

setLayout(null);
add(designationLabel);
add(titleLabel);
add(titleTextField);
add(clearTitleTextFieldButton);
add(buttonsPanel);
}
private boolean addDesignation()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation required");
titleTextField.requestFocus();
return false;
}
DesignationInterface d=new Designation();
d.setTitle(title);

try
{
designationModel.add(d);
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}

}
private boolean updateDesignation()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation required");
titleTextField.requestFocus();
return false;
}
DesignationInterface d=new Designation();
d.setCode(this.designation.getCode());
d.setTitle(title);
try
{
designationModel.update(d);
int rowIndex=0;
try
{
rowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
titleTextField.requestFocus();
return false;
}

}


private void removeDesignation()
{
try
{
String title=this.designation.getTitle();
int selectedOption=JOptionPane.showConfirmDialog(this,"Delete "+title+" ?","Confirmation",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.NO_OPTION) return;
designationModel.remove(this.designation.getCode());
JOptionPane.showMessageDialog(this,title+" removed");
//this.clearDesignation();
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
else
{
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
}
}

}
private void addListeners()
{
this.exportToPDFButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
JFileChooser jfc=new JFileChooser();
jfc.setAcceptAllFileFilterUsed(false);
jfc.setCurrentDirectory(new File("."));
jfc.addChoosableFileFilter(new javax.swing.filechooser.FileFilter(){
public boolean accept(File file)
{
if(file.isDirectory()) return true;
if(file.getName().endsWith(".pdf")) return true;
return false;
}
public String getDescription()
{
return "Pdf Files";
}
});

int selectedOption=jfc.showSaveDialog(DesignationUI.this);
if(selectedOption==jfc.APPROVE_OPTION)
{
try
{
File selectedFile=jfc.getSelectedFile();
String pdfFile=selectedFile.getAbsolutePath();
if(!(pdfFile.endsWith(".pdf"))) pdfFile+=".pdf";
else if(pdfFile.endsWith(".")) pdfFile+="pdf";
File file=new File(pdfFile);
File parent=new File(file.getParent());
if(parent.exists()==false || parent.isDirectory()==false)
{
JOptionPane.showMessageDialog(DesignationUI.this,"Incorrect path : "+file.getAbsolutePath());
return;
}
designationModel.exportToPDF(file);
JOptionPane.showMessageDialog(DesignationUI.this,"Data exported to : "+file.getAbsolutePath());

}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(DesignationUI.this,blException.getGenericException());
}
}
catch(Exception exception)
{
System.out.println(exception);
}
}
}
});
addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setAddMode();
}
else
{
if(addDesignation())
{
setViewMode();
}

}
}
});
editButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setEditMode();
}
else
{
if(updateDesignation())
{
setViewMode();
}

}
}
});
cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setViewMode();
}
});
deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setDeleteMode();
}
});
}

public void setDesignation(DesignationInterface designation)
{
this.designation=designation;
this.titleLabel.setText(this.designation.getTitle());
}
public void clearDesignation()
{
this.titleLabel.setText("");
this.designation=null;
}

void setViewMode()
{
DesignationUI.this.setViewMode();
titleLabel.setVisible(true);
titleTextField.setText("");
titleTextField.setVisible(false);
clearTitleTextFieldButton.setVisible(false);
addButton.setEnabled(true);
addButton.setIcon(addIcon);
editButton.setIcon(editIcon);
cancelButton.setEnabled(false);
if(DesignationUI.this.designationModel.getRowCount()>0)
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}
else
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
}
void setAddMode()
{
DesignationUI.this.setAddMode();
titleLabel.setVisible(false);
titleTextField.setText("");
titleTextField.setVisible(true);
clearTitleTextFieldButton.setVisible(true);
addButton.setIcon(saveIcon);
cancelButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
void setEditMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>=designationModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Selete designation to edit");
return;
}
DesignationUI.this.setEditMode();
titleLabel.setVisible(false);
titleTextField.setText(this.designation.getTitle());
titleTextField.setVisible(true);
titleTextField.requestFocus();
clearTitleTextFieldButton.setVisible(true);
editButton.setIcon(saveIcon);
cancelButton.setEnabled(true);
addButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}

void setDeleteMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>=DesignationUI.this.designationModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Selete designation to delete");
return;
}
DesignationUI.this.setDeleteMode();
titleLabel.setVisible(true);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
addButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
removeDesignation();
DesignationUI.this.setViewMode();
setViewMode();
}
void setExportToPDF()
{
DesignationUI.this.setExportToPDFMode();
editButton.setEnabled(false);
cancelButton.setEnabled(false);
addButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}

}

// inner class ends
}