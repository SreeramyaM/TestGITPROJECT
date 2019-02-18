package org.fwcms.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;

 
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class FileRename {
	
	
	 
	
 
	@SuppressWarnings("resource")
	public   List<String>  renameFileAndCollectAllITRs(String oldFilePath, List<String> totalitr) throws IOException, InterruptedException {
		 
		 File oldName = new File(oldFilePath);
		 
		 long millis =System.currentTimeMillis();
		 String newFileName="Transaction_Ids_"+millis+".csv";
		 System.out.println(newFileName);
		 String pathname="C:\\DownloadITR\\"+newFileName;
		 System.out.println(pathname);
	     File newName = new File(pathname);
	      if(oldName.renameTo(newName)) {
	         System.out.println("renamed");
	      } else {
	         System.out.println("Error");
	      } 
	      
	      //Write data to text  file to store the file location
	     /* File txtFile = new File("C:\\Framework\\SOAAutomationFramework\\TestData\\VDRflow_UIAndServices\\DownloadedFileName.txt");
	      if (!txtFile.exists()) {
	    	  txtFile.createNewFile();
			}

			FileWriter fw = new FileWriter(txtFile);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.write(pathname);
			bw.close();
			Thread.sleep(5000);*/
			//Read CSV file
			 	 
		
			
			FileReader fileReader = null;
			CSVParser csvFileParser = null;
			fileReader = new FileReader(pathname);
			csvFileParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
			@SuppressWarnings("rawtypes")
			List csvRecords = csvFileParser.getRecords();
			
			CSVRecord record = (CSVRecord) csvRecords.get(1);
			
			if(!record.get(8).toString().isEmpty()){
				totalitr.add(record.get(8).toString());
			}
			if(!record.get(9).toString().isEmpty()){
				totalitr.add(record.get(9).toString());
			}
			if(!record.get(10).toString().isEmpty()){
				totalitr.add(record.get(10).toString());
			}
			return totalitr;
 
	   }
	/**
	 * Renaming the file name 
	 * @param oldFilePath
	 * @param filename
	 * @param fileExtension
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public String renameFile(String oldFilePath, String filename, String fileExtension) throws IOException, InterruptedException {
		 
		 File oldName = new File(oldFilePath);
		 
		 long millis =System.currentTimeMillis();
		 String newFileName=filename+"_"+millis+"."+fileExtension;
		 System.out.println(newFileName);
		 String pathname="C:\\DownloadITR\\"+newFileName;
		 System.out.println(pathname);
	     File newName = new File(pathname);
	      if(oldName.renameTo(newName)) {
	         System.out.println("renamed");
	      } else {
	         System.out.println("Error");
	      }   
			return pathname;
	   }
	
	
	
	
	public   void enterITRstoExcel(List<String> totalitr) throws IOException, InterruptedException {
			//Write data to excel	
			File exfile = new File("C:\\Framework\\SOAAutomationFramework\\TestData\\VDRflow_UIAndServices\\ITR.xlsx");
			 if (!exfile.exists()) {
				 exfile.createNewFile();
				}
			 FileOutputStream out = new FileOutputStream( 
				      new File("C:\\Framework\\SOAAutomationFramework\\TestData\\VDRflow_UIAndServices\\ITR.xlsx"));
			//Create workbook
			 XSSFWorkbook workbook = new XSSFWorkbook(); 
		      //Create a blank sheet
		      XSSFSheet spreadsheet = workbook.createSheet("ITRVALUE");
		      
		     
			for(int i=0;i<totalitr.size();i++){
				spreadsheet.createRow(i).createCell(0).setCellValue(totalitr.get(i));
			}
			workbook.write(out);
		      out.close();
			System.out.println("Writing ITR values  into excel file is completed");
			Thread.sleep(3000);
			totalitr.clear();
	   }
 
	} 


