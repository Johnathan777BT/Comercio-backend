package net.johnathan.report;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import net.johnathan.DTO.ComercianteReportDTO;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class PdfUtils {
	
	public static ByteArrayOutputStream generatePdfStream(List<ComercianteReportDTO>  queryResults) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();        // Write column names
     
        
        PdfPTable table = new PdfPTable(8);         
        table.setWidthPercentage(95);
  
        PdfPCell cell22 = new PdfPCell(new Phrase("ID"));
        table.addCell(cell22);

        PdfPCell cell23 = new PdfPCell(new Phrase("Nombre"));                
        table.addCell(cell23);

        PdfPCell cell24 = new PdfPCell(new Phrase("Departamento"));        
        table.addCell(cell24);

        PdfPCell cell25 = new PdfPCell(new Phrase("Municipio"));        
        table.addCell(cell25);

        PdfPCell cell26 = new PdfPCell(new Phrase("Estado"));        
        table.addCell(cell26);

        PdfPCell cell27 = new PdfPCell(new Phrase("Ingresos"));        
        table.addCell(cell27);      

        PdfPCell cell29 = new PdfPCell(new Phrase("Empleados"));        
        table.addCell(cell29); 
        
        PdfPCell cell30 = new PdfPCell(new Phrase("Nro Est"));        
        table.addCell(cell30); 

      
        
        document.add(new Paragraph("\n"));        // Write data rows
        for (ComercianteReportDTO comer : queryResults) {
          
        
        	 PdfPCell cell = new PdfPCell(new Phrase(comer.getId_com().toString()));        
             table.addCell(cell); 
             
               cell = new PdfPCell(new Phrase(comer.getNombre().toString()));        
             table.addCell(cell); 
              

             cell = new PdfPCell(new Phrase(comer.getNombre_departamento().toString()));        
             table.addCell(cell);
             
             cell = new PdfPCell(new Phrase(comer.getNombre_municipio().toString()));        
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(comer.isEstado().toString()));        
             table.addCell(cell);
             
             cell = new PdfPCell(new Phrase(comer.getIngresos().toString()));        
             table.addCell(cell);
             
             cell = new PdfPCell(new Phrase(comer.getNro_empleados().toString()));        
             table.addCell(cell);
             
             cell = new PdfPCell(new Phrase(comer.getNro_establecimiento().toString()));        
             table.addCell(cell);
            
        } 
        
        document.add(table);
        document.close();
        return outputStream;
    }

}
