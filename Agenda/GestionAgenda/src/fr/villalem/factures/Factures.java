/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.villalem.factures;

import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Villalemons
 */
public class Factures {
    
    public Factures(){
        
    }
    
    public static void creerFacture(){
    
        //Création des variables
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Format");
        HSSFCellStyle cellStyle = null;
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        HSSFFont fonte = null;
        
        //Redimensionnement des lignes et colonnes par défaut
        sheet.setDefaultColumnWidth(15);
        sheet.setDefaultRowHeightInPoints(22);
        
        /*
        DEBUT 1 LIGNE
        */
        
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)18);
        fonte.setFontName("Courier New");
        fonte.setBold(true);
        cell = row.createCell(0);
        cell.setCellValue("Lemons Production");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2)); // Fusionne les cellules 0;0 à 0;2
        
        cell = row.createCell(5);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor((short)8);
        cell.setCellStyle(cellStyle);
        
        /*
        FIN 1 LIGNE
        */
        
        /*
        DEBUT 2 LIGNE
        */
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)16);
        fonte.setFontName("Courier New");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        //cell.setCellValue("Société de production audiovisuelle");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBottomBorderColor((short)8); // 8 = couleur noire
        for (int i = 0; i <= 5; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            if (i == 0) {
                cell.setCellValue("Société de production audiovisuelle");
            }   
        }
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
        cell = row.createCell(5);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor((short)8);
        cell.setCellStyle(cellStyle);
        
        /*
        FIN 2 LIGNE
        */
        
        /*
        DEBUT 3 LIGNE
        */
        
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)12);
        fonte.setFontName("Courier New");
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("n° d'identification : 450 621 818 R.C.S");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
        
        /*
        FIN 3 LIGNE
        */
        
        /*
        DEBUT 4 LIGNE
        */
        
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)13);
        fonte.setFontName("Courier New");
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("TVA intracommunautaire : FR 37 450 621 818 921 C");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3));
        
        /*
        FIN 4 LIGNE
        */
        
        /*
        DEBUT 5 LIGNE
        */
        
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)13);
        fonte.setFontName("Courier New");
        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("5, impasse Mousset");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
        
        /*
        FIN LIGNE 5
        */
        
        /*
        DEBUT LIGNE 6
        */
        
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)13);
        fonte.setFontName("Courier New");
        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("75012 Paris");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        /*
        FIN LIGNE 6
        */
        
        /*
        DEBUT LIGNE 7
        */
        
        fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)13);
        fonte.setFontName("Courier New");
        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Sébastien AUTRET");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
        
        /*
        FIN LIGNE 7
        */
        
        /*
        DEBUT LIGNE 8
        */
        
        for(int i = 4 ; i <= 5 ; i++){
            for(int j = 7 ; j <= 10 ; j++){
                if(i == 4){
                    fonte = wb.createFont();
                    fonte.setFontHeightInPoints((short)16);
                    fonte.setFontName("Courier New");
                    fonte.setBold(true);
                    row = sheet.createRow(j);
                    cell = row.createCell(i);
                    cellStyle = wb.createCellStyle();
                    cellStyle.setFont(fonte);
                    cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyle.setLeftBorderColor((short)8);
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    if(j == 7){
                        fonte.setFontHeightInPoints((short)20);
                        cell.setCellValue("FACTURE");
                        cellStyle.setBorderTop(BorderStyle.MEDIUM);
                        cellStyle.setTopBorderColor((short)8);
                        sheet.addMergedRegion(new CellRangeAddress(7, 7, 4, 5));
                    }
                    else if(j == 8){
                        cell.setCellValue("Bird Office");
                        sheet.addMergedRegion(new CellRangeAddress(8, 8, 4, 5));
                    }
                    else if(j == 9){
                        cell.setCellValue("29 Bid Lannes");
                        sheet.addMergedRegion(new CellRangeAddress(9, 9, 4, 5));
                    }
                    else if(j == 10){
                        cell.setCellValue("75116 Paris");
                        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                        cellStyle.setBottomBorderColor((short)8);
                        cellStyle.setBorderRight(BorderStyle.MEDIUM);
                        cellStyle.setRightBorderColor((short)8);
                        sheet.addMergedRegion(new CellRangeAddress(10, 10, 4, 5));
                    }
                }
                /*else if(i == 5){
                    row = sheet.createRow(j);
                    cell = row.createCell(i);
                    cellStyle = wb.createCellStyle();
                    cellStyle.setBorderRight(BorderStyle.MEDIUM);
                    cellStyle.setRightBorderColor((short)8);
                    if(j == 7){
                        cellStyle.setBorderTop(BorderStyle.MEDIUM);
                        cellStyle.setTopBorderColor((short)8);
                    }
                    else if(j == 10){
                        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                        cellStyle.setBottomBorderColor((short)8);
                    }
                }*/
                cell.setCellStyle(cellStyle);
            }
        }
        
        /*fonte = wb.createFont();
        fonte.setFontHeightInPoints((short)13);
        fonte.setFontName("Courier New");
        row = sheet.createRow(4);
        cell = row.createCell(4);
        cell.setCellValue("Sébastien AUTRET");
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 1));*/
        
        /*
        FIN LIGNE 8
        */
        
        
        
        /*cell.setCellValue(10);
        row.createCell(1).setCellValue(30);
        row.createCell((short)2, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("ma valeur"));
        
        
        cell = row.createCell(3);
        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("SUM(A1:B1)");
        
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(1000);
        cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
        cell.setCellStyle(cellStyle);
        
        
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cellStyle = wb.createCellStyle();
        HSSFDataFormat hssfDataFormat = wb.createDataFormat();
        cellStyle.setDataFormat(hssfDataFormat.getFormat("dd/mm/yyyy h:mm"));
        cell.setCellStyle(cellStyle);*/
        
        try{
            
            FileOutputStream fileOut = new FileOutputStream("facture.xls");
            wb.write(fileOut);
            fileOut.close();
            
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
