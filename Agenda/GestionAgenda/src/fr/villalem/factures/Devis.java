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
import org.apache.poi.ss.usermodel.BorderStyle;
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
        sheet.setColumnWidth(0, 500);
        sheet.setColumnWidth(3, 11000);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 2200);
        sheet.setColumnWidth(4, 3500);
        sheet.setColumnWidth(5, 3500);
        sheet.setColumnWidth(6, 3500);
        
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
        sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(27, 27, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(36, 36, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(21, 21, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(51, 51, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(25, 25, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(26, 26, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(18, 18, 2, 3));
        
        
        /*
        DEFINITIONS DES FONTS ET CELLSTYLES
        */
        //Bordure bas
        HSSFCellStyle cellStyleBottom = wb.createCellStyle();
        cellStyleBottom.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleBottom.setBottomBorderColor((short)8);
        //Bordure left
        HSSFCellStyle cellStyleLeft = wb.createCellStyle();
        cellStyleLeft.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleLeft.setLeftBorderColor((short)8);
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
        fonte.setFontHeightInPoints((short)20);
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
        fonte.setFontHeightInPoints((short)12);
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
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("TVA intracommunautaire : FR 37 450 621 818 921 C");
        cell.setCellStyle(cellStyleFont12);
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("5 impasse Mousset 75012 PARIS");
        cell.setCellStyle(cellStyleFont12);
        
        //--------------------------------------------------------------------//
        
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
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("01 45 44 42 99");
        cell.setCellStyle(cellStyleFont12);
        
        //--------------------------------------------------------------------//
        
        
        HSSFRow row8 = sheet.createRow(8);
        HSSFRow row9 = sheet.createRow(9);
        HSSFRow row10 = sheet.createRow(10);
        HSSFRow row11 = sheet.createRow(11);
        HSSFRow row12 = sheet.createRow(12);
        HSSFRow[] lesRows = new HSSFRow[5];
        lesRows[0] = row8;
        lesRows[1] = row9;
        lesRows[2] = row10;
        lesRows[3] = row11;
        lesRows[4] = row12;
        
        cell = lesRows[0].createCell(1);
        cell.setCellValue("contact@villalemons.fr");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN LIGNE 5 - 9
        */
        
        /*
        BLOC LIGNES 7 - 13
        */
        
        for(int i = 8 ; i <= 12 ; i++){  
            for(int j = 4 ; j <= 6 ; j++){
                cell = lesRows[i - 8].createCell(j);
                if(i == 8 && j == 4){
                    cell.setCellValue("CLIENT");
                    fonte = wb.createFont();
                    fonte.setFontHeightInPoints((short)15);
                    fonte.setFontName("Courier New");
                    fonte.setBold(true);
                    cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyle.setBorderRight(BorderStyle.MEDIUM);
                    cellStyle.setFont(fonte);
                    cell.setCellStyle(cellStyle);
                }
                if(i == 9 && j == 5 || i == 12 && j == 5){
                    cell.setCellStyle(cellStyleBottom);
                }
                if(i == 9 && j == 4 || i == 12 && j == 4){
                    cellStyle = wb.createCellStyle();
                    if(i == 12){
                        cell.setCellValue("TEL");
                        fonte = wb.createFont();
                        fonte.setFontHeightInPoints((short)13);
                        fonte.setFontName("Courier New");
                        cellStyle.setFont(fonte);
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    }
                    cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                    cell.setCellStyle(cellStyle);
                }
                if(i == 9 && j == 6 || i == 12 && j == 6){
                    cellStyle = wb.createCellStyle();
                    cellStyle.setBorderRight(BorderStyle.MEDIUM);
                    cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                    cell.setCellStyle(cellStyle);
                }
                if(i == 10 && j == 4 || i == 11 && j == 4){
                    if(i == 10){
                        cell.setCellValue("Nom du contact");
                    }
                    else{
                        cell.setCellValue("Mail");
                    }
                    fonte = wb.createFont();
                    fonte.setFontHeightInPoints((short)13);
                    fonte.setFontName("Courier New");
                    cellStyle = wb.createCellStyle();
                    cellStyle.setAlignment(HorizontalAlignment.CENTER);
                    cellStyle.setBorderLeft(BorderStyle.MEDIUM);
                    cellStyle.setFont(fonte);
                    cell.setCellStyle(cellStyle);
                    
                }
                if(i == 8 && j == 6 || i == 10 && j == 6 || i == 11 && j == 6){
                    cellStyle = wb.createCellStyle();
                    cellStyle.setBorderRight(BorderStyle.MEDIUM);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
        
        /*
        FIN BLOC LIGNES 7 - 13
        */
        
        /*
        DEBUT LIGNE 13
        */
        
        cell = lesRows[4].createCell(1);
        cell.setCellValue("PARIS, le");
        fonte = wb.createFont();
        fonte.setFontName("Courier New");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        //--------------------------------------------------------------------//
        
        cell = lesRows[4].createCell(2);
        cell.setCellValue("14 février 2017");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN LIGNE 13
        */
        
        /*
        DEBUT CREATION DES LIGNES POUR LE GROS BLOC
        */
        
        HSSFRow[] lesLignes = new HSSFRow[39];
        for(int i = 0 ; i < lesLignes.length ; i++){
            lesLignes[i] = sheet.createRow(i + 14);
        }
        
        
        /*
        FIN CREATION DES LIGNES POUR LE GROS BLOC
        */
        
        /*
        DEBUT BLOC CENTRAL
        */
        HSSFFont fonte14Gras = wb.createFont();
        fonte14Gras.setFontHeightInPoints((short)14);
        fonte14Gras.setFontName("Courier New");
        fonte14Gras.setBold(true);
        HSSFCellStyle cellStyleFont14Gras = wb.createCellStyle();
        cellStyleFont14Gras.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont14Gras.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleFont14Gras.setFont(fonte14Gras);
        
        HSSFFont fonte13Gras = wb.createFont();
        fonte13Gras.setFontHeightInPoints((short)13);
        fonte13Gras.setFontName("Courier New");
        fonte13Gras.setBold(true);
        HSSFCellStyle cellStyleFont13Gras = wb.createCellStyle();
        cellStyleFont13Gras.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont13Gras.setFont(fonte13Gras);
        
        HSSFFont fonte13 = wb.createFont();
        fonte13.setFontHeightInPoints((short)13);
        fonte13.setFontName("Courier New");
        HSSFCellStyle cellStyleFont13 = wb.createCellStyle();
        cellStyleFont13.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont13.setFont(fonte13);
        
        HSSFFont fonte11 = wb.createFont();
        fonte11.setFontHeightInPoints((short)10);
        fonte11.setFontName("Courier New");
        HSSFCellStyle cellStyleFont11 = wb.createCellStyle();
        cellStyleFont11.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont11.setFont(fonte11);
        
        HSSFFont fonte13CT = wb.createFont();
        fonte13CT.setFontHeightInPoints((short)13);
        fonte13CT.setFontName("Courier New");
        HSSFCellStyle cellStyleFont13CT = wb.createCellStyle();
        cellStyleFont13CT.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont13CT.setAlignment(HorizontalAlignment.CENTER);
        cellStyleFont13CT.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleFont13CT.setFont(fonte13CT);
        
        HSSFFont fonte13HCT = wb.createFont();
        fonte13CT.setFontHeightInPoints((short)13);
        fonte13CT.setFontName("Courier New");
        HSSFCellStyle cellStyleFont13HCT = wb.createCellStyle();
        cellStyleFont13HCT.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleFont13HCT.setFont(fonte13HCT);
        
        for(int i = 14 ; i <= 52 ; i++){
            for(int j = 1 ; j <= 6 ; j++){
                cell = lesLignes[i - 14].createCell(j);
                if(j == 1){
                    if(i == 16 || i == 27 || i == 36){
                        switch(i){
                            case 16: cell.setCellValue("Espaces"); break;
                            case 27: cell.setCellValue("Equipements"); break;
                            case 36: cell.setCellValue("Services"); break;
                        }
                        cell.setCellStyle(cellStyleFont14Gras);
                    }
                    if(i == 18 || i == 22 || i == 38 || i == 40 || i == 42 || i == 44 || i == 46 || i == 48 || i == 51){
                        switch(i){
                            case 18: cell.setCellValue("La Cabane"); break;
                            case 38: cell.setCellValue("Accueil petit déjeuner"); break;
                            case 40: cell.setCellValue("Pause café"); break;
                            case 42: cell.setCellValue("Pause café gourmand"); break;
                            case 44: cell.setCellValue("Plateaux repas"); break;
                            case 46: cell.setCellValue("Afterwork"); break;
                            case 48: cell.setCellValue("Bloc-notes et stylos"); break;
                        }
                        cell.setCellStyle(cellStyleFont13Gras);
                    }
                    if(i == 19 || i == 39 || i == 41 || i == 43 || i == 45 || i == 47 || i == 49){
                        switch(i){
                            case 19: cell.setCellValue("Salle de 80 m² et espace bar de 25m²"); break;
                            case 39: cell.setCellValue("Thé, café, jus de fruit, eau et viennoiseries"); break;
                            case 41: cell.setCellValue("Thé, café, eau"); break;
                            case 43: cell.setCellValue("Thé, café, eau, petits fours sucrés"); break;
                            case 45: cell.setCellValue("Entrée, plat et dessert. 21 menus au choix, softs et vins sur commande"); break;
                            case 47: cell.setCellValue("10 pièces/personne boissons comprises"); break;
                            case 49: cell.setCellValue("Bloc A5 et stylo pour chaque participant"); break;
                        }
                        cell.setCellStyle(cellStyleFont11);
                    }
                    if(i == 26 || i == 29 || i == 30 || i == 31 || i == 32 || i == 33 || i == 34){
                        switch(i){
                            case 29: cell.setCellValue("Chaises"); break;
                            case 30: cell.setCellValue("Tables"); break;
                            case 31: cell.setCellValue("Paperboard"); break;
                        }
                        cell.setCellStyle(cellStyleFont13);
                    }
                    if(i == 20 || i == 21){
                        switch(i){
                            case 20: cell.setCellValue("Participants :"); break;
                            case 21: cell.setCellValue("1 février 2017"); break;
                        }
                        cell.setCellStyle(cellStyleFont13CT);
                    }
                    if(i == 15 || i == 17 || i == 23 || i == 24 || i == 25 || i == 28 || i == 35 || i == 37 || i == 50){
                        cell.setCellStyle(cellStyleLeft);
                    }
                    if(i == 52){
                        cell.setCellStyle(cellStyleFont14Gras);
                    }
                }
                else if(j == 2){
                    if(i == 16 || i == 27 || i == 36 || i == 52){
                        cell.setCellStyle(cellStyleBottom);
                    }
                    if(i == 18 || i == 20){
                        switch(i){
                            case 18: cell.setCellValue("Forfait Journée (8h)"); break;
                            case 20: cell.setCellValue("20"); break;
                        }
                        cell.setCellStyle(cellStyleFont13HCT);
                    }
                }
                else if(j == 3){
                    if(i == 16 || i == 27 || i == 36 || i == 52){
                        cell.setCellStyle(cellStyleBottom);
                    }
                    if(i == 21){
                        cell.setCellValue("9h à 17h");
                        cell.setCellStyle(cellStyleFont13HCT);
                    }
                }
            }
        }
        
        /*
        FIN BLOC CENTRAL
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
