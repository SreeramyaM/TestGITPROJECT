package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileReader;
import java.util.HashMap;

import org.fwcms.initiators.VDRInitiator;
import org.fwcms.pages.CommonElements;
import org.fwcms.pages.cdc.LoginPage;
import org.fwcms.pages.cdc.vdr.HomePage;
import org.fwcms.pages.cdc.vdr.VdrDetailsPage;
import org.fwcms.pages.cdc.vdr.WorkerSelectionPage;
import org.fwcms.prop.cdc.vdrApplicationPageProp;
import org.fwcms.util.EmployerCredentialsProp;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VDRClearListFunctionalityTest extends VDRInitiator {
	@DataProvider(name="vdrValidationData")
	public Object[][] clearListFunctionalityInWorkerSelection() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(VDRClearListFunctionalityTest.class.getResource("/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	public static String wdCSVFilePath;
	
	@Test(dataProvider="vdrValidationData")
	public void verifyClearListFunctionalityInWorkerSelection(HashMap<String, String> data) throws Exception {
		wdCSVFilePath=System.getProperty("user.dir")+"\\src\\test\\resources\\org\\fwcms\\cdc\\vdr\\csvValidations\\wd_ValidAndInvalid_NNS.csv";
		
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
		wsp.enterAgentIdAndVerifyAgentDetailsPopup(data.get("agentIdValidData"));
		wsp.attachWorkersAndClickSearch(wdCSVFilePath);
		
		wsp.selectWorkerCheckBox();
		wsp.clickSaveButton();  
		wsp.selectSourceCountryEmbassy();	
		wsp.clickOKButton();
		String workersSavedSuccessMessage=vdrApplicationPageProp.getWorkersSavedSuccessMessage();
		cm.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(workersSavedSuccessMessage);
		String appid=wsp.retrieveApplicationId();
		System.out.println(appid);
		wsp.verifyClearListLink();
		wsp.clickAbortButton();
		wsp.verifyAbortButtonPopup();
		wsp.clickAbortButton();
		wsp.clickYesInAbortPopUp();
		vdp.verifyIfKdnListISDisplayed();
		vdp.clickOnHomeIcon();
		hp.verifyIfVdrApplicationIsAborted(appid);
	}
}
