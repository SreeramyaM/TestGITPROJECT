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

public class VDRAbortFunctionalityTest extends VDRInitiator {
	@DataProvider(name = "vdrValidationData")
	public Object[][] vdrApplicationData() throws Exception {
		JSONArray ja = (JSONArray) new JSONParser()
				.parse(new FileReader(VDRAbortFunctionalityTest.class.getResource(
						"/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for (int i = 0; i < ja.size(); i++) {
			result[i][0] = ja.get(i);
		}
		return result;
	}

	public static final String cdcUrl = configProp.getProperty("cdcUser");
	
	//Abort Functionality - Worker Selection Page
	@Test(dataProvider = "vdrValidationData", priority=0)
	public void testAbortFunctionalityWorkerSelectionPage(HashMap<String, String> data)
			throws Exception {
		WebDriver driver = getDriver();
		driver.get(cdcUrl);
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		cm.waitForOverlayToHide();
		
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
				
		WorkerSelectionPage wsp = new WorkerSelectionPage(driver)
				.initElements();
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data
				.get("agentIdValidData"));
		wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers();
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();
		wsp.selectWorkerCheckBox();
		wsp.clickSaveButton();
		wsp.selectSourceCountryEmbassy();
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		String applicationId=wsp.retrieveApplicationId();
		wsp.verifyAbortButtonDisplay();
		wsp.clickAbortButton();
		wsp.verifyAbortButtonPopup();
		wsp.clickAbortButton();
		wsp.clickYesInAbortPopUp();
		vdp.verifyIfKdnListISDisplayed();
		vdp.clickOnHomeIcon();
		hp.verifyIfVdrApplicationIsAborted(applicationId);
	}
	
	//Abort Functionality - Worker Point Of Entry Page
	@Test(dataProvider = "vdrValidationData",priority=1)
	public void testAbortFunctionalityWorkerPointOfEntryPage(HashMap<String, String> data)
			throws Exception {
		WebDriver driver = getDriver();
		driver.get(cdcUrl);
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		cm.waitForOverlayToHide();
		
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
				
		WorkerSelectionPage wsp = new WorkerSelectionPage(driver)
				.initElements();
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data
				.get("agentIdValidData"));
		wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers();
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();
		wsp.selectWorkerCheckBox();
		wsp.clickSaveButton();
		wsp.selectSourceCountryEmbassy();
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		String applicationId=wsp.retrieveApplicationId();
		wsp.clickContinueButton();
		
		WorkerPointOfEntryPage wpoep= new WorkerPointOfEntryPage(driver).initElements();
		wpoep.clickAbortButton();
		wpoep.verifyAbortButtonPopup();
		wpoep.clickAbortButton();
		wpoep.clickYesInAbortPopUp();
		vdp.verifyIfKdnListISDisplayed();
		vdp.clickOnHomeIcon();
		hp.verifyIfVdrApplicationIsAborted(applicationId);
	}
	
	//Abort Functionality - Submit VDR Page
	@Test(dataProvider = "vdrValidationData",priority=2)
	public void testAbortFunctionalitySubmitVDRPage(HashMap<String, String> data)
				throws Exception {
		WebDriver driver = getDriver();
		driver.get(cdcUrl);
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		cm.waitForOverlayToHide();
		
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
			
		WorkerSelectionPage wsp = new WorkerSelectionPage(driver)
					.initElements();
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data
					.get("agentIdValidData"));
		wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers();
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();
		wsp.selectWorkerCheckBox();
		wsp.clickSaveButton();
		wsp.selectSourceCountryEmbassy();
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		String applicationId=wsp.retrieveApplicationId();
		wsp.clickContinueButton();
			
		WorkerPointOfEntryPage wpoep= new WorkerPointOfEntryPage(driver).initElements();
		wpoep.enterWorkerPointOfEntry();
		wpoep.clickSaveButton();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		wpoep.clickContinueButton();
		
		SubmitVDRPage svp= new SubmitVDRPage(driver).initElements();
		svp.clickAbortButton();
		svp.verifyAbortButtonPopup();
		svp.clickAbortButton();
		svp.clickYesInAbortPopUp();
		vdp.verifyIfKdnListISDisplayed();
		vdp.clickOnHomeIcon();
		hp.verifyIfVdrApplicationIsAborted(applicationId);
	}
	
	//Abort Functionality - Purchase Insurance Page
	@Test(dataProvider = "vdrValidationData",priority=3)
	public void testAbortFunctionalityPurchaseInsurancePage(HashMap<String, String> data)
				throws Exception {
		WebDriver driver = getDriver();
		driver.get(cdcUrl);
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		cm.waitForOverlayToHide();
		
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
			
		WorkerSelectionPage wsp = new WorkerSelectionPage(driver)
					.initElements();
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data
					.get("agentIdValidData"));
		wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers();
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();
		wsp.selectWorkerCheckBox();
		wsp.clickSaveButton();
		wsp.selectSourceCountryEmbassy();
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		String applicationId=wsp.retrieveApplicationId();
		wsp.clickContinueButton();
			
		WorkerPointOfEntryPage wpoep= new WorkerPointOfEntryPage(driver).initElements();
		wpoep.enterWorkerPointOfEntry();
		wpoep.clickSaveButton();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		wpoep.clickContinueButton();
		
		SubmitVDRPage svp= new SubmitVDRPage(driver).initElements();
		svp.selectDeclarationCheckBox();
		svp.clickSubmitButton();
		svp.verifySubmitButtonConfirmationPopup();
		String vdrSubmissionMessage=vdrApplicationPageProp.getVdrSubmissionMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(vdrSubmissionMessage);
		
		String oneTimePassword=new MySqlAccess().getOTPOfEmpForVdrSubmission(EmployerCredentialsProp.getVdrEmployerUserName().trim());
		svp.enterAndConfirmOTP(oneTimePassword);
		cm.waitForOverlayToHide();
		
		PurchaseInsurancePage pip=new PurchaseInsurancePage(driver).initElements();
		pip.clickAbortButton();
		pip.verifyAbortButtonPopup();
		pip.clickAbortButton();
		pip.clickYesInAbortPopUp();
		vdp.verifyIfKdnListISDisplayed();
		vdp.clickOnHomeIcon();
		hp.verifyIfVdrApplicationIsAborted(applicationId);
		       
	}
}
