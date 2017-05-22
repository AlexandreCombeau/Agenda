/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factures;

import static admin.ITgenerationDevis.round;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

/**
 *
 * @author Villalemons
 */
public class Devis {
    
    
    public static void creerDevis(String[] fichier, String[] client, String[] salle1, String[] salle2, String[] equipements, String[] services, String[] commentaires, String[] nbOptions, int[] nbServices, double[] tarifs, double[] prixServices, int[] nbFormules, double[] tvaServices) throws FileNotFoundException, IOException{
        
        /*
        DEFINITION DES VARIABLES
        */
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Format");
        HSSFCellStyle cellStyle = null;
        HSSFRow row = null;
        HSSFCell cell = null;
        HSSFFont fonte = null;
        
        //LA DATE DU JOUR
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        
        //PRIX FINAL HT
        double salle1FINAL = tarifs[0]*nbFormules[0];
        double salle2FINAL = 0;
        if(!(salle2[0].equals(""))) salle2FINAL = tarifs[1] * nbFormules[1];
        double service1FINAL = prixServices[0] * nbServices[0];
        double service2FINAL = prixServices[1] * nbServices[1];
        double service3FINAL = prixServices[2] * nbServices[2];
        double service4FINAL = prixServices[3] * nbServices[3];
        double service5FINAL = prixServices[4] * nbServices[4];
        double service6FINAL = prixServices[5] * nbServices[5];
        
        double prixFinalHT = salle1FINAL + salle2FINAL + service1FINAL + service2FINAL + service3FINAL + service4FINAL + service5FINAL + service6FINAL; //PRIX FINAL DU DEVIS HT
        double tvaFinal = tvaServices[0] + tvaServices[1] + tvaServices[2] + tarifs[0] * nbFormules[0] * 0.2;
        if(!(salle2[0].equals(""))) tvaFinal += tarifs[1] * nbFormules[1] * 0.2; //SI IL EXISTE UNE DEUXIEME SALLE ON RAJOUTE LA TVA DE CELLE CI
        double prixFinalTTC = prixFinalHT + tvaFinal;
        /*
        REDIMENSSIONEMENT
        
        sheet.setDefaultColumnWidth(15);
        sheet.setDefaultRowHeightInPoints(22);
        */
        sheet.setColumnWidth(0, 500);
        sheet.setColumnWidth(3, 9500);
        sheet.setColumnWidth(1, 5000);
        sheet.setColumnWidth(2, 2200);
        sheet.setColumnWidth(4, 2800);
        sheet.setColumnWidth(5, 6300);
        sheet.setColumnWidth(6, 3800);
        
        /*
        FUSION DES CELLULES
        */
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 3, 1, 1));
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
        sheet.addMergedRegion(new CellRangeAddress(23, 23, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(36, 36, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(21, 21, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(51, 51, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(25, 25, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(26, 26, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(18, 18, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(22, 22, 2, 3));
        sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(55, 55, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(56, 56, 1, 2));
        sheet.addMergedRegion(new CellRangeAddress(57, 57, 1, 3));
        
        
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
        fonte12.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont12 = wb.createCellStyle();
        cellStyleFont12.setFont(fonte12);
        //Font 16 en gras
        HSSFFont fonte16Gras = wb.createFont();
        fonte16Gras.setFontHeightInPoints((short)16);
        fonte16Gras.setFontName("Calibri (Corps)");
        fonte16Gras.setBold(true);
        HSSFCellStyle cellStyleFont16Gras = wb.createCellStyle();
        cellStyleFont16Gras.setFont(fonte16Gras);
        
        
        /***********************************************************************
                                    DEBUT DU DEVIS
        ***********************************************************************/
        
        // /!\ METTRE IMAGE LOGO /!\
        
        InputStream inputStream = new FileInputStream("images/lemons.png");
        byte[] bytes = IOUtils.toByteArray(inputStream);
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        inputStream.close();
        CreationHelper helper = wb.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(1);
        anchor.setRow1(1);
        anchor.setDx1(175);
        Picture pict = drawing.createPicture(anchor, pictureIdx);
        pict.resize(0.9 , 3);
        
        /*
        DEBUT LIGNES 2 - 3
        */
        
        row = sheet.createRow(1);
        cell = row.createCell(2);
        cell.setCellValue("Lemons Production");
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)22);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        //cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(2);
        cell = row.createCell(2);
        cell.setCellValue("Société de production audiovisuelle");
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        //cellStyle.setAlignment(HorizontalAlignment.CENTER);
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
        fonte.setFontName("Calibri (Corps)");
        fonte.setBold(true);
        fonte.setColor((short)HSSFColor.WHITE.index);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(6);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cell.setCellStyle(cellStyle);
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("01 45 44 42 99");
        cell.setCellStyle(cellStyleFont12);
        
        cell = row.createCell(4);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(6);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cell.setCellStyle(cellStyle);
        
        
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
        
        lesRows[1].setHeight((short)1500);
        
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
                    cell.setCellValue(client[0]+"\n"+client[1]+"\n"+client[2]); //ON AJOUTE L'ADRESSE DE FACTURATION A L'EMPLACEMENT "CLIENT" TROUVER MOYEN METTRE A LA LIGNE
                    fonte = wb.createFont();
                    fonte.setFontHeightInPoints((short)15);
                    fonte.setFontName("Calibri (Corps)");
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
                        cell.setCellValue(client[5]); //Prend le tel du contact en paramètre
                        fonte = wb.createFont();
                        fonte.setFontHeightInPoints((short)13);
                        fonte.setFontName("Calibri (Corps)");
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
                        cell.setCellValue(client[3]); //Prend le nom du contact en paramètre
                    }
                    else{
                        cell.setCellValue(client[4]); //Prend l'email en paramètre
                    }
                    fonte = wb.createFont();
                    fonte.setFontHeightInPoints((short)13);
                    fonte.setFontName("Calibri (Corps)");
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
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        
        //--------------------------------------------------------------------//
        
        cell = lesRows[4].createCell(2);
        formater = new SimpleDateFormat("dd MMMM yyyy");
        cell.setCellValue(formater.format(aujourdhui));
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
        fonte14Gras.setFontName("Calibri (Corps)");
        fonte14Gras.setBold(true);
        HSSFCellStyle cellStyleFont14Gras = wb.createCellStyle();
        cellStyleFont14Gras.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont14Gras.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleFont14Gras.setFont(fonte14Gras);
        
        HSSFFont fonte13Gras = wb.createFont();
        fonte13Gras.setFontHeightInPoints((short)13);
        fonte13Gras.setFontName("Calibri (Corps)");
        fonte13Gras.setBold(true);
        HSSFCellStyle cellStyleFont13Gras = wb.createCellStyle();
        cellStyleFont13Gras.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont13Gras.setFont(fonte13Gras);
        
        HSSFFont fonte13 = wb.createFont();
        fonte13.setFontHeightInPoints((short)13);
        fonte13.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont13 = wb.createCellStyle();
        cellStyleFont13.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont13.setFont(fonte13);
        
        HSSFFont fonte12left = wb.createFont();
        fonte12left.setFontHeightInPoints((short)12);
        fonte12left.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont12left = wb.createCellStyle();
        cellStyleFont12left.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont12left.setFont(fonte12left);
        
        HSSFFont fonte11 = wb.createFont();
        fonte11.setFontHeightInPoints((short)10);
        fonte11.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont11 = wb.createCellStyle();
        cellStyleFont11.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont11.setFont(fonte11);
        
        HSSFFont fonte12CT = wb.createFont();
        fonte12CT.setFontHeightInPoints((short)12);
        fonte12CT.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont12CT = wb.createCellStyle();
        cellStyleFont12CT.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleFont12CT.setAlignment(HorizontalAlignment.CENTER);
        cellStyleFont12CT.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleFont12CT.setFont(fonte12CT);
        
        HSSFFont fonte12CTsansBordures = wb.createFont();
        fonte12CTsansBordures.setFontHeightInPoints((short)12);
        fonte12CTsansBordures.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont12CTsansBordures = wb.createCellStyle();
        cellStyleFont12CTsansBordures.setAlignment(HorizontalAlignment.CENTER);
        cellStyleFont12CTsansBordures.setFont(fonte12CTsansBordures);
        
        HSSFFont fonte12HCT = wb.createFont();
        fonte12HCT.setFontHeightInPoints((short)12);
        fonte12HCT.setFontName("Calibri (Corps)");
        HSSFCellStyle cellStyleFont12HCT = wb.createCellStyle();
        cellStyleFont12HCT.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleFont12HCT.setFont(fonte12HCT);
        
        HSSFFont fonte13ET = wb.createFont();
        fonte13ET.setFontHeightInPoints((short)13);
        fonte13ET.setFontName("Calibri (Corps)");
        fonte13ET.setBold(true);
        fonte13ET.setColor((short)HSSFColor.WHITE.index);
        HSSFCellStyle cellStyle13ET = wb.createCellStyle();
        cellStyle13ET.setAlignment(HorizontalAlignment.CENTER);
        cellStyle13ET.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle13ET.setBorderRight(BorderStyle.MEDIUM);
        cellStyle13ET.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle13ET.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle13ET.setFont(fonte13ET);
        
        for(int i = 14 ; i <= 52 ; i++){
            for(int j = 1 ; j <= 7 ; j++){
                cell = lesLignes[i - 14].createCell(j);
                if(j == 1){
                    if(i == 14){
                        cell.setCellValue("Désignation");
                        cell.setCellStyle(cellStyle13ET);
                    }
                    else if(i == 16 || i == 27 || i == 36){
                        switch(i){
                            case 16: cell.setCellValue("Espaces"); break;
                            case 27: cell.setCellValue("Equipements"); break;
                            case 36: cell.setCellValue("Services"); break;
                        }
                        cell.setCellStyle(cellStyleFont14Gras);
                    }
                    else if(i == 18 || i == 22 || i == 38 || i == 40 || i == 42 || i == 44 || i == 46 || i == 48 || i == 51){
                        switch(i){
                            case 18: cell.setCellValue(salle1[0]); break; //NOM DE LA SALLE 1
                            case 22: cell.setCellValue(salle2[0]); break; //NOM DE LA SALLE 2
                            case 38: cell.setCellValue(services[0]); break; //NOM DU SERVICE 1
                            case 40: cell.setCellValue(services[1]); break; //NOM DU SERVICE 2
                            case 42: cell.setCellValue(services[2]); break; //NOM DU SERVICE 3
                            case 44: cell.setCellValue(services[3]); break; //NOM DU SERVICE 4
                            case 46: cell.setCellValue(services[4]); break; //NOM DU SERVICE 5
                            case 48: cell.setCellValue(services[5]); break; //NOM DU SERVICE 6
                        }
                        cell.setCellStyle(cellStyleFont13Gras);
                    }
                    else if(i == 19 || i == 23 || i == 39 || i == 41 || i == 43 || i == 45 || i == 47 || i == 49){
                        switch(i){
                            case 19: cell.setCellValue(salle1[7]); break; //COMMENTAIRE DE LA SALLE 1
                            case 23: cell.setCellValue(salle2[7]); break; //COMMENTAIRE DE LA SALLE 2
                            case 39: cell.setCellValue(commentaires[0]); break; //COMMENTAIRE DU PREMIER SERVICE
                            case 41: cell.setCellValue(commentaires[1]); break; //COMMENTAIRE DU DEUXIEME SERVICE
                            case 43: cell.setCellValue(commentaires[2]); break; //COMMENTAIRE DU TROISIEME SERVICE
                            case 45: cell.setCellValue(commentaires[3]); break; //COMMENTAIRE DU QUATRIEME SERVICE
                            case 47: cell.setCellValue(commentaires[4]); break; //COMMENTAIRE DU CINQUIEME SERVICE
                            case 49: cell.setCellValue(commentaires[5]); break; //COMMENTAIRE DU SIXIEME SERVICE
                        }
                        cell.setCellStyle(cellStyleFont11);
                    }
                    else if(i == 26 || i == 29 || i == 30 || i == 31 || i == 32 || i == 33 || i == 34){
                        switch(i){
                            case 29: cell.setCellValue(equipements[0]); break; //NOM DE L'OPTION 1
                            case 30: cell.setCellValue(equipements[1]); break; //NOM DE L'OPTION 2
                            case 31: cell.setCellValue(equipements[2]); break; //NOM DE L'OPTION 3
                            case 32: cell.setCellValue(equipements[3]); break; //NOM DE L'OPTION 4
                            case 33: cell.setCellValue(equipements[4]); break; //NOM DE L'OPTION 5
                            case 34: cell.setCellValue(equipements[5]); break; //NOM DE L'OPTION 6
                        }
                        cell.setCellStyle(cellStyleFont12left);
                    }
                    else if(i == 20 || i == 21 || i == 24 || i == 25 || i == 26){
                        switch(i){
                            case 20: cell.setCellValue("Participants :"); break;
                            case 21: cell.setCellValue(salle1[4]); break; //DATE SALLE 1
                            case 24: if(!(salle2[0].equals(""))) cell.setCellValue("Participants :"); break;//SI IL EXISTE UNE DEUXIEME SALLE
                            case 25: cell.setCellValue(salle2[4]); break; //DATE SALLE 2
                        }
                        cell.setCellStyle(cellStyleFont12CT);
                    }
                    else if(i == 52){
                        cell.setCellStyle(cellStyleFont14Gras);
                    }
                    else{
                        cell.setCellStyle(cellStyleLeft);
                    }
                }
                else if(j == 2){
                    if(i == 16 || i == 27 || i == 36 || i == 52){
                        cell.setCellStyle(cellStyleBottom);
                    }
                    if(i == 18 || i == 20 || i == 22 || i == 24 || i == 29 || i == 30 || i == 31 || i == 32 || i == 33 || i == 34){
                        switch(i){
                            case 18: if(!(salle1[1].equals("Forfait Heure"))) cell.setCellValue(salle1[1]+" ("+salle1[2]+"h)"); else cell.setCellValue(salle1[1]); break; //NOM FORMULE + HEURE FORMULE SALLE 1 
                            case 20: cell.setCellValue(salle1[3]); break; //LE NOMBRE DE PARTICIPANT A LA SALLE 1
                            case 22: if(!(salle2[0].equals(""))){if(!(salle2[1].equals("Forfait Heure"))) cell.setCellValue(salle2[1]+" ("+salle2[2]+"h)"); else cell.setCellValue(salle2[1]);} break; //NOM FORMULE + HEURE FORMULE SALLE 2
                            case 24: cell.setCellValue(salle2[3]); break; //LE NOMBRE DE PARTICIPANT A LA SALLE 2
                            case 29: cell.setCellValue(nbOptions[0]); break; //LE NOMBRE DE L'OPTION 1
                            case 30: cell.setCellValue(nbOptions[1]); break; //LE NOMBRE DE L'OPTION 2
                            case 31: cell.setCellValue(nbOptions[2]); break; //LE NOMBRE DE L'OPTION 3
                            case 32: cell.setCellValue(nbOptions[3]); break; //LE NOMBRE DE L'OPTION 4
                            case 33: cell.setCellValue(nbOptions[4]); break; //LE NOMBRE DE L'OPTION 5
                            case 34: cell.setCellValue(nbOptions[5]); break; //LE NOMBRE DE L'OPTION 6    
                        }
                        cell.setCellStyle(cellStyleFont12HCT);
                    }
                }
                else if(j == 3){
                    if(i == 16 || i == 27 || i == 36 || i == 52){
                        cell.setCellStyle(cellStyleBottom);
                    }
                    if(i == 21 || i == 25){
                        switch(i){
                            case 21: cell.setCellValue(salle1[5]+" à "+salle1[6]); break;//HORAIRE DEBUT + FIN SALLE 1
                            case 25: if(!(salle2[0].equals(""))) cell.setCellValue(salle2[5]+" à "+salle2[6]); break;//HORAIRE DEBUT + FIN SALLE 2
                        }
                        cell.setCellStyle(cellStyleFont12HCT);
                    }
                    if(i == 29){
                        cell.setCellValue("Disposition : "+equipements[6]); //DISPOSITION
                        cell.setCellStyle(cellStyleFont12HCT);
                    }
                }
                else if(j == 4){
                    if(i == 14){
                        cell.setCellValue("Nombre");
                        cell.setCellStyle(cellStyle13ET);
                    }
                    else if(i == 18 || i == 22 || i == 38 || i == 40 || i == 42 || i == 44 || i == 46 || i == 48){
                        switch(i){
                            case 18: cell.setCellValue(nbFormules[0]); break; //LE NOMBRE DE FORMULE SALLE 1
                            case 22: if(!(salle2[0].equals(""))) cell.setCellValue(nbFormules[1]); break; //LE NOMBRE DE FORMULE SALLE 2
                            case 38: cell.setCellValue(nbServices[0]); break;//LE NOMBRE DU SERVICE 1
                            case 40: cell.setCellValue(nbServices[1]); break;//LE NOMBRE DU SERVICE 2
                            case 42: cell.setCellValue(nbServices[2]); break;//LE NOMBRE DU SERVICE 3
                            case 44: cell.setCellValue(nbServices[3]); break;//LE NOMBRE DU SERVICE 4
                            case 46: cell.setCellValue(nbServices[4]); break;//LE NOMBRE DU SERVICE 5
                            case 48: cell.setCellValue(nbServices[5]); break;//LE NOMBRE DU SERVICE 6
                        }
                        cell.setCellStyle(cellStyleFont12CT);
                    }
                    else if(i == 52){
                        cell.setCellStyle(cellStyleFont14Gras);
                    }
                    else{
                        cell.setCellStyle(cellStyleLeft);
                    }
                }
                else if(j == 5){
                    if(i == 14){
                        cell.setCellValue("Prix unitaire HT");
                        cell.setCellStyle(cellStyle13ET);
                    }
                    else if(i == 18 || i == 22 || i == 38 || i == 40 || i == 42 || i == 44 || i == 46 || i == 48){
                        switch(i){
                            case 18: cell.setCellValue(tarifs[0]+" €"); break; //TARIF DE LA SALLE 1 EN FONCTION DE LA FORMULE CHOISIE
                            case 22: if(!(salle2[0].equals(""))) cell.setCellValue(tarifs[1]+" €"); break; //TARIF DE LA SALLE 2 EN FONCTION DE LA FORMULE CHOISIE
                            case 38: cell.setCellValue(prixServices[0]+" €"); break; //PRIX DU SERVICE 1
                            case 40: cell.setCellValue(prixServices[1]+" €"); break; //PRIX DU SERVICE 2
                            case 42: cell.setCellValue(prixServices[2]+" €"); break; //PRIX DU SERVICE 3
                            case 44: cell.setCellValue(prixServices[3]+" €"); break; //PRIX DU SERVICE 4
                            case 46: cell.setCellValue(prixServices[4]+" €"); break; //PRIX DU SERVICE 5
                            case 48: cell.setCellValue(prixServices[5]+" €"); break; //PRIX DU SERVICE 6
                        }
                        cell.setCellStyle(cellStyleFont12CTsansBordures);
                    }
                    else if(i == 52){
                        cell.setCellStyle(cellStyleBottom);
                    }
                }
                else if(j == 6){
                    if(i == 14){
                        cell.setCellValue("Prix HT");
                        cell.setCellStyle(cellStyle13ET);
                    }
                    else if(i == 18 || i == 22 || i == 38 || i == 40 || i == 42 || i == 44 || i == 46 || i == 48){
                        switch(i){
                            case 18: cell.setCellValue(salle1FINAL+" €"); break; //PRIX FINAL SALLE 1
                            case 22: if(!(salle2[0].equals(""))) cell.setCellValue(salle2FINAL+" €"); break; //PRIX FINAL SALLE 2 {SI EXISTE}
                            case 38: cell.setCellValue(service1FINAL+" €"); break; //PRIX FINAL SERVICE 1
                            case 40: cell.setCellValue(service2FINAL+" €"); break; //PRIX FINAL SERVICE 2
                            case 42: cell.setCellValue(service3FINAL+" €"); break; //PRIX FINAL SERVICE 3
                            case 44: cell.setCellValue(service4FINAL+" €"); break; //PRIX FINAL SERVICE 4
                            case 46: cell.setCellValue(service5FINAL+" €"); break; //PRIX FINAL SERVICE 5
                            case 48: cell.setCellValue(service6FINAL+" €"); break; //PRIX FINAL SERVICE 6
                        }
                        cell.setCellStyle(cellStyleFont12CTsansBordures);
                    }
                    else if(i == 52){
                        cell.setCellStyle(cellStyleBottom);
                    }
                }
                else{
                    if(i != 14) cell.setCellStyle(cellStyleLeft);
                }
            }
        }
        
        /*
        FIN BLOC CENTRAL
        */
        
        /*
        DEBUT LIGNES 56 - 58
        */
        
        row = sheet.createRow(55);
        cell = row.createCell(1);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Acompte de 50% à la réservation.");
        
        cell = row.createCell(5);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TVA 5,5%");
        
        cell = row.createCell(6);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(tvaServices[0]+" €"); //TVA 10% 
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(56);
        cell = row.createCell(1);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Pour le présent devis :");
        
        cell = row.createCell(3);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(round(prixFinalTTC/2, 2)+" €"); //L'ACCOMPTE 50% PRIX FINAL TTC
        
        cell = row.createCell(5);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TVA 10%");
        
        cell = row.createCell(6);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(tvaServices[1]+" €"); //TVA 10 %
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(57);
        cell = row.createCell(1);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        cellStyle = wb.createCellStyle();
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Solde au plus tard 24h avant utilisation des locaux.");
        
        cell = row.createCell(5);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TVA 20%");
        
        cell = row.createCell(6);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        tvaServices[2] += tarifs[0] * nbFormules[0] * 0.2;
        if(!(salle2[0].equals(""))) tvaServices[2] += tarifs[1] * nbFormules[1] * 0.2;
        cell.setCellValue(tvaServices[2]+" €");
        
        /*
        FIN LIGNES 56 - 58
        */
        
        /*
        DEBUT BLOC DU BAS 
        */
        
        row = sheet.createRow(54);
        cell = row.createCell(5);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        fonte.setColor((short)HSSFColor.WHITE.index);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TOTAL HT");
        
        cell = row.createCell(6);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        fonte.setColor((short)HSSFColor.WHITE.index);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(round(prixFinalHT, 2)+" €"); //PRIX FINAL DU DEVIS HT
        
        //--------------------------------------------------------------------//
        
        row = sheet.createRow(58);
        cell = row.createCell(5);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        fonte.setColor((short)HSSFColor.WHITE.index);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("TOTAL TTC");
        
        cell = row.createCell(6);
        fonte = wb.createFont();
        fonte.setFontName("Calibri (Corps)");
        fonte.setFontHeightInPoints((short)12);
        fonte.setBold(true);
        fonte.setColor((short)HSSFColor.WHITE.index);
        cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFont(fonte);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(round(prixFinalTTC, 2)+" €"); //PRIX FINAL DU DEVIS TTC
        
        /*
        FIN BLOC DU BAS
        */
        
        
        try{
            
            FileOutputStream fileOut = new FileOutputStream("devis "+fichier[0]+" "+fichier[1]+".xls");
            wb.write(fileOut);
            fileOut.close();
            
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
