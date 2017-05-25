/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factures;

import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.*;
import static gestionagenda.GestionAgenda.rq;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
    
    public static void creerFacture(int idResa) throws SQLException{
    	
    	//Recuperer montant, et cr�er facture dans la bdd
    	DecimalFormat dcf= new DecimalFormat ("0.00");
    	double montant = getmontant(idResa);
    	rq.ajoutFacture(montant,idResa);
    	int idclient = rq.getUniqueIntByIdFromTable("reservation", "idReservation", idResa, "fkidClient");
    	System.out.println();
    	System.out.println(Integer.toString(idclient));
    	String adresseclient= rq.getUniqueStringByIdFromTable("client", "idClient", idclient, "adresseFacturation");
    	System.out.println(adresseclient);
    	String [] adresseprete=adresseclient.split(",");
    	String nom= rq.getUniqueStringByIdFromTable("client", "idClient", idclient, "nom");
    	String prenom= rq.getUniqueStringByIdFromTable("client", "idClient", idclient, "prenom");
    	String intituleclient= prenom+" "+nom;
    	String[] optselib = getlibelleoption(idResa);
    	double[] optseprix = getprixoption(idResa);
    	
    	
    	
    	
        /*
        Création des variables
        */
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Format");
        HSSFCellStyle cellStyle = null;
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = null;
        HSSFFont fonte = null;
        
        /*
        DEFINITION DES CELL STYLES
        */
        //Bordure droite
        HSSFCellStyle cellStyleRight = wb.createCellStyle();
        cellStyleRight.setBorderRight(BorderStyle.MEDIUM);
        cellStyleRight.setRightBorderColor((short)8);
        //Bordure gauche
        HSSFCellStyle cellStyleLeft = wb.createCellStyle();
        cellStyleLeft.setBorderLeft(BorderStyle.MEDIUM);
        cellStyleLeft.setLeftBorderColor((short)8);
        //Bordure haut
        HSSFCellStyle cellStyleTop = wb.createCellStyle();
        cellStyleTop.setBorderTop(BorderStyle.MEDIUM);
        cellStyleTop.setTopBorderColor((short)8);
        //Bordure bas
        HSSFCellStyle cellStyleBottom = wb.createCellStyle();
        cellStyleBottom.setBorderBottom(BorderStyle.MEDIUM);
        cellStyleBottom.setBottomBorderColor((short)8);
        
        /*
        DEFINITION DES FONTS
        */
        //Font 12
        HSSFFont fonte12 = wb.createFont();
        fonte12.setFontHeightInPoints((short)12);
        fonte12.setFontName("Courier New");
        HSSFCellStyle cellStyleFont12 = wb.createCellStyle();
        cellStyleFont12.setFont(fonte12);
        //Font 16
        HSSFFont fonte16 = wb.createFont();
        fonte16.setFontHeightInPoints((short)16);
        fonte16.setFontName("Courier New");
        HSSFCellStyle cellStyleFont16 = wb.createCellStyle();
        cellStyleFont16.setFont(fonte16);
        //Font 16 gras
        HSSFFont fonte16Gras = wb.createFont();
        fonte16Gras.setFontHeightInPoints((short)16);
        fonte16Gras.setFontName("Courier New");
        fonte16Gras.setBold(true);
        HSSFCellStyle cellStyleFont16Gras = wb.createCellStyle();
        cellStyleFont16Gras.setFont(fonte16Gras);
        //Font 18
        HSSFFont fonte18 = wb.createFont();
        fonte18.setFontHeightInPoints((short)18);
        fonte18.setFontName("Courier New");
        HSSFCellStyle cellStyleFont18 = wb.createCellStyle();
        cellStyleFont18.setFont(fonte18);
        //Font 18 gras
        HSSFFont fonte18Gras = wb.createFont();
        fonte18Gras.setFontHeightInPoints((short)18);
        fonte18Gras.setFontName("Courier New");
        fonte18Gras.setBold(true);
        HSSFCellStyle cellStyleFont18Gras = wb.createCellStyle();
        cellStyleFont18Gras.setFont(fonte18Gras);
        //Bordures left, bottom, right avec font 16 en gras
        HSSFCellStyle cellStyleLBR = wb.createCellStyle();
        cellStyleLBR.setBorderLeft(BorderStyle.MEDIUM_DASHED);
        cellStyleLBR.setLeftBorderColor((short)8);
        cellStyleLBR.setBorderBottom(BorderStyle.MEDIUM_DASHED);
        cellStyleLBR.setBottomBorderColor((short)8);
        cellStyleLBR.setBorderRight(BorderStyle.MEDIUM_DASHED);
        cellStyleLBR.setRightBorderColor((short)8);
        cellStyleLBR.setFont(fonte16Gras);
        
        /*
        Fusion des cellules
        */
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2)); // Fusionne les cellules de la ligne 0 à la ligne 0 et colonnes 0 à colonne 2
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,3));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 3));
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 4, 5));
        sheet.addMergedRegion(new CellRangeAddress(14, 14, 4, 5));
        
        /*
        Redimensionnement des lignes et colonnes par défaut
        */
        sheet.setDefaultColumnWidth(15);
        sheet.setDefaultRowHeightInPoints(22);
        
        /*
        DEBUT 1 LIGNE
        */
        
        cell = row.createCell(0);
        cell.setCellValue("Lemons Production");
        cell.setCellStyle(cellStyleFont18Gras); 
        cell = row.createCell(5);
        cell.setCellStyle(cellStyleRight);
        
        /*
        FIN 1 LIGNE
        */
        
        /*
        DEBUT 2 LIGNE
        */
        row = sheet.createRow(1);
        for (int i = 0; i <= 5; ++i) {
            cell = row.createCell(i);
            if (i == 0) {
                cell.setCellValue("Soci�t� de production audiovisuelle");
                cellStyle = wb.createCellStyle();
                cellStyle.setFont(fonte16);
                cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                cellStyle.setBottomBorderColor((short)8);
                cell.setCellStyle(cellStyle);
            }
            else if(i != 0){
            cell.setCellStyle(cellStyleBottom);
            }
        }
        cell = row.createCell(5);
        cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBottomBorderColor((short)8);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setRightBorderColor((short)8);
        cell.setCellStyle(cellStyle);
        
        /*
        FIN 2 LIGNE
        */
        
        /*
        DEBUT 3 LIGNE
        */

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue("n° d'identification : 450 621 818 R.C.S");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN 3 LIGNE
        */
        
        /*
        DEBUT 4 LIGNE
        */
        
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("TVA intracommunautaire : FR 37 450 621 818 921 C");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN 4 LIGNE
        */
        
        /*
        DEBUT 5 LIGNE
        */
        
        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("5, impasse Mousset");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN LIGNE 5
        */
        
        /*
        DEBUT LIGNE 6
        */
        
        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("75012 Paris");
        cell.setCellStyle(cellStyleFont12);
        
        /*
        FIN LIGNE 6
        */
        
        /*
        DEBUT LIGNE 7
        */
        
        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Sébastien AUTRET");
        cell.setCellStyle(cellStyleFont12);
        
        
        /*
        FIN LIGNE 7
        */
        
        /*
        DEBUT LIGNES 8 - 11
        */
        
        HSSFRow row0 = sheet.createRow(7);
        HSSFRow row1 = sheet.createRow(8);
        HSSFRow row2 = sheet.createRow(9);
        HSSFRow row3 = sheet.createRow(10);
        HSSFRow[] lesLignes = new HSSFRow[4];
        lesLignes[0] = row0;
        lesLignes[1] = row1;
        lesLignes[2] = row2;
        lesLignes[3] = row3;
        for(int i = 4 ; i <= 5 ; i++){
            for(int j = 7 ; j <= 10 ; j++){
                if(i == 4){
                    fonte = wb.createFont();
                    fonte.setFontHeightInPoints((short)16);
                    fonte.setFontName("Courier New");
                    fonte.setBold(true);
                    cell = lesLignes[j - 7].createCell(i);
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
                        
                    }
                    else if(j == 8){
                        cell.setCellValue(intituleclient);
                        
                    }
                    else if(j == 9){
                        cell.setCellValue(adresseprete[0]);
                        
                    }
                    else if(j == 10){
                        cell.setCellValue(adresseprete[1]);
                        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
                        cellStyle.setBottomBorderColor((short)8);
                        cellStyle.setBorderRight(BorderStyle.MEDIUM);
                        cellStyle.setRightBorderColor((short)8);
                        
                    }
                }
                else if(i == 5){
                    cell = lesLignes[j - 7].createCell(i);
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
                }
                cell.setCellStyle(cellStyle);
            }
        }
        
        /*
        FIN LIGNES 8 - 11
        */
        
        /*
        DEBUT LIGNE 15
        */
        
        row = sheet.createRow(14);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd" );
     
        Date currentTime_1 = new Date();
      
        String dateString = formatter.format(currentTime_1);
        cell = row.createCell(4);
        cell.setCellValue("Paris, "+dateString+"");
        cell.setCellStyle(cellStyleFont16);
        
        /*
        FIN LIGNE 15
        */
        
        /*
        DEBUT LIGNE 17
        */
        
        row = sheet.createRow(16);
        for(int i = 0 ; i <= 2 ; i++){
            cell = row.createCell(i);
            cellStyle = wb.createCellStyle();
            cellStyle.setBorderTop(BorderStyle.MEDIUM_DASHED);
            cellStyle.setTopBorderColor((short)8);
            cellStyle.setBorderBottom(BorderStyle.MEDIUM_DASHED);
            cellStyle.setBottomBorderColor((short)8);
            if(i == 0){
                fonte = wb.createFont();
                fonte.setFontHeightInPoints((short)15);
                fonte.setFontName("Courier New");
                fonte.setBold(true);
                cell.setCellValue("Facture");
                cellStyle.setFont(fonte);
            }
            else if(i == 2){
                fonte = wb.createFont();
                fonte.setFontHeightInPoints((short)17);
                fonte.setFontName("Courier New");
                cell.setCellValue(rq.getidFacture(idResa,montant)+" /2017");
                cellStyle.setFont(fonte);
                cellStyle.setBorderRight(BorderStyle.MEDIUM_DASHED);
                cellStyle.setRightBorderColor((short)8);
            }
            cell.setCellStyle(cellStyle);
        }
        
        /*
        FIN LIGNE 17
        */
        
        /*
        DEBUT LIGNES 20 - 27
        */
        
        HSSFRow row19 = sheet.createRow(19);
        HSSFRow row20 = sheet.createRow(20);
        HSSFRow row21 = sheet.createRow(21);
        HSSFRow row22 = sheet.createRow(22);
        HSSFRow row23 = sheet.createRow(23);
        HSSFRow row24 = sheet.createRow(24);
        HSSFRow row25 = sheet.createRow(25);
        HSSFRow row26 = sheet.createRow(26);
        HSSFRow row27 = sheet.createRow(27);
        HSSFRow row28 = sheet.createRow(28);
        HSSFRow row29 = sheet.createRow(29);
        HSSFRow row30 = sheet.createRow(30);
        HSSFRow row31 = sheet.createRow(31);
        HSSFRow row32 = sheet.createRow(32);
        HSSFRow row33 = sheet.createRow(33);
        HSSFRow row34 = sheet.createRow(34);
        HSSFRow[] lesRows = new HSSFRow[16];
        lesRows[0] = row19;
        lesRows[1] = row20;
        lesRows[2] = row21;
        lesRows[3] = row22;
        lesRows[4] = row23;
        lesRows[5] = row24;
        lesRows[6] = row25;
        lesRows[7] = row26;
        lesRows[8] = row27;
        lesRows[9] = row28;
        lesRows[10] = row29;
        lesRows[11] = row30;
        lesRows[12] = row31;
        lesRows[13] = row32;
        lesRows[14] = row33;
        lesRows[15] = row34;
        
        for(int i = 19 ; i <= 34 ; i++){
            for(int j = 0 ; j <= 5 ; j++){
            	
            	cell = lesRows[i - 19].createCell(j);
            	cellStyle = wb.createCellStyle();
            	
            	if(i == 19){
            		cellStyle.setBorderTop(BorderStyle.MEDIUM);
            		cellStyle.setTopBorderColor((short)8);
            	}
            	if(i == 34){
            		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            		cellStyle.setBottomBorderColor((short)8);
            	}
            	if(j >= 3 && j <= 5){
            		cellStyle.setBorderRight(BorderStyle.MEDIUM);
            		cellStyle.setRightBorderColor((short)8);
            	}
            	if(i == 20 && j == 4 || i == 20 && j == 5){
            		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
            		cellStyle.setBottomBorderColor((short)8);
            	}
            	cell.setCellStyle(cellStyle);
            	for(int k = 21;k<35;k++){
            		if(i == k && j==0){
            			cell.setCellValue(optselib[k-21]);
            		}
            		if(i == k && j==5 && optselib[k-21]!=null){
            			cell.setCellValue(optseprix[k-21]+" �");
            		}
            		
            	}
            }
        }
        
        
        /*
        FIN LIGNES 20 - 27
        */
        
        /*
        DEBUT LIGNES 28 - 30
        */
        
        HSSFRow row35 = sheet.createRow(35);
        HSSFRow row36 = sheet.createRow(36);
        HSSFRow row37 = sheet.createRow(37);
        lesRows[0] = row35;
        lesRows[1] = row36;
        lesRows[2] = row37;
        
        //Juste pour l'exemple (à voir)
        String[] lesTotaux = {"TOTAL HT", "TVA 20%", "TOTAL TTC"};
        double prix = montant;
        double tva = prix/100*20;
        double[] lesPrix = {prix, tva, prix+tva};
        
        for(int i = 35 ; i <= 37 ; i++){
            for(int j = 4 ; j <= 5 ; j++){
                cell = lesRows[i - 35].createCell(j);
                cell.setCellStyle(cellStyleLBR);
                if(j == 4){
                    cell.setCellValue(lesTotaux[i - 35]);
                }
                else{
                    cell.setCellValue(dcf.format(lesPrix[i - 35])+" �");
                }
            }
        }
        
        /*
        FIN LIGNES 28 - 30
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
            
            FileOutputStream fileOut = new FileOutputStream("facture"+idResa+".xls");
            wb.write(fileOut);
            fileOut.close();
            
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static double getmontant(int id) throws SQLException{
    	double montantotal = 0.00;
    	int [] opse = rq.getUniqueListElementByIdFromTable("choix", "fkidReservation", id, "fkidOptionsServices");
    	
    	for(int unique : opse){
    		System.out.println(Integer.toString(unique));
    		System.out.println(Double.toString(montantotal));
    		System.out.println(Double.toString(rq.getTarifServiceint(unique)));
    		montantotal += rq.getTarifServiceint(unique);
    	}
    	/*int [] infosalle = rq.getUniqueListElementByIdFromTable("sallesresa", "fkidReservation", id, "fkidInfoSalle");
    	for(int unique : infosalle){
    		montantotal += rq.getUniqueElementByIdFromTable("optionsservices","idOptionsServices", unique, "prixTTC");
    	}*/
    	System.out.println(Double.toString(montantotal));
    	return montantotal;
    }
    
    public static String[] getlibelleoption(int id) throws SQLException{
    	int [] opse = rq.getUniqueListElementByIdFromTable("choix", "fkidReservation", id, "fkidOptionsServices");
    	String [] listelibelle = new String [16];
    	for(int i=0; i<opse.length;++i){
    		listelibelle[i]=rq.getUniqueStringByIdFromTable("optionService", "idOptionsServices", opse[i], "libelle");
    	}
    	return listelibelle;
    }
    
    public static double[] getprixoption(int id) throws SQLException{
    	int [] opse = rq.getUniqueListElementByIdFromTable("choix", "fkidReservation", id, "fkidOptionsServices");
    	double [] listeprix = new double [16];
    	for(int i=0; i<opse.length;++i){
    		listeprix[i]=rq.getTarifServiceint(opse[i]);
    	}
    	return listeprix;
    }
}
