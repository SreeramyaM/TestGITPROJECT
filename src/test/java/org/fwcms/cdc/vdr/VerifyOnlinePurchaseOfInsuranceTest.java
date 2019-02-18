package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileReader;
import java.util.HashMap;

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
import org.fwcms.util.MySqlAccess;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VerifyOnlinePurchaseOfInsuranceTest extends VDRInitiator {
	
	@DataProvider(name="vdrManualEntryHappyFlow")
	public Object[][] vdrApplicationData() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(VerifyOnlinePurchaseOfInsuranceTest.class.getResource("/org/fwcms/cdc/vdr/vdrManualEntryHappyFlow.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	@Test(dataProvider="vdrManualEntryHappyFlow")
	public void testOnlinePurchaseOfInsurance(HashMap<String, String> data) throws Exception {
		WebDriver driver = getDriver();	
		driver.get(cdcUrl);	
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		 
		WorkerSelectionPage workerSelection= new WorkerSelectionPage(driver).initElements();
		cm.waitForOverlayToHide(); 
		vdp.selectKdnNumberFirstVisibleInList(data.get("KDNNumber"));
		workerSelection.enterAgentIdAndVerifyAgentDetailsPopup(data.get("agentName"));
		workerSelection.selectEnterWorkerDetailsManuallyRadioButton();
		workerSelection.clickAddWorkers(); 
		workerSelection.enterPassportNumber(data.get("passportNumber"));
		workerSelection.clickSearchButtonInPassportEntryWindow();
		workerSelection.selectWorkerCheckBox();
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
		purchaseInsurance.selectPurchaseInsuranceOption("Online");
		purchaseInsurance.verifyElementsInOnlinePurchaseInsuranceOption();
		
		purchaseInsurance.selectPurchaseInsuranceOption("Online");
		purchaseInsurance.selectFirstOnlinePurchaseInsuranceCompany();
		purchaseInsurance.verifyConfirmInsurancePurchasePopUp();
		
		purchaseInsurance.generateTransactionIds();
		purchaseInsurance.clickIUnderstandButton();
		purchaseInsurance.selectPurchaseInsuranceOption("Online");
		purchaseInsurance.selectFirstOnlinePurchaseInsuranceCompany();
		purchaseInsurance.clickConfirmInConfirmInsurancePurchasePopUp();
		
	}
	

}
