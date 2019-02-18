package org.fwcms.pages.cdc.vdr;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchVdrPage {
private static final Logger logger = LogManager.getLogger(SearchVdrPage.class.getName());
	
	private BrowserBot browser;
	private WebDriver driver;
	
	public SearchVdrPage(WebDriver driver){
		this.driver = driver;
		browser = new BrowserBot(driver);
	}
	
	public SearchVdrPage initElements() {
		return PageFactory.initElements(driver, SearchVdrPage.class);
	}
	
	@FindBy(css="div[class='pageHeading']>span")
	private WebElement pageHeader;
	
	@FindBy(id="request_id")
	private WebElement textBoxSearchVdr;
	
	@FindBy(id="vdr_app_id")
	private WebElement radioButtonApplicationId;
	
	@FindBy(id="vdr_kdn_no")
	private WebElement radioButtonKdnReference;
	
	@FindBy(id="search_vdr_btn")
	private WebElement buttonSearchVdr;
	
	@FindBy(css="div[class='listRow']")
	private WebElement searchResults;
	
	@FindBy(className="noresults")
	private WebElement textNoResultsFound;
	
	@FindBy(css="div[title='View Worker Details']")
	private WebElement iconViewWorkerDetails;
	
	@FindBy(className="worker_row")
	private WebElement popUpWorkerDetails;
	
	@FindBy(xpath="//span[text()='Ok']")
	private WebElement buttonOkInWorkerDetailsPopUp;
	
	@FindBy(css="div[title='Abort VDR application']")
	private WebElement iconAbortApplication;
	
	@FindBy(css="div[class='vdr_generation_ack_popup popups']")
	private WebElement comfirmationAlertAbortVdr;
	
	@FindBy(xpath="//div[@class='vdr_generation_ack_popup popups']//span[text()='No']")
	private WebElement buttonNoComfirmationAlertAbortVdr;
	
	@FindBy(css="div[title='Resume VDR application']")
	private WebElement iconResumeApplication;
	
	@FindBy(css="div[class='vdr_status data']>span")
	private WebElement applicationStatus;
	
	@FindBy(css="div.listRow:nth-child(1)>div[class='name_td_field visa_data data']")
	private WebElement applicationIdOfFirstSearchResult;
	
	//Verify the header of the page
	public void verifySearchVdrPageTitle() throws Exception {
		logger.info("Verifying the header of the page");
		browser.waitForVisible(pageHeader);
		browser.verifyText(pageHeader, "Search VDR");
	}
	
	//Verify elements present in search vdr page
	public void verifyElementsPresentInSearchVdrPage() throws Exception{
		logger.info("Verifying the elements present in Search VDR Page");
		browser.verifyEditable(textBoxSearchVdr);
		browser.verifyVisible(buttonSearchVdr);
		browser.verifyEditable(buttonSearchVdr);
		browser.verifyEditable(radioButtonApplicationId);
		browser.verifyEditable(radioButtonKdnReference);
	}
		
	//Verify if search by application id is the default selected option
	public void verifyIfSearchByApplicationIdIsDefaultOption() throws Exception {
		logger.info("Verify if search by application id is the default selected option");
		browser.verifyChecked(radioButtonApplicationId);
		browser.verifyNotChecked(radioButtonKdnReference);
	}
	
	//Verify the radio options in search vdr page
	public void verifyRadioOptionsInSearchVdrPage() throws Exception {
		logger.info("Verifying the radio options present in search vdr page");
		browser.click(radioButtonKdnReference);
		browser.verifyChecked(radioButtonKdnReference);
		browser.verifyNotChecked(radioButtonApplicationId);
		
		browser.click(radioButtonApplicationId);
		browser.verifyChecked(radioButtonApplicationId);
		browser.verifyNotChecked(radioButtonKdnReference);
	}
	
	//Search Vdr by application id
	public void searchVdrByApplicationId(String applicationId) throws Exception {
		logger.info("Searching vdr using application id");
		browser.Wait(1);
		browser.click(radioButtonApplicationId);
		browser.clearAndType(textBoxSearchVdr, applicationId);
		browser.click(buttonSearchVdr);
	}
	
	//Search Vdr by KDN reference
	public void searchVdrByKdnReference(String kdnReference) throws Exception {
		logger.info("Searching a vdr using kdn reference");
		browser.click(radioButtonKdnReference);
		browser.clearAndType(textBoxSearchVdr, kdnReference);
		browser.click(buttonSearchVdr);
	}
	
	//Verify the visibility of search results
	public void verifyVisibilityOfSearchResults() throws Exception {
		logger.info("Verifying the visibility of search results");
		browser.verifyVisible(searchResults);
	}
	
	//Verify the search results when irrelevant search is performed
	public void verifyIfNoResultsFoundIsDisplayed() throws Exception {
		logger.info("Verify if 'No Results Found' is displayed");
		browser.verifyVisible(textNoResultsFound);
	}
	
	//Perform irrelevant search
	public void performIrrelevantSearch(String reference) throws Exception {
		logger.info("typing "+reference+" in Search VDR text box");
		browser.clearAndType(textBoxSearchVdr, reference);
		browser.Wait(1);
		browser.click(buttonSearchVdr);
	}
	
	//Verify the actions present for saved vdr application
	public void verifyActionsForSavedVdrApplications(String savedVdrApplicationId) throws Exception {
		logger.info("Verifying the actions available for saved vdr application");
		browser.verifyVisible(iconViewWorkerDetails);
		verifyDisplayOfWorkerDetailsPopUp();
		
		browser.verifyVisible(iconAbortApplication);
		browser.click(iconAbortApplication);
		browser.verifyVisible(comfirmationAlertAbortVdr);
		browser.click(buttonNoComfirmationAlertAbortVdr);
				
		browser.verifyVisible(iconResumeApplication);
		browser.click(iconResumeApplication);
	}
	
	//Verify the actions present for aborted vdr application
	public void verifyActionsForAbortedVdrApplications(String abortedVdrApplicationId) throws Exception {
		searchVdrByApplicationId(abortedVdrApplicationId);
		logger.info("Verifying the actions available for aborted vdr application");
		try{
			browser.Wait(2);
			browser.verifyVisible(iconViewWorkerDetails);
			verifyDisplayOfWorkerDetailsPopUp();
			
			browser.verifyElementNotPresent(iconAbortApplication);
			browser.verifyElementNotPresent(iconResumeApplication);
		}catch(NoSuchElementException e) {
			System.out.println("AbortApplication & ResumeApplication icons not present for this vdr");
		}
	}
	
	//Verify if worker details pop up is displayed on clicking View Worker Details icon
	public void verifyDisplayOfWorkerDetailsPopUp() throws Exception {
		logger.info("Verifying if worker details pop up is displayed");
		browser.Wait(1);
		browser.click(iconViewWorkerDetails);
		browser.verifyElementPresent(popUpWorkerDetails);
		browser.click(buttonOkInWorkerDetailsPopUp);
	}
	
	//Method to retrieve the application id of the first search result
	public String retrieveApplicationId() throws Exception {
		logger.info("retrieving the application id of the first search result");
		String applicationId=browser.getAttribute(applicationIdOfFirstSearchResult, "title");
		return applicationId;
	}
	
}
