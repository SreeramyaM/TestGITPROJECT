package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileReader;
import java.util.HashMap;

import org.fwcms.initiators.VDRInitiator;
import org.fwcms.pages.CommonElements;
import org.fwcms.pages.cdc.LoginPage;
import org.fwcms.pages.cdc.vdr.HomePage;
import org.fwcms.pages.cdc.vdr.SubmitVDRPage;
import org.fwcms.pages.cdc.vdr.VdrDetailsPage;
import org.fwcms.pages.cdc.vdr.WorkerPointOfEntryPage;
import org.fwcms.pages.cdc.vdr.WorkerSelectionPage;
import org.fwcms.prop.cdc.vdrApplicationPageProp;
import org.fwcms.util.EmployerCredentialsProp;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VDRPreviousButtonFunctionalityTest extends VDRInitiator {
	@DataProvider(name="vdrValidationData")
	public Object[][] deleteWorkersFunctionalityInWorkerPointOfEntry() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(VDRPreviousButtonFunctionalityTest.class.getResource("/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	
	@Test(dataProvider="vdrValidationData")
	public void testPreviousButtonFunctionality(HashMap<String, String> data) throws Exception {
		WebDriver driver = getDriver();	
		driver.get(cdcUrl);	
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		HomePage hp = new HomePage(driver).initElements();
		hp.clickNewVdrLinkInActions();
		cm.waitForOverlayToHide();
		
		VdrDetailsPage vdp = new VdrDetailsPage(driver).initElements();
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
		
		WorkerSelectionPage wsp= new WorkerSelectionPage(driver).initElements();
		wsp.clickTopPreviousButton();
		cm.waitForOverlayToHide();
		vdp.verifyIfKdnListISDisplayed();
		
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data.get("agentIdValidData"));
		wsp.selectEnterWorkerDetailsManuallyRadioButton();
		wsp.clickAddWorkers(); 
		wsp.enterPassportNumber(data.get("passportNumberValidData"));
		wsp.clickSearchButtonInPassportEntryWindow();
		cm.waitForOverlayToHide();
		wsp.clickPreviousButton();
		cm.waitForOverlayToHide();
		vdp.verifyIfKdnListISDisplayed();
		
		vdp.selectKdnNumberFirstVisibleInList(data.get("kdnValidData"));
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data.get("agentIdValidData"));
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
		wsp.clickPreviousButton();
		cm.waitForOverlayToHide();
		vdp.verifyIfKdnListISDisplayed();
		
		vdp.clickOnHomeIcon();
		hp.clickSavedVdrApplication(applicationId);
		cm.waitForOverlayToHide();
		wsp.clickContinueButton();
		
		WorkerPointOfEntryPage wpoep= new WorkerPointOfEntryPage(driver).initElements();
		wpoep.clickPreviousButton();
		cm.waitForOverlayToHide();
		wsp.verifyPageHeader();
		wsp.clickContinueButton();
		cm.waitForOverlayToHide();
		wpoep.enterWorkerPointOfEntry();
		wpoep.clickSaveButton();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		wpoep.clickPreviousButton();
		cm.waitForOverlayToHide();
		wsp.verifyPageHeader();
		wsp.clickContinueButton();
		cm.waitForOverlayToHide();
		wpoep.clickContinueButton();
	
		SubmitVDRPage svp= new SubmitVDRPage(driver).initElements();
		svp.clickPreviousButton();
		cm.waitForOverlayToHide();
		wpoep.verifyWorkerPointOfEntryIsActive();
		wpoep.clickAbortButton();
		wpoep.verifyAbortButtonPopup();
		wpoep.clickAbortButton();
		wpoep.clickYesInAbortPopUp();
		
	}
}
