package com.thinking.machines.hr.pl.model;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.*;
import com.itextpdf.io.font.constants.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.layout.property.*;
import com.itextpdf.layout.borders.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;
public class DesignationModel extends AbstractTableModel
{
private DesignationManagerInterface designationManager;
private java.util.List<DesignationInterface> designations;
private String title[];
public DesignationModel()
{
populateDataStructure();
System.out.println("Constructor");
}
private void populateDataStructure()
{
System.out.println("*******populateDataStructure*****");
title=new String[2];
title[0]="S.No.";
title[1]="Designation";
designations=new LinkedList<>();
try
{

designationManager=DesignationManager.getDesignationManager();

}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
System.out.println("Hi");
Set<DesignationInterface> blDesignations=designationManager.getDesignations();
System.out.println("Hello");
for(DesignationInterface designation:blDesignations)
{
designations.add(designation);
}
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left, DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
}
public int getColumnCount()
{
return title.length;
}
public int getRowCount()
{
return designations.size();
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;
return String.class;
}
public Object getValueAt(int rowIndex, int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
return designations.get(rowIndex).getTitle();
}
public boolean isCellEditable(int rowIndex, int columnIndex)
{
return false;
}
// Application Specific Methods
public void add(DesignationInterface designation) throws BLException
{
this.designationManager.addDesignation(designation);
this.designations.add(designation);
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left, DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public void update(DesignationInterface designation) throws BLException
{
this.designationManager.updateDesignation(designation);
this.designations.remove(indexOfDesignation(designation));
this.designations.add(designation);
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left, DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public void remove(int code) throws BLException
{
this.designationManager.removeDesignation(code);
Iterator<DesignationInterface> iterator=this.designations.iterator();
int index=0;
while(iterator.hasNext())
{
if(iterator.next().getCode()==code) break;
index++;
}
if(index==this.designations.size())
{
BLException blException=new BLException();
blException.setGenericException("Invalid designation code : "+code);
throw blException;
}
this.designations.remove(index);
fireTableDataChanged();
}
public int indexOfDesignation(DesignationInterface designation) throws BLException
{
Iterator<DesignationInterface> iterator;
iterator=this.designations.iterator();
DesignationInterface d;
int index=0;
while(iterator.hasNext())
{
d=iterator.next();
if(designation.equals(d))
{
return index;
}
index++;
}

BLException blException=new BLException();
blException.setGenericException("Invalid designation : "+designation.getTitle());
throw blException;

}

public int indexOfTitle(String title, boolean partialLeftSearch) throws BLException
{
Iterator<DesignationInterface> iterator;
iterator=designations.iterator();
DesignationInterface d;
int index=0;
while(iterator.hasNext())
{
d=iterator.next();
if(partialLeftSearch==true)
{
if(d.getTitle().toUpperCase().startsWith(title.toUpperCase())) return index;
}
else if(d.getTitle().equalsIgnoreCase(title)) return index;
index++;
}

BLException blException=new BLException();
blException.setGenericException("Invalid title : "+title);
throw blException;
}
public DesignationInterface getDesignationAt(int index) throws BLException
{
if(index<0 || index>=this.designations.size())
{
BLException blException=new BLException();
blException.setGenericException("Invalid index : "+index);
throw blException;
}
return this.designations.get(index);
}
public void exportToPDF(File file) throws BLException
{
try
{
if(file.exists()) file.delete();
PdfWriter pdfWriter=new PdfWriter(file);
PdfDocument pdfDocument=new PdfDocument(pdfWriter);
Document document=new Document(pdfDocument);
Image logo=new Image(ImageDataFactory.create(this.getClass().getResource("/icons/logo.png")));
Paragraph logoPara=new Paragraph();
logoPara.add(logo);
Paragraph companyNamePara=new Paragraph();
companyNamePara.add("ABCD Corporation");
PdfFont companyNameFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
companyNamePara.setFont(companyNameFont);
companyNamePara.setFontSize(18);
Paragraph reportTitlePara=new Paragraph("List of designations");
PdfFont reportTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
reportTitlePara.setFont(reportTitleFont);
reportTitlePara.setFontSize(15);
PdfFont columnTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont dataFont=PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
PdfFont pageNumberFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);Paragraph columnTitle1=new Paragraph("S.No.");
columnTitle1.setFont(columnTitleFont);
columnTitle1.setFontSize(14);
Paragraph columnTitle2=new Paragraph("Designation");
columnTitle2.setFont(columnTitleFont);
columnTitle2.setFontSize(14);
Paragraph pageNumberParagraph;
Paragraph dataParagraph;
float topTableColumnWidths[]={1,5};
float dataTableColumnWidths[]={1,5};
int sno,x,pageSize;
pageSize=5;
sno=0;
x=0;
boolean newPage=true;
Table topTable;
Table dataTable=null;
Table pageNumberTable;
Cell cell;
int pageNumber=0;
int numberOfPages=this.designations.size()/pageSize;
if(this.designations.size()%pageSize!=0) numberOfPages++;
DesignationInterface designation;
while(x<this.designations.size())
{
if(newPage==true)
{
// create new page header
pageNumber++;
topTable=new Table(UnitValue.createPercentArray(topTableColumnWidths));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(logoPara);
topTable.addCell(cell);
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(companyNamePara);
cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
topTable.addCell(cell);
document.add(topTable);
pageNumberParagraph=new Paragraph();
pageNumberParagraph.add("Page : "+pageNumber+"/"+numberOfPages);
pageNumberParagraph.setFont(pageNumberFont);
pageNumberParagraph.setFontSize(13);
pageNumberTable=new Table(1);
pageNumberTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(pageNumberParagraph);
cell.setTextAlignment(TextAlignment.RIGHT);
pageNumberTable.addCell(cell);
document.add(pageNumberTable);
dataTable=new Table(UnitValue.createPercentArray(dataTableColumnWidths));
dataTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell(1,2);
cell.add(reportTitlePara);
cell.setTextAlignment(TextAlignment.CENTER);
dataTable.addHeaderCell(cell);
dataTable.addHeaderCell(columnTitle1);
dataTable.addHeaderCell(columnTitle2);
newPage=false;
}
designation=this.designations.get(x);
// add row to table
sno++;
cell=new Cell();
dataParagraph=new Paragraph(String.valueOf(sno));
dataParagraph.setFont(dataFont);
dataParagraph.setFontSize(14);
cell.add(dataParagraph);
cell.setTextAlignment(TextAlignment.RIGHT);
dataTable.addCell(cell);
cell=new Cell();
dataParagraph=new Paragraph(designation.getTitle());
dataParagraph.setFont(dataFont);
dataParagraph.setFontSize(14);
cell.add(dataParagraph);
dataTable.addCell(cell);


x++;
if(sno%pageSize==0 || x==this.designations.size())
{
// create footer
document.add(dataTable);
document.add(new Paragraph("Software by : Thinking Machines"));

if(x<this.designations.size())
{
// add new page to document
document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
newPage=true;
}
}
}
document.close();
}catch(Exception exception)
{
BLException blException=new BLException();
blException.setGenericException(exception.getMessage());
throw blException;
}
}
}