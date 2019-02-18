package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileReader;
import java.util.HashMap;

import org.fwcms.initiators.VDRInitiator;
import org.fwcms.pages.CommonElements;
import org.fwcms.pages.cdc.LoginPage;
import org.fwcms.pages.cdc.vdr.HomePage;
import org.fwcms.pages.cdc.vdr.NewVdrCreatePage;
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

public class VerifyElementsPresentInVDRPagesTest extends VDRInitiator{
	@DataProvider(name="vdrValidationData")
	public Object[][] vdrApplicationData() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(VerifyElementsPresentInVDRPagesTest.class.getResource("/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	
	@Test(dataProvider="vdrValidationData")
	public void verifyVDRPageElements(HashMap<String, String> data) throws Exception {
		WebDriver driver = getDriver();
		driver.get(cdcUrl);
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		
		HomePage hp = new HomePage(driver).initElements();
		hp.verifyActionsTabCollapsedAndVisible();
		hp.verifyVdrOptionsInMenuActionsTab();
	 	hp.clickNewVdrLinkInActions();
	 	
	 	cm.waitForOverlayToHide();
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		vdp.verifyElementsInVDRDetailsPage();
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
		cm.waitForOverlayToHide();
		
		NewVdrCreatePage nvcp = new NewVdrCreatePage(driver).initElements();
		nvcp.verifyVDRApplicationHeader();
		nvcp.verifyNavigationStepAreVisibleAndNameOfStep();
		
		WorkerSelectionPage wsp= new WorkerSelectionPage(driver).initElements();
		wsp.verifyWorkerSelectionIsActive();
		wsp.verifyElementsPresentInWorkerSelection();
		wsp.verifyUploadCsvOptionSelectedDefaultInWorkerSelection();
		wsp.verifyRadioOptionsFunctionalityInWorkerSelection();
		wsp.checkPreviousButtonDisplay();
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data.get("agentIdValidData"));
	 	wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers(); 
		wsp.verifyElementsPresentInPassportEntryWindow();
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();	
				 
		wsp.selectWorkerCheckBox();
		wsp.verifyWorkersCountInAddMoreLink();
		wsp.verifyIfWorkerDetailsPopUpIsDisplayed();
		wsp.verifySaveButtonDisplay();
		wsp.verifyElementsInWorkerSelectionAfterVdrWorkersUploaded();
		String bottomInfoMessage=vdrApplicationPageProp.getWorkersSelectionPageBottomInfoMessage();
		wsp.verifyBottomInfoMessageDisplay(bottomInfoMessage);
		wsp.clickSaveButton();
		wsp.selectSourceCountryEmbassy();
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		wsp.verifyIfSourceCountryEmbassyIsDisplayed();
		wsp.verifyIfDeleteWorkersButtonIsDisplayed();
		wsp.verifyContinueButtonDisplay();
		wsp.verifyAbortButtonDisplay();
		wsp.clickContinueButton();
		
		WorkerPointOfEntryPage wpoep= new WorkerPointOfEntryPage(driver).initElements();
		wpoep.verifyWorkerPointOfEntryIsActive();
	 	wpoep.enterWorkerPointOfEntry();
		wpoep.verifyElementsPresentInWorkerPointOfEntryPage();
		wpoep.verifyWorkersCountInAddMoreLink();
		wpoep.verifyIfWorkerDetailsPopUpIsDisplayed();
		wpoep.clickSaveButton();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		
		wpoep.verifyContinueButtonDisplay();
		wpoep.verifyAbortButtonDisplay();
		wpoep.clickContinueButton();
		
		SubmitVDRPage svp= new SubmitVDRPage(driver).initElements();
		svp.verifySubmitVDRIsActive();
		svp.isDeclarationCheckboxUnSelectedByDefault();
		svp.verifySubmitButtonDisplay();
		svp.selectDeclarationCheckBox();
		svp.verifyElementsPresentInSubmitVDRPage();
		svp.verifyNationality();
		svp.verifyFooterDisplay();
		svp.verifyPreviousButtonDisplay();
		svp.verifySubmitButtonDisplay();
		svp.verifyAbortButtonDisplay();
		svp.clickSubmitButton();
		svp.verifySubmitButtonConfirmationPopup();
		
		String vdrSubmissionMessage=vdrApplicationPageProp.getVdrSubmissionMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(vdrSubmissionMessage);
		String oneTimePassword=new MySqlAccess().getOTPOfEmpForVdrSubmission(EmployerCredentialsProp.getVdrEmployerUserName().trim());
		System.out.println(oneTimePassword);
		svp.verifyElementsPresentInConfirmOTPPopUp();
		svp.enterAndConfirmOTP(oneTimePassword);
		cm.waitForOverlayToHide();
		
		PurchaseInsurancePage pip=new PurchaseInsurancePage(driver).initElements();
		pip.verifyPurchaseInsuranceIsActive();
		pip.verifyElementsPresentInPurchaseInsurancePage();
		pip.verifyIfWorkersCheckboxIsDisabled();
		pip.clickAbortButton();
		pip.verifyAbortButtonPopup();
		pip.clickAbortButton();
		pip.clickYesInAbortPopUp();
	}
	
}
