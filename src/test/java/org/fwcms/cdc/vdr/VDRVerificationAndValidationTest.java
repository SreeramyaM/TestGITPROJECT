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

public class VDRVerificationAndValidationTest extends VDRInitiator {
	
	@DataProvider(name="vdrValidationData")
	public Object[][] vdrValidationData() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(VDRVerificationAndValidationTest.class.getResource("/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	@Test(dataProvider="vdrValidationData")
	public void testVDRVerificationsAndValidations(HashMap<String, String> data) throws Exception {
		WebDriver driver = getDriver();	
		driver.get(cdcUrl);	
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		String searchKDNEmptyExpectedErrorMessage=vdrApplicationPageProp.getSearchKDNEmptyExpectedErrorMessage();
		String searchKDNMinCharExpectedErrorMessage=vdrApplicationPageProp.getSearchKDNMinCharExpectedErrorMessage();
		String searchKDNInvalidExpectedErrorMessage=vdrApplicationPageProp.getSearchKDNInvalidExpectedErrorMessage();
		vdp.validateSearchKDN(searchKDNEmptyExpectedErrorMessage, data.get("kdnMinCharData"), searchKDNMinCharExpectedErrorMessage, data.get("kdnInvalidData"), searchKDNInvalidExpectedErrorMessage, data.get("kdnNoResultsData"));
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
		cm.waitForOverlayToHide(); 
		
		WorkerSelectionPage wsp= new WorkerSelectionPage(driver).initElements();
		String agentIdInvalidExpectedErrorMessage=vdrApplicationPageProp.getAgentIdInvalidExpectedErrorMessage();
		String agentIdNotFoundExpectedErrorMessage=vdrApplicationPageProp.getAgentIdNotFoundExpectedErrorMessage();
		wsp.validateAgentId(agentIdInvalidExpectedErrorMessage, data.get("agentIdInvalidData"), agentIdNotFoundExpectedErrorMessage);
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data.get("agentIdValidData"));
		
		String projectPath = System.getProperty("user.dir");
		String imageFilePath = projectPath+data.get("imageFilePath");
		String csvFileInvalidExpectedErrorMessage=vdrApplicationPageProp.getCSVFileNotUploadedExpectedErrorMessage();
		String workersNotAddedExpectedErrorMessage=vdrApplicationPageProp.getWorkersNotAddedExpectedErrorMessage();
		wsp.validateWorkerDetails(csvFileInvalidExpectedErrorMessage,workersNotAddedExpectedErrorMessage,imageFilePath);
		
		String passportNumberEmptyExpectedErrorMessage=vdrApplicationPageProp.getPassportEmptyExpectedErrorMessage();
		String passportNumberInvalidExpectedErrorMessage=vdrApplicationPageProp.getPassportSpaceSpecialCharNotAllowedExpectedErrorMessage();
		String workersCannotBeAddedExpectedErrorMessage=vdrApplicationPageProp.getWorkersCannotBeAddedExpectedErrorMessage();
		wsp.validateWorkerPassport(passportNumberEmptyExpectedErrorMessage, data.get("passportNumberSpaceData"), passportNumberInvalidExpectedErrorMessage, data.get("passportNumberSpecialCharData"),workersCannotBeAddedExpectedErrorMessage);
		
		wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers(); 
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();
		wsp.selectWorkerCheckBox();
		
		String searchWorkerEmptyExpectedErrorMessage=vdrApplicationPageProp.getSearchWorkerEmptyExpectedErrorMessage();
		String searchWorkerSpaceExpectedErrorMessage=vdrApplicationPageProp.getSearchWorkerSpaceExpectedErrorMessage();
		String searchWorkerMinCharExpectedErrorMessage=vdrApplicationPageProp.getSearchWorkerMinCharExpectedErrorMessage();
		String searchWorkerSpecialCharExpectedErrorMessage=vdrApplicationPageProp.getSearchWorkerSpecialCharExpectedErrorMessage();
		wsp.validateSearchWorker(searchWorkerEmptyExpectedErrorMessage, data.get("searchWorkerSpaceData"), searchWorkerSpaceExpectedErrorMessage, data.get("searchWorkerMinCharData"), searchWorkerMinCharExpectedErrorMessage, data.get("searchWorkerSpecialCharData"), searchWorkerSpecialCharExpectedErrorMessage);
		
		
		wsp.clickSaveButton();
		//String sourceEmbassyInvalidExpectedErrorMessage=vdrApplicationPageProp.getSourceEmbassyInvalidExpectedErrorMessage();
		//wsp.validateSourceCountryEmbassy(data.get("sourceEmbassyInvalidData"), sourceEmbassyInvalidExpectedErrorMessage);
		//wsp.closeSourceCountryEmbassy();
		//wsp.clickSaveButton();
		wsp.selectSourceCountryEmbassy();
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		wsp.clickContinueButton();
		
		WorkerPointOfEntryPage wpoep= new WorkerPointOfEntryPage(driver).initElements();
		wpoep.validateWorkerPointOfEntry(data.get("workerPointOfEntryInvalidData"));
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
		svp.regenerateOTP();
		String regenerateOTPMessage=vdrApplicationPageProp.getRegenerateOTPSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(regenerateOTPMessage);
		svp.enterAndConfirmOTP(oneTimePassword);
		svp.validateInvalidOTP(vdrApplicationPageProp.getOTPInvalidExpectedErrorMessage());
		
		oneTimePassword=new MySqlAccess().getOTPOfEmpForVdrSubmission(EmployerCredentialsProp.getVdrEmployerUserName().trim());
		svp.enterAndConfirmOTP(oneTimePassword);
		cm.waitForOverlayToHide();
		
		PurchaseInsurancePage pip=new PurchaseInsurancePage(driver).initElements();
		pip.clickAbortButton();
		pip.verifyAbortButtonPopup();
		pip.clickAbortButton();
		pip.clickYesInAbortPopUp();
	}
	

}
