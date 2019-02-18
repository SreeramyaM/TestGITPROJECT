package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileReader;
import java.util.ArrayList;

import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.fwcms.initiators.VDRInitiator;
import org.fwcms.pages.CommonElements;
import org.fwcms.pages.cdc.LoginPage;
import org.fwcms.pages.cdc.vdr.HomePage;
import org.fwcms.pages.cdc.vdr.PurchaseInsurancePage;
import org.fwcms.pages.cdc.vdr.SubmitVDRPage;
import org.fwcms.pages.cdc.vdr.VdrDetailsPage;
import org.fwcms.pages.cdc.vdr.WorkerPointOfEntryPage;
import org.fwcms.pages.cdc.vdr.WorkerSelectionPage;
import org.fwcms.prop.cdc.vdrApplicationPageProp;
import org.fwcms.util.EmployerCredentialsProp;
import org.fwcms.util.FileRename;
import org.fwcms.util.MySqlAccess;
 
import org.openqa.selenium.WebDriver;
 
import org.testng.annotations.Test;

public class VDRAllCountrySubmissionTest extends VDRInitiator {
	 
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	public static String vdrDetailsFilepath,wdCSVFilePath;
	public static List<String> totalitr = new ArrayList<String>();
	
	@SuppressWarnings("resource")
	@Test
	public void testVDRFlowForAllCountry() throws Exception {
		totalitr.clear();
		WebDriver driver = getDriver();	
		driver.get(cdcUrl);	
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		vdrDetailsFilepath= System.getProperty("user.dir")+"\\src\\test\\resources\\org\\fwcms\\cdc\\vdr\\VDRDetails.csv";
		wdCSVFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\org\\fwcms\\cdc\\vdr\\workerDetails\\";
		
		List<String> execute = new ArrayList<String>();	 
		List<String> country = new ArrayList<String>();
		List<String> wdFileName = new ArrayList<String>();	 
		List<String> agentId = new ArrayList<String>();
		List<String> kdnNumber = new ArrayList<String>();
	
		
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		fileReader = new FileReader(vdrDetailsFilepath);
		csvFileParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
		@SuppressWarnings("rawtypes")
		List csvRecords = csvFileParser.getRecords();
		 
		//changed i=2 from i=1
		for (int i = 2; i < csvRecords.size(); i++) {
			CSVRecord record = (CSVRecord) csvRecords.get(i);
			 
			for (int j = 0; j < record.size(); j++) {
			 	if (record.get(j).toString().equals("Y")) {
			 		execute.add(record.get(j + 0).toString());
			 		country.add(record.get(j + 1).toString());
			 		wdFileName.add(record.get(j + 2).toString());
			 		agentId.add(record.get(j + 3).toString());
			 		kdnNumber.add(record.get(j + 4).toString());
			 		
			 		} 
				}
			}
		
		int totalExecute= execute.size();
		System.out.println("Execute with Y is/are "+totalExecute);
		for (int t = 0; t < totalExecute; t++) {
			@SuppressWarnings("unused")
			String countryValue=country.get(t);
			String wdCSVFileUpload= wdCSVFilePath+wdFileName.get(t);
			String agentIdvalue= agentId.get(t);
			String kdnNumberValue=kdnNumber.get(t);
			
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
			workerSelection.attachWorkersAndClickSearch(wdCSVFileUpload);		
			
			workerSelection.selectWorkerCheckBox();
			workerSelection.clickDownloadUnmatchedWorkersList();
			workerSelection.clickSaveButton();  
			workerSelection.selectSourceCountryEmbassy();	
			workerSelection.clickOKButton();	
			String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
			cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
			workerSelection.clickContinueButton();	
			
			WorkerPointOfEntryPage WorkerPointOfEntry= new WorkerPointOfEntryPage(driver).initElements();
			WorkerPointOfEntry.enterWorkerPointOfEntry();
			WorkerPointOfEntry.clickSaveButton();
			cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
			WorkerPointOfEntry.clickContinueButton();	
			
			SubmitVDRPage submitVDR= new SubmitVDRPage(driver).initElements();
			submitVDR.selectDeclarationCheckBox();
			submitVDR.verifyNationality();
			submitVDR.clickSubmitButton();
			submitVDR.verifySubmitButtonConfirmationPopup();
			String vdrSubmissionMessage=vdrApplicationPageProp.getVdrSubmissionMessage();
			cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(vdrSubmissionMessage);	
			
			String oneTimePassword=new MySqlAccess().getOTPOfEmpForVdrSubmission(EmployerCredentialsProp.getVdrEmployerUserName().trim());
			System.out.println(oneTimePassword);
			submitVDR.enterAndConfirmOTP(oneTimePassword);
			cm.waitForOverlayToHide();
			
		 	PurchaseInsurancePage purchaseInsurance=new PurchaseInsurancePage(driver).initElements();
			purchaseInsurance.generateTransactionIds();
			cm.waitForOverlayToHide();
			String itrGenerationMessage=vdrApplicationPageProp.getConfirmITRGenerationMessage();
			purchaseInsurance.assertITRGenerationConfirmationPopUp(itrGenerationMessage);
			purchaseInsurance.generateTransactionIds();
			purchaseInsurance.clickIUnderstandButton();
			purchaseInsurance.verifyElementsInPurchaseInsuranceOptionsPopUp();
			
			purchaseInsurance.generateTransactionIds();
			purchaseInsurance.clickIUnderstandButton();
			purchaseInsurance.selectPurchaseInsuranceOption("Offline Self Assistance");
			purchaseInsurance.clickOnProceedInPurchaseInsuranceOptionsPopUp();
			String itrGenerationSuccessMessage=vdrApplicationPageProp.getITRGenerationSuccessMessage();
			cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(itrGenerationSuccessMessage);
			purchaseInsurance.clickProceedButton();
			cm.waitForOverlayToHide();
			purchaseInsurance.verifyIfThankYouMessageIsDisplayed();
						
			Thread.sleep(5000);
			FileRename fr= new FileRename();
			totalitr = fr.renameFileAndCollectAllITRs("C:\\DownloadITR\\Transaction_Ids.csv",totalitr);
		}
		FileRename fr= new FileRename();
		fr.enterITRstoExcel(totalitr);
		}}


