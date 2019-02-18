package org.fwcms.cdc.vdr;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileReader;
import java.util.HashMap;

import org.fwcms.initiators.VDRInitiator;
import org.fwcms.pages.CommonElements;
import org.fwcms.pages.cdc.LoginPage;
import org.fwcms.pages.cdc.vdr.HomePage;
import org.fwcms.pages.cdc.vdr.SearchVdrPage;
import org.fwcms.util.EmployerCredentialsProp;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchVDRTest extends VDRInitiator {
	
	@DataProvider(name="vdrValidationData")
	public Object[][] vdrApplicationData() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(SearchVDRTest.class.getResource("/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	
	@Test(dataProvider="vdrValidationData")
	public void testSearchVDRFlow(HashMap<String, String> data) throws Exception {
		WebDriver driver = getDriver();	
		driver.get(cdcUrl);	
		new LoginPage(getDriver()).initElements().loginToCDC(EmployerCredentialsProp.getVdrEmployerUserName().trim(), EmployerCredentialsProp.getVdrEmployerPassword().trim());
		CommonElements cm = new CommonElements(driver).initElements();
		
		HomePage hp = new HomePage(driver).initElements();
		hp.clickSearchContinuePrintAbortLinkInActions();
		
		SearchVdrPage svp=new SearchVdrPage(driver).initElements();
		svp.verifySearchVdrPageTitle();
		svp.verifyElementsPresentInSearchVdrPage();
		svp.verifyIfSearchByApplicationIdIsDefaultOption();
		svp.verifyRadioOptionsInSearchVdrPage();
		
		svp.performIrrelevantSearch(data.get("kdnValidData"));
		cm.waitForOverlayToHide();
		svp.verifyIfNoResultsFoundIsDisplayed();
		
		svp.searchVdrByKdnReference(data.get("kdnValidData"));
		cm.waitForOverlayToHide();
		svp.verifyVisibilityOfSearchResults();
		String applicationId=svp.retrieveApplicationId();
		
		svp.performIrrelevantSearch(applicationId);
		cm.waitForOverlayToHide();
		svp.verifyIfNoResultsFoundIsDisplayed();
		
		svp.searchVdrByApplicationId(applicationId);
		cm.waitForOverlayToHide();
		svp.verifyVisibilityOfSearchResults();
	}	

}
