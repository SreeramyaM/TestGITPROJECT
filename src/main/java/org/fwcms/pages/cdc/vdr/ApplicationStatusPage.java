package org.fwcms.pages.cdc.vdr;





import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ApplicationStatusPage {
private static final Logger logger = LogManager.getLogger(ApplicationStatusPage.class.getName());
	
	private BrowserBot browser;
	private WebDriver driver;
	
	public ApplicationStatusPage(WebDriver driver){
		this.driver = driver;
		browser = new BrowserBot(driver);
	}
	
	public ApplicationStatusPage initElements() {
		return PageFactory.initElements(driver, ApplicationStatusPage.class);
	}
	
	@FindBy(css="div[class='pageHeading']>span")
	private WebElement pageHeader;	
	
	@FindBy(css="div[id='dk_container_vdr_application_status']>a[class='dk_toggle']")
	private WebElement dropDownStatus;
	
	@FindBy(xpath="//div[@id='dk_container_vdr_application_status']//li")
	private WebElement optionsApplicationStatus;
	
	@FindBy(id="search_vdr_apps")
	private WebElement butttonSearch;
	
	@FindBy(id="vdr_applications")
	private WebElement tableSearchResults;
	
	@FindBy(css="li[title='Saved']")
	private WebElement dropDownStatusSaved;
	
	@FindBy(css="li[title='Aborted']")
	private WebElement dropDownStatusAborted;
	
	@FindBy(css="td[aria-describedby='vdr_applications_vdrApplicationStatus']")
	private WebElement tableApplicationStatus;
	
	@FindBy(css="img[class='ui-datepicker-trigger']")
	private WebElement datePickerFromDate;
	
	@FindBy(xpath="//input[@id='vdr_to_date']/following-sibling::img")
	private WebElement datePickerToDate;
	
	@FindBy(css="a[title='Prev']")
	private WebElement buttonPreviousInDatePicker;
	
	@FindBy(css="a[title='Next']")
	private WebElement buttonNextInDatePicker;
	
	@FindBy(css="td[class$='ui-datepicker-today']>a")
	private WebElement currentDate;
	
	@FindBy(xpath="//a[text()='1']")
	private WebElement dateOnePreviousMonth;
	
	@FindBy(className="formErrorContent")
	private WebElement errorInvalidDate;
	
	@FindBy(css="span[class='_icons abort']")
	private WebElement iconAbort;
	
	@FindBy(css="div[class='vdr_generation_ack_popup popups']>div")
	private WebElement abortBox;
	
	@FindBy(css = "div#cancel_worker_deletion>span:nth-child(2)>span")
	private WebElement abortNoButton;

	@FindBy(css = "div#approve_worker_deletion>span:nth-child(2)>span")
	private WebElement abortYesButton;
	
	@FindBy(id="jqgh_vdr_applications_vdrApplicationId")
	private WebElement applicationIdHeader;
	
	boolean flag;
	int i,j;
	
	//Verify the header of the page
	public void verifySearchVdrByApplicationStatusPageTitle() throws Exception {
		logger.info("Verifying the header of the page");
		browser.waitForVisible(pageHeader);
		browser.verifyText(pageHeader, "Applications Status");
	}
	
	//Click on search button
	public void clickSearchButton() throws Exception {
		logger.info("Clicking on search button");
		browser.click(butttonSearch);
	}
	
	//Search vdr applications irrespective of the status
	public void searchAllStatusVdrApplications() throws Exception {
		logger.info("Searching for vdr applications irrespective of the status");
		clickSearchButton();
	}
	
	//Verify if the search results are displayed
	public void verifyIfSearchResultsAreDisplayed() throws Exception {
		logger.info("Verifying if the search results are displayed");
		browser.verifyVisible(tableSearchResults);
	}
	
	//Select the status 'Saved' from status dropdown
	public void selectSavedStatusFromDropDown() throws Exception {
		logger.info("Selecting the status 'Saved' from dropdown");
		browser.click(dropDownStatus);
		browser.actions().moveToElement(dropDownStatusSaved).click(dropDownStatusSaved).build().perform();
		clickSearchButton();
	}
	
	//Select the status 'Aborted' from status dropdown
	public void selectAbortedStatusFromDropDown() throws Exception {
		logger.info("Selecting the status 'Aborted' from dropdown");
		browser.click(dropDownStatus);
		browser.actions().moveToElement(dropDownStatusAborted).click(dropDownStatusAborted).build().perform();
		browser.click(datePickerFromDate);
		browser.click(buttonNextInDatePicker);
		browser.click(buttonNextInDatePicker);
		browser.click(buttonNextInDatePicker);
		browser.click(currentDate);
		clickSearchButton();
	}
	
	//Verify the status of vdr applications listed
	public void verifyStatusOfVdrApplication(String applicationId, String status) throws Exception {
		logger.info("Verifying the status of vdr applications");
		flag=browser.findElement(By.xpath("//td[text()='"+applicationId+"']/following-sibling::td[@aria-describedby='vdr_applications_vdrApplicationStatus']")).getText().contains(status);
		browser.verifyTrue(flag);
	}
	
	//Date Validations
	public void dateValidations() throws Exception {
		logger.info("Validating dates");
		browser.click(datePickerFromDate);
		browser.click(buttonNextInDatePicker);
		browser.click(buttonNextInDatePicker);
		browser.click(buttonNextInDatePicker);
		browser.click(currentDate);
		
		browser.click(datePickerToDate);
		browser.click(buttonPreviousInDatePicker);
		browser.click(dateOnePreviousMonth);
		browser.verifyVisible(errorInvalidDate);
		browser.verifyText(errorInvalidDate, "To date should be on or after of from date");
		clickSearchButton();
		browser.verifyVisible(errorInvalidDate);
		browser.verifyText(errorInvalidDate, "Select a date");
		
	}
	
	//Method to abort the saved vdr application
	public void abortSavedVDRApplication(String applicationId) throws Exception{
		logger.info("clicking on the saved vdr application with id "+applicationId);
		browser.click(browser.findElement(By.xpath("//td[text()='"+applicationId+"']/following-sibling::td[@aria-describedby='vdr_applications_vdrApplicationStatus']")));
		logger.info("waiting for abort icon to be visible");
		browser.waitForVisible(iconAbort);
		logger.info("clicking on abort");
		browser.click(iconAbort);
		logger.info("waiting for the abort pop up to be visible");
		browser.waitForVisible(abortBox);
		Assert.assertTrue(abortNoButton.isDisplayed());
		Assert.assertTrue(abortYesButton.isDisplayed());
		browser.click(abortYesButton);
	}
	
	//Method to sort the application id in descending order
	public void sortApplicationId() throws Exception{
		logger.info("sorting the vdr application id by descending order");
		browser.click(applicationIdHeader);
		browser.click(applicationIdHeader);
	}
	
	
		
}
