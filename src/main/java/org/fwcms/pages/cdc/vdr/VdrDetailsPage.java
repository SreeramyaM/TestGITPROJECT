package org.fwcms.pages.cdc.vdr;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VdrDetailsPage {
	
	private static final Logger logger = LogManager.getLogger(VdrDetailsPage.class.getName());
	
	private BrowserBot browser;
	private WebDriver driver;
	
	public VdrDetailsPage(WebDriver driver){
		this.driver = driver;
		browser = new BrowserBot(driver);
	}
	
	public VdrDetailsPage initElements()
	{
		return PageFactory.initElements(driver, VdrDetailsPage.class);
	}
	
	@FindBy(className="vdr_step_instructions")
	private WebElement vdrStepInstructions;
	
	@FindBy(id="worker_name_header")
	private WebElement kdnRefNumberHeader;
	
	@FindBy(css="div[class^='listRow_header']>div:nth-child(2)")
	private WebElement expiryDateHeader;
	
	@FindBy(css="div[class^='listRow_header']>div:nth-child(3)")
	private WebElement sectorHeader;
	
	@FindBy(css="div[class='nationality_td_field header']")
	private WebElement quotaApprovedHeader;
	
	@FindBy(css="span[class='refresh_text']>a")
	private WebElement refreshListLink;
	
	@FindBy(id="kdn_ref_search")
	private WebElement kdnSearchTextBox;
	
	@FindBy(css="span[class$='search_workername']>a")
	private WebElement kdnSearchLink;
	
	@FindBy(id="kdnNo")
	private List<WebElement> kdnNumberList;
	
	@FindBy(className="noresults")
	private WebElement noResults;
	
	@FindBy(css="div#footer")
	private WebElement footer;
	
	@FindBy(css="div[class^='kdn_ref_searchformError']>div.formErrorContent")
	private WebElement searchKDNFormError;
	
	@FindBy(className="kdn_details_list")
	private WebElement kdnList;
	
	@FindBy(className="vdr-home")
	private WebElement imageHome;
	
	public void searchByKdnNumber(String kdnNumber) throws Exception{
		logger.info("searching kdn by kdnNumber");
		browser.waitForEditable(kdnSearchTextBox);
		browser.type(kdnSearchTextBox, kdnNumber);
		browser.click(kdnSearchLink);
		browser.Wait(2);
		Iterator<WebElement> kdnNumbers = kdnNumberList.iterator();
		boolean comparedKdnNumber = false;
		while(kdnNumbers.hasNext()){
			WebElement eachElement = kdnNumbers.next();
			if(browser.isVisible(eachElement)){
				comparedKdnNumber = true;
				String kdnTitle = browser.getAttribute(eachElement, "title");
				if(!kdnTitle.contains(kdnNumber)){
					browser.verifyFail("search not matching with kdn number "+kdnNumber+" with actual kdn number "+kdnTitle);
				}
			}
		}
		browser.verifyTrue(comparedKdnNumber, "No data for the given kdn number "+kdnNumber);
	}
	
	public void selectKdnNumberFirstVisibleInList(String kdnNumber) throws Exception{
		searchByKdnNumber(kdnNumber);
		Iterator<WebElement> kdnNumbers = kdnNumberList.iterator();
		boolean clickedKdn = false;
		int i=0;
		while(kdnNumbers.hasNext()){
			WebElement eachKdnNumber =  kdnNumbers.next();
			if(browser.isVisible(eachKdnNumber)){
				clickedKdn = true;
				browser.click(kdnNumberList.get(i));
				break;
			}
			i++;
		}
		browser.assertTrue(clickedKdn, "Could not click Kdn number '"+kdnNumber+"' in the list");
	}
	
	public void verifyElementsInVDRDetailsPage() throws Exception{
		logger.info("verifying elements in new vdr application page");
		browser.verifyVisible(vdrStepInstructions);
		browser.verifyText(vdrStepInstructions, "Please click on the preferred KDN details row to proceed further.");
		browser.verifyVisible(kdnRefNumberHeader);
		browser.verifyVisible(expiryDateHeader);
		browser.verifyVisible(sectorHeader);
		browser.verifyVisible(quotaApprovedHeader);
		browser.verifyEditable(refreshListLink);
		browser.verifyEditable(kdnSearchTextBox);
		browser.verifyEditable(kdnSearchLink);
		browser.verifyVisible(footer);
	}
	
	//Method to validate the search KDN field
	public void validateSearchKDN(String searchKDNEmptyExpectedMessage, String kdnMinCharData, String kdnMinCharDataExpectedMessage,String kdnInvalidData, String kdnInvalidDataExpectedMessage, String kdnNoResultsData) throws Exception{
		logger.info("validating search KDN field");
		logger.info("moving focus to search KDN");
		browser.focus(kdnSearchTextBox);
		logger.info("clicking on search button");
		browser.click(kdnSearchLink);
		logger.info("waiting for search KDN form error");
		browser.waitForVisible(searchKDNFormError);
		logger.info("verifying text in search KDN form error");
		browser.verifyText(searchKDNFormError, searchKDNEmptyExpectedMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);
		
		logger.info("clearing and typing kdnMinCharData '"+kdnMinCharData+"' in search KDN");
		browser.clearAndType(kdnSearchTextBox, kdnMinCharData);
		logger.info("clicking on search button");
		browser.click(kdnSearchLink);
		logger.info("waiting for search KDN form error");
		browser.waitForVisible(searchKDNFormError);
		logger.info("verifying text in search KDN form error");
		browser.verifyText(searchKDNFormError, kdnMinCharDataExpectedMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);
		
		logger.info("clearing and typing kdnInvalidData '"+kdnInvalidData+"' in search KDN");
		browser.clearAndType(kdnSearchTextBox, kdnInvalidData);
		logger.info("clicking on search button");
		browser.click(kdnSearchLink);
		logger.info("waiting for search KDN form error");
		browser.waitForVisible(searchKDNFormError);
		logger.info("verifying text in search KDN form error");
		browser.verifyText(searchKDNFormError, kdnInvalidDataExpectedMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);
		
		logger.info("clearing and typing kdnNoResultsData '"+kdnNoResultsData+"' in search KDN");
		browser.clearAndType(kdnSearchTextBox, kdnNoResultsData);
		logger.info("clicking on search button");
		browser.click(kdnSearchLink);
		logger.info("Waiting for No Results Found message");
		browser.verifyVisible(noResults);
		browser.clear(kdnSearchTextBox);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);
		
	}
	
	//Method to verify if kdn list is displayed
	public void verifyIfKdnListISDisplayed() throws Exception {
		logger.info("verifying if kdn list is visible");
		browser.waitForVisible(kdnList);
		browser.verifyVisible(kdnList);
	}
	
	//Method to click on Home icon
	public void clickOnHomeIcon() throws Exception {
		logger.info("clicking on Home icon");
		browser.waitForVisible(imageHome);
		browser.Wait(5);
		browser.click(imageHome);
	}
	
}
