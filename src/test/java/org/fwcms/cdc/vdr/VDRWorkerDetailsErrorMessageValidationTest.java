package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fwcms.initiators.VDRInitiator;
import org.fwcms.pages.CommonElements;
import org.fwcms.pages.cdc.LoginPage;
import org.fwcms.pages.cdc.vdr.HomePage;
import org.fwcms.pages.cdc.vdr.VdrDetailsPage;
import org.fwcms.pages.cdc.vdr.WorkerSelectionPage;
import org.fwcms.util.EmployerCredentialsProp;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VDRWorkerDetailsErrorMessageValidationTest extends VDRInitiator {
	 
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	public static String vdrDetailsFilepath,wdCSVFilePath;
	 
	public static List<String> errorMessage = new ArrayList<String>();
	public static List<String> kdnValue = new ArrayList<String>();
	public static List<String> agentIdValue = new ArrayList<String>();
	public static List<String> nameValue = new ArrayList<String>();
	public static List<String> passportNuberValue = new ArrayList<String>();
	public static List<String> genderValue = new ArrayList<String>();
	public static List<String> nationalityValue = new ArrayList<String>();
	
	List<String> csvPassportNumber = new ArrayList<String>();
	List<String> csvReason = new ArrayList<String>();
	
	@SuppressWarnings("resource")
	@Test
	public void vdrWorkerDetailsErrorMessageValidationTest() throws Exception {
		errorMessage.clear();
		kdnValue.clear();
		agentIdValue.clear();
		nameValue.clear();
		passportNuberValue.clear();
		genderValue.clear();
		nationalityValue.clear();
		csvPassportNumber.clear();
		csvReason.clear();
		
		WebDriver driver = getDriver();	
		driver.get(cdcUrl);	
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		
		vdrDetailsFilepath= System.getProperty("user.dir")+"\\src\\test\\resources\\org\\fwcms\\cdc\\vdr\\WorkerDetailsErrorValidation\\ErrorMessageValidation.xlsx";
		wdCSVFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\org\\fwcms\\cdc\\vdr\\WorkerDetailsErrorValidation\\wd_NoNeedToUpdate.csv";
		
		FileInputStream inputStream = new FileInputStream(new File(vdrDetailsFilepath));  
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);       
        Iterator<Row> iterator = firstSheet.iterator();  
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
           
            String flagvalue= cellIterator.next().getStringCellValue();
            if(flagvalue.equalsIgnoreCase("Y")){
            	errorMessage.add(cellIterator.next().getStringCellValue())	;
            	kdnValue.add(cellIterator.next().getStringCellValue())	;
            	agentIdValue.add(cellIterator.next().getStringCellValue())	;
            	nameValue.add(cellIterator.next().getStringCellValue())	;
            	passportNuberValue.add(cellIterator.next().getStringCellValue())	;
            	genderValue.add(cellIterator.next().getStringCellValue())	;
            	nationalityValue.add(cellIterator.next().getStringCellValue())	;
            }
                          
        }  
		        inputStream.close();
		        FileWriter fileWriter = null;
		        final String COMMA_DELIMITER = ",";
		        final String NEW_LINE_SEPARATOR = "\n";
		        final String FILE_HEADER = "WorkerName,PassportNumber,Gender,Nationality";
		        fileWriter = new FileWriter(wdCSVFilePath);
		        fileWriter.append(FILE_HEADER.toString());
		        fileWriter.append(NEW_LINE_SEPARATOR);
		        int totalpassportSize=passportNuberValue.size();
		         
		        for(int i=0;i<passportNuberValue.size();i++){
		        	fileWriter.append(nameValue.get(i));
		        	fileWriter.append(COMMA_DELIMITER);
		        	fileWriter.append(passportNuberValue.get(i));
		        	fileWriter.append(COMMA_DELIMITER);
		        	fileWriter.append(genderValue.get(i));
		        	fileWriter.append(COMMA_DELIMITER);
		        	fileWriter.append(nationalityValue.get(i));
		        	fileWriter.append(NEW_LINE_SEPARATOR);
		
		         
			  
		        }
		        
		        fileWriter.close();
		        if(totalpassportSize>0){
		        	
		        	String agentIdvalue= agentIdValue.get(0);
					String kdnNumberValue=kdnValue.get(0);
					
					 /**
					  * code to loop the VDR flow
					  * 
					  */
					CommonElements cm = new CommonElements(driver).initElements();
					HomePage hp = new HomePage(driver).initElements();
					hp.clickNewVdrLinkInActions();
					VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();	 
					WorkerSelectionPage workerSelection= new WorkerSelectionPage(driver).initElements();
					cm.waitForOverlayToHide(); 
					vdp.selectKdnNumberFirstVisibleInList(kdnNumberValue);
					workerSelection.enterAgentIdAndVerifyAgentDetailsPopup(agentIdvalue);
					workerSelection.attachWorkersAndClickSearch(wdCSVFilePath);	
					workerSelection.selectWorkerCheckBox();
					String csvdownloadedFilename = workerSelection.clickDownloadUnmatchedWorkersListAndgetFileName();
		        	Thread.sleep(5000);
					System.out.println(csvdownloadedFilename);
					String pathname="C:\\DownloadITR\\"+csvdownloadedFilename;
					
					FileReader fr = null;
					CSVParser csvFileParser = null;
					fr = new FileReader(pathname);
					csvFileParser = new CSVParser(fr, CSVFormat.DEFAULT);
					@SuppressWarnings("rawtypes")
					List csvRecords = csvFileParser.getRecords();
					 
					 //Read CSV value and save the details in arrayList
					for (int i = 1; i < csvRecords.size()-1; i++) {
						CSVRecord record = (CSVRecord) csvRecords.get(i);
						csvPassportNumber.add(record.get(0).toString());
						csvReason.add(record.get(2).toString());
						
					}
System.out.println(csvPassportNumber);
System.out.println("csvPassportNumber size "+csvPassportNumber.size());
					//Assert the CSV file value with excel sheet value
					for (int k = 0; k <csvPassportNumber.size(); k++) {
						 
					String passNum = csvPassportNumber.get(k);
					System.out.println("passNum "+passNum);
					if(!passNum.equals("")){
						String reasonValue=	csvReason.get(k);
						int excelsheetPassportIndex= passportNuberValue.indexOf(passNum);
						
						String excelSheetErrorMessage= errorMessage.get(excelsheetPassportIndex);
						System.out.println("ErrorMessage From Excel: "+excelSheetErrorMessage );
						System.out.println("ErrorMessage From csv: "+reasonValue);
						Assert.assertEquals(reasonValue, excelSheetErrorMessage);
					}else{
						
						System.out.println("CSV ROW "+k+" IS EMPTY");	
					}
 }
 
		        }else{
		        	
		        	System.out.println("The Flag is set to N for all the error message validation.");
		        }
		        
		 	
	}
	

}


