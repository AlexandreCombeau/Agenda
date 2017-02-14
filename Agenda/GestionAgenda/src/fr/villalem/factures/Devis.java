/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.factures;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Villalemons
 */
public class Devis {
    
    
    public static void creerDevis(){
        
        /*
        DEFINITION DES VARIABLES
        */
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Format");
        HSSFCellStyle cellStyle = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        HSSFFont fonte = null;
        
        /*
        REDIMENSSIONEMENT
        
        sheet.setDefaultColumnWidth(15);
        sheet.setDefaultRowHeightInPoints(22);
        */
        sheet.setColumnWidth(3, 14000);
        sheet.setColumnWidth(1, 3000);
        
        /*
        FUSION DES CELLULES
        */
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(6, 7, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 4, 6));
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 4, 6));
        
        /*
        DEFINITIONS DES FONTS ET CELLSTYLES
        */
        //Font 12
        HSSFFont fonte12 = wb.createFont();
        fonte12.setFontHeightInPoints((short)12);
        fonte12.setFontName("Courier New");
        HSSFCellStyle cellStyleFont12 = wb.createCellStyle();
        cellStyleFont12.setFont(fonte12);
        //Font 16 en gras
        HSSFFont fonte16Gras = wb.createFont();
        fonte16Gras.setFontHeightInPoints((short)16);
        fonte16Gras.setFontName("Courier New");
        fonte16Gras.setBold(true);
        HSSFCellStyle cellStyleFont16Gras = wb.createCellStyle();
        cellStyleFont16Gras.setFont(fonte16Gras);
        
        
        /***********************************************************************
                                    DEBUT DU DEVIS
        ***********************************************************************/
        
        // /!\ METTRE IMAGE LOGO /!\
        
        /*
        DEBUT LIGNES 2 - 3
        */
        
        row = sheet.createRow(1);
        cell = row.createCell(3);
        cell.setCellValue("Lemons Production");
        fonte = wb.createFont();
        fonte.setFontName("Courier New");
        fonte.setFontHeightInPoints((short)22);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(2);
        cell = row.createCell(3);
        cell.setCellValue("Société de production audiovisuelle");
        fonte = wb.createFont();
        fonte.setFontName("Courier New");
        fonte.setFontHeightInPoints((short)14);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        /*
        FIN LIGNES 2 - 3
        */
        
        /*
        DEBUT LIGNE 5 - 9
        */
        
        row = sheet.createRow(4);
        cell = row.createCell(1);
        cell.setCellValue("n° d'identification : 450 621 818 R.C.S");
        cell.setCellStyle(cellStyleFont12);
        
        row = sheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("TVA intracommunautaire : FR 37 450 621 818 921 C");
        cell.setCellStyle(cellStyleFont12);
        
        row = sheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("5 impasse Mousset 75012 PARIS");
        cell.setCellStyle(cellStyleFont12);
        
        cell = row.createCell(4);
        cell.setCellValue("DEVIS");
        //Font 14 gras + centré
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)15);
        fonte.setFontName("Courier New");
        fonte.setBold(true);
        fonte.setColor((short)HSSFColor.WHITE.index);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        
        row = sheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("01 45 44 42 99");
        cell.setCellStyle(cellStyleFont12);
        
        row = sheet.createRow(8);
        cell = row.createCell(1);
        cell.setCellValue("contact@villalemons.fr");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN LIGNE 5 - 9
        */
        
        /*
        BLOC LIGNES 7 - 13
        */
        
        for(int i = 6 ; i <= 12 ; i++){
            if(i > 8) row = sheet.createRow(i);
            for(int j = 4 ; j <= 6 ; j++){
                
            }
        }
        
        /*
        FIN BLOC LIGNES 7 - 13
        */
        
        /*
        DEBUT LIGNE 13
        */
        
        row = sheet.createRow(12);
        cell = row.createCell(1);
        cell.setCellValue("PARIS, le");
        fonte = wb.createFont();
        fonte.setFontName("Courier New");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        //--------------------------------------------------------------------//
        
        cell = row.createCell(2);
        cell.setCellValue("14 février 2017");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN LIGNE 13
        */
        
        
        
        try{
            
            FileOutputStream fileOut = new FileOutputStream("devis.xls");
            wb.write(fileOut);
            fileOut.close();
            
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
