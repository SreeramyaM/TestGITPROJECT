package org.fwcms.pages.cdc.vdr;

import java.util.Iterator;
import java.util.List;
import org.testng.Assert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.fwcms.pages.CommonElements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkerPointOfEntryPage {
	private static final Logger logger = LogManager
			.getLogger(WorkerPointOfEntryPage.class.getName());

	private BrowserBot browser;
	private WebDriver driver;

	public WorkerPointOfEntryPage(WebDriver driver) {
		this.driver = driver;
		browser = new BrowserBot(driver);
	}

	public WorkerPointOfEntryPage initElements() {
		return PageFactory.initElements(driver, WorkerPointOfEntryPage.class);
	}

	@FindBy(css = "div[class='steps_nav']>div")
	private List<WebElement> navigationStepsList;

	@FindBy(css = "span.floatRight.rmargin")
	private WebElement textEnterWorkerPointofEntry;

	@FindBy(xpath = "//div[@id='save_worker_point_of_entry']//span[2]")
	private WebElement saveButton;

	@FindBy(css = "div#submit_worker_vdr>span:nth-child(2)>span")
	private WebElement selectContinueButton;

	@FindBy(xpath = "//div[@id='abortVDRProcess']//span[2]/span")
	private WebElement selectAbortButton;

	@FindBy(className = "searchWorkerWrapper")
	private WebElement searchWorkerWrapper;

	@FindBy(id = "worker_port_id")
	private WebElement workerPointOfEntry;

	@FindBy(css = "a.editEmbassy")
	private WebElement editEmbassyLink;

	@FindBy(css = ".removeallworkers_text>a")
	private WebElement deleteWorkersLink;

	@FindBy(className = "sector")
	private WebElement sector;

	@FindBy(id = "agentNationality")
	private WebElement nationality;

	@FindBy(css = "div[class='addMoreWorkers']>div>a")
	private WebElement addMoreWorkersLink;

	@FindBy(id = "worker_name_header")
	private WebElement workerNameHeader;

	@FindBy(css = "div[class='passport_td_field header']")
	private WebElement passportNumberHeader;

	@FindBy(css = "div[class^='sno_td_field header']")
	private WebElement genderHeader;

	@FindBy(css = "div[class^='med_report_validity']")
	private WebElement medicalExpiryHeader;

	@FindBy(className = "filter_list")
	private WebElement filterHeader;

	@FindBy(css = "span[class='refresh_text']>a")
	private WebElement refreshListLink;

	@FindBy(css = "span[class='clearallworkers_text']>a")
	private WebElement clearListLink;

	@FindBy(id = "worker_name_search")
	private WebElement searchWorkerTextBox;

	@FindBy(css = "span[class$='search_workername']>a")
	private WebElement searchWorkerLink;

	@FindBy(id = "selectedOccupationFromList")
	private WebElement occupations;

	@FindBy(id = "selectedEmbassyFromList")
	private WebElement sourceCountryEmbassy;

	@FindBy(css = "div[id='abortVDRPopup']")
	private WebElement abortBox;

	@FindBy(css = "span.confirm_alert>strong")
	private WebElement abortBoxMessage;

	@FindBy(css = "p.msg_descr")
	private WebElement abortBoxMessageDescription;

	@FindBy(css = "div#cancel_worker_deletion>span:nth-child(2)>span")
	private WebElement abortNoButton;

	@FindBy(css = "div#approve_worker_deletion>span:nth-child(2)>span")
	private WebElement abortYesButton;

	@FindBy(css = "span.removeallworkers_text>a[title='Delete selected Worker(s) from the list']")
	private WebElement deleteWorkersLinkInPopUp;

	@FindBy(css = "div[class^='vdr_generation_ack_popup']")
	private WebElement confirmationBox;

	@FindBy(css = "span.confirm_alert>strong")
	private WebElement confirmationBoxMessage;

	@FindBy(className = "msg_descr")
	private WebElement confirmationBoxMessageDescription;

	//@FindBy(css = "div[id='approve_worker_deletion']>span:nth-child(2)>span")
	@FindBy(xpath="//div[@id='deleteSelectedVDRWorkersPop']//div[@id='approve_worker_deletion']")
	private WebElement confirmationYesButton;

	//@FindBy(css = "div[id='cancel_worker_deletion']>span:nth-child(2)>span")
	@FindBy(xpath="//div[@id='deleteSelectedVDRWorkersPop']//div[@id='cancel_worker_deletion']")
	private WebElement confirmationNoButton;

	@FindBy(css = "div[id='subpanel1']>div[class='filterrow']:nth-child(1)>span")
	private WebElement autoSuggestFirstValueWorkerPointOfEntry;

	@FindBy(css = "div[class$='pointOfEntry']")
	private WebElement pointOfEntryTableEntry;

	@FindBy(css = "div[class='listRow']")
	private List<WebElement> workerRowInWorkerSelectionPage;

	@FindBy(css = "div[class^='workers_list_container']>div>div>div:nth-child(1)")
	private WebElement firstWorkerInList;

	@FindBy(className = "worker_details_popup")
	private WebElement workerDetailsPopup;

	@FindBy(className = "worker_details_popup_close")
	private List<WebElement> workerDetailsPopupClose;
	
	@FindBy(className="close_overlay_embassy")
	private WebElement closeSourceEmbassyWindow;
	
	@FindBy(className="editEmbassy")
	private WebElement editSourceCountryEmbassy;
	
	@FindBy(id = "worker_overlay_embassy_id")
	private WebElement enterSourceCountryEmbassy;
	
	@FindBy(css="div#cancel_point_of_entry>span.mid_btn>span")
	private WebElement previousButton;

	public void enterWorkerPointOfEntry() throws Exception {
		logger.info("typing Worker Point of Entry");
		browser.waitForEditable(workerPointOfEntry);
		browser.click(workerPointOfEntry);
		browser.Wait(1);
		browser.waitForVisible(autoSuggestFirstValueWorkerPointOfEntry);
		browser.click(autoSuggestFirstValueWorkerPointOfEntry);

	}

	public void clickSaveButton() throws Exception {
		logger.info("Clicking Save Button");
		saveButton.click();
	}

	public void checkforSuccessStatusBar() throws Exception {
		logger.info("Checking for Success Status Bar and Message");
		Assert.assertTrue(driver.findElement(
				By.xpath("//div[@class='statusBar success']")).isDisplayed());
		logger.info("Success Status Bar is displayed");
		driver.findElement(By.xpath("//div[@class='statusBar success']"))
				.click();

	}

	public void clickTextEnterWorkerPointOfEntry() throws Exception {
		logger.info("Checking Worker Point of Entry");
		browser.click(textEnterWorkerPointofEntry);
	}

	public void clickContinueButton() throws Exception {
		logger.info("Clicking CONTINUE Button");
		browser.waitForVisible(selectContinueButton);
		selectContinueButton.click();
	}

	/**
	 * Verify worker selection tab is active
	 * 
	 * @throws Exception
	 */
	public void verifyWorkerPointOfEntryIsActive() throws Exception {
		logger.info("verifying worker point of entry is active");
		Iterator<WebElement> navigationSteps = navigationStepsList.iterator();
		int i = 0;
		while (navigationSteps.hasNext()) {
			WebElement eachNavigation = navigationSteps.next();
			String className = browser.getAttribute(eachNavigation, "class");
			if (i == 1) {
				if (!className.contains("active")) {
					browser.verifyFail("Worker point of entry is not active");
				}
			} else {
				if (!className.contains("inactive")) {
					logger.info("Multiple steps are active where only Worker point of entry should be active");
				}
			}
			i++;
		}
	}

	public void verifyElementsPresentInWorkerPointOfEntryPage()
			throws Exception {
		logger.info("verifying elements in worker point of entry page ");
		browser.verifyEditable(textEnterWorkerPointofEntry);
		browser.verifyVisible(addMoreWorkersLink);
		browser.verifyEditable(editEmbassyLink);
		browser.verifyEditable(deleteWorkersLink);
		browser.verifyVisible(sector);
		browser.verifyVisible(nationality);
		browser.verifyVisible(workerNameHeader);
		browser.verifyVisible(passportNumberHeader);
		browser.verifyVisible(genderHeader);
		browser.verifyVisible(medicalExpiryHeader);
		browser.verifyVisible(pointOfEntryTableEntry);
		browser.verifyEditable(refreshListLink);
		browser.verifyEditable(searchWorkerTextBox);
		browser.verifyEditable(searchWorkerLink);
		browser.verifyVisible(saveButton);
		browser.verifyVisible(occupations);
		browser.verifyVisible(sourceCountryEmbassy);

	}

	public void verifyContinueButtonDisplay() throws Exception {
		logger.info("Verifying CONTINUE Button Display");
		browser.waitForVisible(selectContinueButton);
		browser.verifyVisible(selectContinueButton);

	}

	public void verifyAbortButtonDisplay() throws Exception {
		logger.info("Verify Abort Button");
		browser.waitForVisible(selectAbortButton);
		browser.verifyVisible(selectAbortButton);

	}

	public void clickAbortButton() throws Exception {
		logger.info("Clicking Abort Button");
		browser.waitForVisible(selectAbortButton);
		selectAbortButton.click();
	}

	public void verifyAbortButtonPopup() throws Exception {
		logger.info("verifying Abort Button Pop up ");

		browser.waitForVisible(abortBox);
		browser.verifyText(abortBoxMessage,
				"Are you sure that you want to abort this VDR application?");
		browser.verifyText(
				abortBoxMessageDescription,
				"Click 'Yes' to proceed further or 'No' to cancel. Once aborted, you may have to start with 'Create New' VDR Application.");
		Assert.assertTrue(abortNoButton.isDisplayed());
		Assert.assertTrue(abortYesButton.isDisplayed());

		browser.click(abortNoButton);
	}

	public void clickYesInAbortPopUp() throws Exception {
		logger.info("Clicking Abort YES Button");
		browser.waitForVisible(abortYesButton);
		abortYesButton.click();
	}

	public void verifyDeleteWorkersFunctionality() throws Exception {
		logger.info("verifying Delete workers functionality");
		browser.click(deleteWorkersLink);
		browser.click(deleteWorkersLinkInPopUp);
		Thread.sleep(2000);
		/*
		 * System.out.println(confirmationBoxMessage.getText());
		 * browser.verifyText(confirmationBoxMessage,
		 * "Are you sure that you want to delete the selected Worker(s)?");
		 * browser.verifyText( confirmationBoxMessageDescription,
		 * "Click 'Yes' to proceed further or 'No' to cancel.");
		 */
		browser.verifyVisible(confirmationNoButton);
		browser.verifyVisible(confirmationYesButton);
		browser.click(confirmationNoButton);
		browser.click(deleteWorkersLinkInPopUp);
		browser.click(confirmationYesButton);

	}

	// Method to verify workers count in Add More Workers link
	public void verifyWorkersCountInAddMoreLink() {
		logger.info("verifying the workers count n Add More Workers link");
		int listSize = workerRowInWorkerSelectionPage.size();
		int workersCount = 100 - listSize;
		browser.assertTrue(addMoreWorkersLink.getText().contains(
				Integer.toString(workersCount)));
	}

	// Method to verify if worker details pop is displayed on clicking on the
	// worker record
	public void verifyIfWorkerDetailsPopUpIsDisplayed() throws Exception {
		logger.info("verifying if worker details pop up is displayed");
		browser.executeJavascript(firstWorkerInList, "arguments[0].click();");
		new CommonElements(driver).initElements().waitForOverlayToHide();
		browser.verifyVisible(workerDetailsPopup);
		browser.waitForVisible(workerDetailsPopupClose.get(2));
		browser.click(workerDetailsPopupClose.get(2));
	}

	// Method to click on Add More Workers link
	public void clickAddMoreWorkersLink() throws Exception {
		logger.info("waiting for Add More Workers link to be visible");
		browser.waitForVisible(addMoreWorkersLink);
		logger.info("clicking on add more workers link");
		browser.click(addMoreWorkersLink);
	}
	
	//Method to validate worker point of entry field
	public void validateWorkerPointOfEntry(String workerPointOfEntryInvalidData) throws Exception{
		logger.info("verifying if save button is not displayed when Worker Point Of Entry is not entered");
		browser.verifyNotVisible(saveButton);
		logger.info("typing "+workerPointOfEntryInvalidData+ " in Worker Point Of Entry");
		browser.type(workerPointOfEntry, workerPointOfEntryInvalidData);
		browser.click(refreshListLink);
		logger.info("verifying if save button is not displayed when invalid Worker Point Of Entry is entered");
		browser.verifyNotVisible(saveButton);
		browser.clear(workerPointOfEntry);
		browser.Wait(1);
		browser.click(refreshListLink);
		browser.Wait(1);
		logger.info("typing Worker Point of Entry");
		browser.click(workerPointOfEntry);
		browser.Wait(1);
		workerPointOfEntry.sendKeys(Keys.BACK_SPACE);
		browser.waitForVisible(autoSuggestFirstValueWorkerPointOfEntry);
		browser.click(autoSuggestFirstValueWorkerPointOfEntry);
	}
	
	//Method to verify if source country embassy window is displayed on clicking on edit
	public void verifyEditSourceCountryEmbassy() throws Exception{
		logger.info("clicking on edit source country embassy");
		browser.click(editSourceCountryEmbassy);
		browser.waitForVisible(enterSourceCountryEmbassy);
		browser.verifyEditable(enterSourceCountryEmbassy);
		browser.click(closeSourceEmbassyWindow);
	}
	
	//Method to click on Previous button
	public void clickPreviousButton() throws Exception {
		logger.info("clicking on previous button");
		browser.waitForVisible(previousButton);
		browser.click(previousButton);
	}

}
