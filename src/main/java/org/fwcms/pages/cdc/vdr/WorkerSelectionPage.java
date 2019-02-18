package org.fwcms.pages.cdc.vdr;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.fwcms.pages.CommonElements;
import org.fwcms.prop.cdc.vdrApplicationPageProp;
import org.fwcms.util.FileRename;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WorkerSelectionPage {

	private static final Logger logger = LogManager
			.getLogger(WorkerSelectionPage.class.getName());

	private BrowserBot browser;
	private WebDriver driver;

	public WorkerSelectionPage(WebDriver driver) {
		this.driver = driver;
		browser = new BrowserBot(driver);
	}

	public WorkerSelectionPage initElements() {
		return PageFactory.initElements(driver, WorkerSelectionPage.class);
	}

	// WebElement Declaration
	@FindBy(css = "div[class='steps_nav']>div")
	private List<WebElement> navigationStepsList;

	@FindBy(className = "searchWorkerWrapper")
	private WebElement searchWorkerWrapper;

	@FindBy(id = "agent_id")
	private WebElement agentIdTextBox;

	@FindBy(className = "agentFilter_overlay")
	private WebElement agentFilterOverlay;

	@FindBy(xpath = "//div[@class='jspPane']//span")
	private WebElement agentDropdown;

	@FindBy(className = "filterrow")
	private WebElement agentFilterRow;

	@FindBy(css = "div[id='search_workers_btn']>span:nth-child(2)>span")
	private WebElement searchWorkersButton;

	@FindBy(id = "radio_csv_upload")
	private WebElement uploadCsvFileRadio;

	@FindBy(id = "radio_enter_passport_no")
	private WebElement enterWorkerDetailsManuallyRadio;

	@FindBy(id = "search_worker_csvfileupload")
	private WebElement uploadCsvAttachFile;

	@FindBy(className = "add_workers_manually")
	private WebElement addWorkersManually;

	@FindBy(id = "wrk_passport_id_1")
	private WebElement passportEntry;

	@FindBy(xpath = "//div[@class='worker_row'][1]//div[@class='wrk_gender']//input[@Value='M']")
	private WebElement maleRadioButton;

	@FindBy(xpath = "//div[@class='worker_row'][1]//div[@class='wrk_gender']//input[@Value='F']")
	private WebElement femaleRadioButton;

	@FindBy(xpath = "//div[@id='save_worker_details_manual']//span[ contains(.,'Search')]")
	private WebElement searchButton;

	@FindBy(xpath = "//div[@id='cancel_worker_details_manual']//span[ contains(.,' Cancel')]")
	private WebElement cancelButton;

	@FindBy(xpath = "//div[@id='cancel_worker_selection']//span[contains(.,'Previous')]")
	private WebElement previousButton;

	@FindBy(xpath = "//div[@id='cancel_worker_selection_top']//span[@class='mid_btn']//span")
	private WebElement topPreviousButton;

	@FindBy(xpath = "//a[contains(.,'Download Unmatched Worker(s) List')]")
	private WebElement downLoadLink;

	@FindBy(className = "stepcount")
	private List<WebElement> stepCountList;

	@FindBy(className = "nav_step_mid")
	private List<WebElement> navigationStepNameList;

	@FindBy(className = "formErrorContent")
	private WebElement formError;
	
	@FindBy(css="div[class*='embassy_idformError']>div.formErrorContent")
	private WebElement sourceEmbassyFormError;

	@FindBy(className = "tick")
	private WebElement navigationSuccessTickMark;

	@FindBy(className = "addr_info")
	private WebElement addressInfoIcon;

	@FindBy(className = "unmatched_popup")
	private WebElement agentDetailsPopup;

	@FindBy(css = "div[id='close_agent_popup']>a")
	private WebElement closeAgentDetailsPopup;

	@FindBy(css = "div._grid_bottom_legnd")
	private WebElement bottomInfoMessage;

	@FindBy(css = "span.clearallworkers_text>a")
	private WebElement clearlist;

	@FindBy(className = "search_worker_container")
	private WebElement searchWorkerContainer;

	@FindBy(className = "matched_count")
	private WebElement matchedCount;

	@FindBy(className = "unmatched_count")
	private WebElement unMatchedCount;

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

	@FindBy(className = "checkAll")
	private WebElement checkAllMatchedWorkersInHeader;

	@FindBy(css = "div[class$='worker_name']")
	private List<WebElement> workerNameList;

	@FindBy(css = "div[class='listRow']>div[class^='passport_td_field']")
	private List<WebElement> passportNumberList;

	@FindBy(css = "div[class='listRow'] span[class^='VDR_icons']")
	private List<WebElement> matchedAndUnMatchedIconsList;

	@FindBy(css = "div[class='listRow']>div:nth-child(1)")
	private List<WebElement> checkBoxColumn;

	@FindBy(className = "customCheckBox")
	private List<WebElement> matchedWorkerCheckBoxList;

	@FindBy(css = "span[class='refresh_text']>a")
	private WebElement refreshListLink;

	@FindBy(css = "span[class='clearallworkers_text']>a")
	private WebElement clearListLink;

	@FindBy(id = "worker_name_search")
	private WebElement searchWorkerTextBox;

	@FindBy(css = "span[class$='search_workername']>a")
	private WebElement searchWorkerLink;

	@FindBy(className = "workers_count")
	private WebElement workersCount;

	@FindBy(css = "span[class='download_unmatched_passport_num']>a")
	private WebElement downloadUnmatchedPassportNumberLink;

	@FindBy(css = "div[id='save_worker_details']>span:nth-child(2)>span")
	private WebElement saveWorkerDetailsButton;

	@FindBy(id = "worker_overlay_embassy_id")
	private WebElement enterSourceCountryEmbassy;

	@FindBy(xpath = "//div[@class='embassyFilter_overlay open']//div[@class='jspPane']/div[1]/span[@class='chktxt']")
	private WebElement autoSuggestFirstValueSourceCountryEmbassy;

	@FindBy(xpath = "//div[@id='continue_worker_details']//span[2]")
	private WebElement selectContinueButton;

	@FindBy(xpath = "//div[@id='set_worker_embassy']//span[2]")
	private WebElement selectOKButton;

	@FindBy(xpath = "//div[@id='abortVDRProcess']//span[2]/span")
	private WebElement selectAbortButton;

	@FindBy(css = "div[class^='vdr_generation_ack_popup']")
	private WebElement confirmationBox;
	
	@FindBy(xpath = "//div[@id='clearUnsavedWorkersPop']//span[@class='confirm_alert']/strong")
	private WebElement clearWorkerconfirmationBoxMessage;

	@FindBy(xpath = "//div[@id='clearUnsavedWorkersPop']//span/p")
	private WebElement clearWorkerconfirmationBoxMessageDescription;

	@FindBy(xpath = "//div[@id='clearUnsavedWorkersPop']//div[@id='approve_worker_deletion']")
	private WebElement clearListConfirmationYesButton;

	@FindBy(xpath = "//div[@id='clearUnsavedWorkersPop']//div[@id='cancel_worker_deletion']")
	private WebElement clearListConfirmationNoButton;

	@FindBy(className = "worker_details_popup")
	private WebElement workerDetailsPopup;

	@FindBy(className = "worker_details_popup_close")
	private List<WebElement> workerDetailsPopupClose;

	@FindBy(id = "wrkrNameId")
	private WebElement workerNameInPopup;

	@FindBy(id = "wrkrPassportNumberId")
	private WebElement passportNumberInPopup;

	@FindBy(id = "wrkrAgentId")
	private WebElement workerAgentIdInPopup;

	@FindBy(id = "wrkrCountryId")
	private WebElement nationalityInPopup;

	@FindBy(className = "worker_overlay_occupation")
	private WebElement workerOverlayOccupation;

	@FindBy(className = "close_overlay_occupation")
	private WebElement workerOverlayOccupationCloseImage;

	@FindBy(id = "worker_overlay_occupation_id")
	private WebElement workerOccupationTextBox;

	@FindBy(css = "div[id='set_worker_occupation']>span:nth-child(2)>span")
	private WebElement workerOccupationOkButton;

	@FindBy(css = "div[class^='occupationFilter_overlay']")
	private WebElement occupationListOverlay;

	@FindBy(className = "chktxt")
	private List<WebElement> occupationsList;

	@FindBy(className = "applicationId")
	private WebElement applicationIdRendered;

	@FindBy(className = "selectedOccupationFromList")
	private WebElement selectedOccupationFromList;

	@FindBy(css = "div[id='abortVDRProcess']>span:nth-child(2)>span")
	private WebElement abortVdrButton;

	@FindBy(css = "div[id='continue_worker_details']>span:nth-child(2)>span")
	private WebElement continueVdrButton;

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

	@FindBy(css = ".removeallworkers_text>a")
	private WebElement deleteWorkersLink;

	@FindBy(xpath = "//a[@title='Delete Selected Worker(s)']")
	private WebElement deleteWorkersLinkInPopUp;

	@FindBy(xpath = "//div[contains(@class,'workers_list_delete_container')]//div[@class='jspPane']")
	private WebElement firstWorkerDetailsInPopup;

	@FindBy(xpath = "//div[@id='deleteSelectedVDRWorkersPop']//span/strong")
	private WebElement deleteWorkerconfirmationBoxMessage;

	@FindBy(xpath = "//div[@id='deleteSelectedVDRWorkersPop']//span/p")
	private WebElement deleteWorkerconfirmationBoxMessageDescription;

	@FindBy(xpath = "//*[@id='deleteSelectedVDRWorkersPop'] //div[2]//span[2]/strong")
	private WebElement confirmationBoxMessage;

	@FindBy(xpath = "//*[@id='deleteSelectedVDRWorkersPop']//div[2]//span[3]/p")
	private WebElement confirmationBoxMessageDescription;

	@FindBy(xpath = "//div[@id='deleteSelectedVDRWorkersPop']//div[@id='approve_worker_deletion']")
	private WebElement deleteWorkerConfirmationYesButton;

	@FindBy(xpath = "//div[@id='deleteSelectedVDRWorkersPop']//div[@id='cancel_worker_deletion']")
	private WebElement deleteWorkerConfirmationNoButton;

	@FindBy(css = "div[class='pageHeading']>span")
	private WebElement pageHeader;

	@FindBy(css = "input[id^='wrk_passport_id']")
	private WebElement textPassportNumber;

	@FindBy(className = "addmore")
	private WebElement linkAddMoreInPassportEntryWindow;

	@FindBy(css = "div[class^='workers_list_container']>div:nth-child(1)")
	private WebElement firstWorkerInList;

	@FindBy(css = "div[class='worker_row']")
	private List<WebElement> workerRowInPassportEntryWindow;

	@FindBy(css = "div[class='listRow']")
	private List<WebElement> workerRowInWorkerSelectionPage;

	@FindBy(id = "selectedEmbassyFromList")
	private WebElement selectedSourceCountryEmbassy;

	@FindBy(linkText = "Sign Out")
	private WebElement linkSignOut;
	
	@FindBy(className="close_overlay_embassy")
	private WebElement closeSourceEmbassyWindow;
	
	@FindBy(className="editEmbassy")
	private WebElement editSourceCountryEmbassy;
	
	@FindBy(css="div[class='statusBar success']")
	private WebElement statusBar;
	
	@FindBy(css="span[title='Details Unmatched']")
	private WebElement unmatchedWorkerStatus;
	
	@FindBy(css="span[title='Details Matched']")
	private WebElement matchedWorkerStatus;
	
	@FindBy(className="csv_errors_popup")
	private WebElement csvErrorListPopUp;
	
	@FindBy(className="row_no")
	private WebElement rowNoHeader;
	
	@FindBy(className="col_no")
	private WebElement columnNoHeader;
	
	@FindBy(className="col_name")
	private WebElement columnNameHeader;
	
	@FindBy(className="error_msg")
	private WebElement errorMessageHeader;
	
	@FindBy(id="cancel_csv_error_details")
	private WebElement cancelButtonInCSVErrorLsit;
	
	@FindBy(id="export_csv_error_details")
	private WebElement exportButtonInCSVErrorList;
	
	@FindBy(css="div.jspPane>div.error_row")
	private List<WebElement> csvInvalidDataErrorRows;
	
	

	private String selectedWorkersOccupattion = null;
	boolean flag;

	/**
	 * Verify worker selection tab is active
	 * 
	 * @throws Exception
	 */
	public void verifyWorkerSelectionIsActive() throws Exception {
		logger.info("verifying worker selection is active");
		Iterator<WebElement> navigationSteps = navigationStepsList.iterator();
		int i = 0;
		while (navigationSteps.hasNext()) {
			WebElement eachNavigation = navigationSteps.next();
			String className = browser.getAttribute(eachNavigation, "class");
			if (i == 0) {
				if (!className.contains("active")) {
					browser.verifyFail("Worker Section is not active");
				}
			} else {
				if (!className.contains("inactive")) {
					browser.verifyFail("Multiple steps are active where only Worker Section should be active");
				}
			}
			i++;
		}
	}

	public void verifyElementsPresentInWorkerSelection() throws Exception {
		logger.info("verifying elements in worker selection");
		browser.verifyEditable(agentIdTextBox);
		browser.verifyVisible(searchWorkersButton);
		browser.verifyEditable(searchWorkersButton);
		browser.verifyEditable(uploadCsvFileRadio);
		browser.verifyEditable(enterWorkerDetailsManuallyRadio);
		browser.verifyVisible(uploadCsvAttachFile);
		browser.verifyEditable(uploadCsvAttachFile);
	}

	public void verifyUploadCsvOptionSelectedDefaultInWorkerSelection()
			throws Exception {
		logger.info("verifying upload csv option selected default in worker selection");
		browser.verifyChecked(uploadCsvFileRadio);
		browser.verifyNotChecked(enterWorkerDetailsManuallyRadio);
	}

	public void verifyRadioOptionsFunctionalityInWorkerSelection()
			throws Exception {
		logger.info("verifying radio options functionality in worker selection");
		browser.click(uploadCsvFileRadio);
		browser.verifyChecked(uploadCsvFileRadio);
		browser.verifyVisible(uploadCsvAttachFile);
		browser.verifyNotChecked(enterWorkerDetailsManuallyRadio);
		browser.click(enterWorkerDetailsManuallyRadio);
		browser.verifyNotChecked(uploadCsvFileRadio);
		browser.verifyChecked(enterWorkerDetailsManuallyRadio);
		browser.verifyVisible(addWorkersManually);
		browser.verifyNotVisible(uploadCsvAttachFile);
		/*
		 * rolling back the radio selection
		 */
		browser.click(uploadCsvFileRadio);
	}

	public void enterAgentId(String agentId) {
		logger.info("typing agent id");
		browser.clearAndType(agentIdTextBox, agentId);
		logger.info("Agent ID is entered");
	}

	public void attachWorkersAndClickSearch(String filePath) throws Exception {
		logger.info("attaching workers");
		browser.Wait(1);
		//browser.click(uploadCsvAttachFile);
		uploadCsvAttachFile.sendKeys(filePath);
		browser.Wait(2);
	//	browser.click(addressInfoIcon);
		browser.Wait(5); //Changed from 1 to 2 secs
		browser.click(searchWorkersButton);
	}

	public void selectEnterWorkerDetailsManuallyRadioButton() throws Exception {
		logger.info("select Enter Workers Details Manually RadioButton");
		browser.executeJavascript(enterWorkerDetailsManuallyRadio,
				"arguments[0].click();");//[0]

	}

	public void clickAddWorkers() throws Exception {
		logger.info("Click Add Workers LinkText");
		browser.click(addWorkersManually);
	}

	public void enterPassportNumber(String passportNumber) throws Exception {
		logger.info("Enter passport number "+passportNumber);
		driver.switchTo().defaultContent();
		browser.waitForEditable(passportEntry);
		browser.type(passportEntry, passportNumber);
	}

	public void clickSearchButtonInPassportEntryWindow() throws Exception {
		logger.info("Click Search Button ");
		browser.click(searchButton);
	}

	public void checkPreviousButtonDisplay() throws Exception {
		logger.info("Check for Previous Button Display ");
		browser.verifyVisible(topPreviousButton);
	}

	public void verifyBottomInfoMessageDisplay(String bottomInfoExpectedMessage) throws Exception {
		logger.info("Verify Bottom Info Message Display");
		browser.verifyVisible(bottomInfoMessage);
		String errorMessage = browser.getText(bottomInfoMessage);
		browser.assertEquals(
				errorMessage.trim(),bottomInfoExpectedMessage.trim());
	}

	public void waitForWorkersListToAppear() {
		logger.info("waiting for the workers list to appear");
		browser.waitForVisible(searchWorkerContainer);
	}

	public void selectWorkerCheckBox() throws Exception {
		logger.info("Selecting Worker Check box");
		browser.waitForVisible(checkAllMatchedWorkersInHeader);
		browser.click(checkAllMatchedWorkersInHeader);
	}

	public void clickSaveButton() throws Exception {
		logger.info("Clicking Save Button");
		browser.waitForVisible(saveWorkerDetailsButton);
		saveWorkerDetailsButton.click();
	}

	public void verifySaveButtonDisplay() throws Exception {
		logger.info("Verifying Save Button Display");
		browser.verifyVisible(saveWorkerDetailsButton);

	}

	public void selectSourceCountryEmbassy() throws Exception {
		logger.info("Entering Source Country Embassy");
		browser.waitForVisible(enterSourceCountryEmbassy);
		browser.click(enterSourceCountryEmbassy);
		browser.Wait(1);
		browser.waitForVisible(autoSuggestFirstValueSourceCountryEmbassy);
		browser.click(autoSuggestFirstValueSourceCountryEmbassy);
	}

	public void clickOKButton() throws Exception {
		logger.info("Clicking OK Button");
		browser.waitForVisible(selectOKButton);
		selectOKButton.click();
	}

	// Need to write the method
	public void checkforSuccessStatusBar() throws Exception {
		logger.info("Checking for Success Status Bar and Message");
		WebElement successStatusBar = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(600, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class,
							StaleElementReferenceException.class);

			successStatusBar = wait
					.until(new Function<WebDriver, WebElement>() {
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By
									.xpath("//div[@class='statusBar success']"));
						}
					});

		} catch (Exception e) {
			logger.info("Success message didnt get display");
			e.printStackTrace();
		}

		Assert.assertTrue(successStatusBar.isDisplayed());
		logger.info("Success Status Bar is displayed");
		browser.click(successStatusBar);

	}

	public void clickContinueButton() throws Exception {
		logger.info("Clicking CONTINUE Button");
		browser.waitForVisible(selectContinueButton);
		selectContinueButton.click();
	}

	public void verifyContinueButtonDisplay() throws Exception {
		logger.info("Verifying CONTINUE Button Display");
		browser.waitForVisible(selectContinueButton);
		browser.verifyVisible(selectContinueButton);

	}

	public void clickAbortButton() throws Exception {
		logger.info("Clicking Abort Button");
		browser.waitForVisible(selectAbortButton);
		selectAbortButton.click();

	}

	public void verifyAbortButtonDisplay() throws Exception {
		logger.info("Verify Abort Button");
		browser.waitForVisible(selectAbortButton);
		browser.verifyVisible(selectAbortButton);

	}

	public void verifyElementsInWorkerSelectionAfterVdrWorkersUploaded()
			throws Exception {
		logger.info("verifying elements in worker selection after vdr workers uploaded");
		browser.verifyVisible(matchedCount);
		browser.verifyVisible(unMatchedCount);
		browser.verifyVisible(sector);
		browser.verifyVisible(nationality);
		browser.verifyVisible(addMoreWorkersLink);
		browser.verifyVisible(workerNameHeader);
		browser.verifyVisible(passportNumberHeader);
		browser.verifyVisible(genderHeader);
		browser.verifyVisible(medicalExpiryHeader);
		browser.verifyVisible(filterHeader);
		browser.verifyEditable(refreshListLink);
		browser.verifyEditable(clearListLink);
		browser.verifyEditable(searchWorkerTextBox);
		browser.verifyEditable(searchWorkerLink);
		browser.verifyEditable(downloadUnmatchedPassportNumberLink);
		browser.verifyEditable(saveWorkerDetailsButton);
	}

	public void verifyClearListLink() throws Exception {
		logger.info("verifying clear list link functionality");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		browser.click(clearListLink);
		browser.waitForVisible(confirmationBox);
		browser.verifyText(clearWorkerconfirmationBoxMessage,
				"Are you sure that you want to clear the list?");
		browser.verifyText(
				clearWorkerconfirmationBoxMessageDescription,
				"Click 'Yes' to proceed further or 'No' to cancel. Once cleared you may have to upload the .CSV file/enter worker details manually.");

		browser.verifyVisible(clearListConfirmationNoButton);
		browser.verifyVisible(clearListConfirmationYesButton);
		browser.click(clearListConfirmationNoButton);

		browser.verifyVisible(addMoreWorkersLink);
		browser.click(clearListLink);
		browser.waitForVisible(confirmationBox);
		browser.click(clearListConfirmationYesButton);
		browser.verifyElementNotPresent(unmatchedWorkerStatus);
		browser.verifyVisible(matchedWorkerStatus);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}

	public void verifyErrorMessageWithoutSelectingWorkers() throws Exception {
		logger.info("verify error message without selecting workers");
		browser.click(saveWorkerDetailsButton);
		new CommonElements(driver)
				.initElements()
				.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(
						"Please select workers from the list to proceed further.");
	}

	public void verifyCheckboxAgainestMatchedAndUnMatchedWorkers()
			throws Exception {
		logger.info("verifying checkbox against matched and unmatched workers");
		Iterator<WebElement> matchedAndUnMatchedIcons = matchedAndUnMatchedIconsList
				.iterator();
		int i = 0;
		while (matchedAndUnMatchedIcons.hasNext()) {
			WebElement eachIcon = matchedAndUnMatchedIcons.next();
			String className = browser.getAttribute(eachIcon, "class");
			if (className.endsWith("found")) {
				try {
					WebElement foundCheckBox = checkBoxColumn.get(i)
							.findElement(By.className("customCheckBox"));
					browser.verifyEditable(foundCheckBox);
				} catch (NoSuchElementException e) {
					browser.verifyFail("Check box not found for matched worker");
				}
			} else {
				try {
					checkBoxColumn.get(i).findElement(
							By.className("customCheckBox"));
					browser.verifyFail("CheckBox should not be present at row "
							+ (i + 1));
				} catch (NoSuchElementException e) {

				}
			}
			i++;
		}
	}

	public void verifyMatchedAndUnMatchedWorkersCountAndTotalWorkersUploaded()
			throws Exception {
		logger.info("verifying matched and unmatched workers count and total workers uploaded");
		Iterator<WebElement> matchedAndUnMatchedIcons = matchedAndUnMatchedIconsList
				.iterator();
		int matchedCountIcons = 0;
		int notMatchedCountIcons = 0;
		while (matchedAndUnMatchedIcons.hasNext()) {
			WebElement eachIcon = matchedAndUnMatchedIcons.next();
			String className = browser.getAttribute(eachIcon, "class");
			if (className.endsWith("found")) {
				matchedCountIcons++;
			} else {
				notMatchedCountIcons++;
			}
		}
		browser.verifyText(matchedCount, "(" + matchedCountIcons + ")");
		browser.verifyText(unMatchedCount, "(" + notMatchedCountIcons + ")");
		browser.verifyText(workersCount, ""
				+ (matchedCountIcons + notMatchedCountIcons) + " Worker(s)");
	}

	public void verifyAllMatchedWorkersSelectedWhenCheckboxInHeaderWorkerNameSelected()
			throws Exception {
		logger.info("verify all matched workers selected when checkbox in header worker name selected");
	//	browser.click(checkAllMatchedWorkersInHeader);
		Iterator<WebElement> matchedWorkerCheckBox = matchedWorkerCheckBoxList
				.iterator();
		while (matchedWorkerCheckBox.hasNext()) {
			browser.verifyChecked(matchedWorkerCheckBox.next());
		}
	}

	public void verifyPassportDetailsVisibleOfMatchedWorkers() throws Exception {
		logger.info("verify passport details visible of matched workers");
		Iterator<WebElement> matchedAndUnMatchedIcons = matchedAndUnMatchedIconsList
				.iterator();
		int i = 0;
		while (matchedAndUnMatchedIcons.hasNext()) {
			WebElement eachIcon = matchedAndUnMatchedIcons.next();
			String className = browser.getAttribute(eachIcon, "class");
			if (className.endsWith("found")) {
				String passportNumber = browser.getAttribute(
						passportNumberList.get(i), "title");
				String workerName = browser.getAttribute(workerNameList.get(i),
						"title");
				browser.click(eachIcon);
				browser.waitForVisible(workerDetailsPopup);
				browser.waitForText(workerNameInPopup, workerName);
				browser.verifyText(passportNumberInPopup, passportNumber);
				browser.verifyTrue(nationalityInPopup.getText().equalsIgnoreCase(browser.getText(nationality)));
				//browser.verifyText(nationalityInPopup,browser.getText(nationality));
				browser.click(workerDetailsPopupClose.get(2));
				browser.waitForNotVisible(workerDetailsPopupClose.get(2));
			}
			i++;
		}
	}

	public void verifyWorkersOccupationVisibleOnSaveWorkers() throws Exception {
		logger.info("verifying workers occupation visible on save workers");
		browser.check(checkAllMatchedWorkersInHeader);
		browser.click(saveWorkerDetailsButton);
		browser.waitForVisible(workerOverlayOccupation);
		browser.verifyEditable(workerOccupationTextBox);
		browser.verifyEditable(workerOccupationOkButton);
	}

	public void verifyWorkerOccupationNotVisibleOnClickOfCloseIcon()
			throws Exception {
		logger.info("verifying worker occupation not visible on click of close icon");
		browser.click(workerOverlayOccupationCloseImage);
		browser.waitForNotVisible(workerOverlayOccupationCloseImage);
		browser.verifyNotVisible(workerOverlayOccupationCloseImage);
	}

	public void enterWorkerOccupationAndClickOk(String workerOccupation)
			throws Exception {
		logger.info("entering worker occupation");
		browser.click(saveWorkerDetailsButton);
		browser.waitForVisible(workerOverlayOccupation);
		browser.Wait(1);
		browser.click(workerOccupationTextBox);
		browser.waitForVisible(occupationListOverlay);
		Iterator<WebElement> occupations = occupationsList.iterator();
		boolean foundOccupation = false;
		while (occupations.hasNext()) {
			WebElement eachOccupation = occupations.next();
			if (browser.getText(eachOccupation).equals(workerOccupation)) {
				selectedWorkersOccupattion = workerOccupation;
				foundOccupation = true;
				browser.Wait(1);
				browser.click(eachOccupation);
				browser.waitForNotVisible(occupationListOverlay);
				browser.Wait(1);
				browser.verifyValue(workerOccupationTextBox, workerOccupation);
				break;
			}
		}
		browser.verifyTrue(foundOccupation,
				"Could not found the worker occupation " + workerOccupation);
		browser.click(workerOccupationOkButton);
	}

	public void verifyApplicationIdAndSelectedOccupationVisible()
			throws Exception {
		logger.info("verifying application id and selected occupation visible");
		browser.verifyVisible(applicationIdRendered);
		browser.verifyText(selectedOccupationFromList,
				selectedWorkersOccupattion);
	}

	public void verifyAbortButtonAndContinueButtonVisible() throws Exception {
		logger.info("verifying abort button and continue button visible");
		verifyAbortButton();
		browser.verifyEditable(continueVdrButton);
	}

	public void verifyWorkerSavedIconAndDeleteIconPresentAndCheckBoxDisabledForSavedWorkers()
			throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("verifying worker saved icon and delete icon present for saved workers");
		Iterator<WebElement> matchedAndUnMatchedIcons = matchedAndUnMatchedIconsList
				.iterator();
		int i = 0;
		while (matchedAndUnMatchedIcons.hasNext()) {
			WebElement eachIcon = matchedAndUnMatchedIcons.next();
			String className = browser.getAttribute(eachIcon, "class");
			if (className.endsWith("found")) {
				try {
					WebElement foundCheckBox = checkBoxColumn.get(i)
							.findElement(By.className("customCheckBox"));
					browser.verifyNotEditable(foundCheckBox);
					System.out.println("check box found");
				} catch (NoSuchElementException e) {
					browser.verifyFail("Check box not found for matched worker");
				}
				try{
					WebElement saveIcon = checkBoxColumn
							.get(i).findElement(By.xpath("//span[@class='saved_workers_ind savedworker']"));
					browser.verifyEditable(saveIcon);
				}catch(NoSuchElementException e){
					browser.verifyFail("Save Icon not found for matched worker");
				}
				try {
					WebElement deleteIcon = checkBoxColumn
							.get(i).findElement(By.xpath("//span[@class='saved_workers_ind deleteworker']"));
					browser.verifyEditable(deleteIcon);
				} catch (NoSuchElementException e) {
					browser.verifyFail("Delete Icon not found for matched worker");
				}
			} else {
				try {
					checkBoxColumn.get(i).findElement(
							By.className("customCheckBox"));
					browser.verifyFail("CheckBox should not be present at row "
							+ (i + 1));
				} catch (NoSuchElementException e) {

				}
			}
			i++;
		}driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	// TODO
	public void verifyContinueButtonInWorkerSelection() throws Exception {
		logger.info("verifying continue button in worker selection");
		browser.click(continueVdrButton);
		browser.waitForVisible(confirmationBox);
		browser.verifyText(confirmationBoxMessage,
				"Please ensure that you do not have any unsaved data.");
		browser.verifyText(confirmationBoxMessageDescription,
				"Click 'Yes' to proceed further or 'No' to cancel.");
		// browser.click(confirmationNoButton);
		browser.waitForNotVisible(confirmationBox);
		browser.verifyVisible(applicationIdRendered);
		browser.verifyVisible(continueVdrButton);
		browser.click(continueVdrButton);
		browser.waitForVisible(confirmationBox);
		// browser.click(confirmationYesButton);
	}

	public void verifyAbortButton() throws Exception {
		logger.info("verifying abort button");
		browser.verifyEditable(abortVdrButton);
	}

	public void verifyRecruimentAgentIdAfterSavedWorkers(
			String recruimentAgentId) throws Exception {
		logger.info("verifying recruiment agent id");
		browser.verifyNotEditable(agentIdTextBox);
		browser.verifyValue(agentIdTextBox, recruimentAgentId);
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
		browser.click(abortYesButton);
	}

	public void verifyDeleteWorkersFunctionality() throws Exception {
		logger.info("verifying Delete workers functionality");
		browser.click(deleteWorkersLink);
		browser.Wait(2);
		browser.click(deleteWorkersLinkInPopUp);
		String selectWorkersToDeleteMessage = vdrApplicationPageProp
				.getSelectWorkersToDeleteMessage();
		new CommonElements(driver)
				.initElements()
				.waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(
						selectWorkersToDeleteMessage);
		browser.click(firstWorkerDetailsInPopup);
		browser.Wait(2);
		browser.click(deleteWorkersLinkInPopUp);

		browser.verifyText(deleteWorkerconfirmationBoxMessage,
				"Are you sure that you want to delete the selected Worker(s)?");
		browser.verifyText(deleteWorkerconfirmationBoxMessageDescription,
				"Click 'Yes' to proceed further or 'No' to cancel.");

		browser.verifyVisible(deleteWorkerConfirmationNoButton);
		browser.verifyVisible(deleteWorkerConfirmationYesButton);
		browser.click(deleteWorkerConfirmationNoButton);

		browser.click(deleteWorkersLinkInPopUp);
		browser.click(deleteWorkerConfirmationYesButton);

	}

	@SuppressWarnings("unused")
	public void enterAgentIdAndVerifyAgentDetailsPopup(final String agentId)
			throws Exception {
		logger.info("entering agent id and verifying agent details popup");
		browser.clearAndType(agentIdTextBox, agentId);
		logger.info("Waiting for the auto suggest values to appear");
		browser.Wait(2);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(600, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class,
						StaleElementReferenceException.class);
		WebElement autoSuggestAgencyName = wait
				.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						driver.findElement(
								By.xpath("//span[contains(text(),'" + agentId
										+ "')]")).click();
						return driver.findElement(By
								.xpath("//span[contains(text(),'" + agentId
										+ "')]"));
					}
				});
		browser.waitForVisible(closeAgentDetailsPopup);
		browser.click(closeAgentDetailsPopup);
	}

	// Method to verify the page heading
	public void verifyPageHeader() throws Exception {
		logger.info("Verifying the heading of the page");
		browser.verifyText(pageHeader, "VDR Application");
	}

	// Method to validate agent id field
	public void validateAgentId(String agentIdInvalidExpectedMessage,
			String agentIdInvalidData, String agentIdNotFoundExpectedMessage)
			throws Exception {
		logger.info("validating agent id field");
		logger.info("moving focus to agent id");
		browser.focus(agentIdTextBox);
		logger.info("clicking on search button");
		browser.click(searchWorkersButton);
		logger.info("waiting for agent id form error");
		browser.waitForVisible(formError);
		logger.info("verifying text in agent id error");
		browser.assertText(formError, agentIdInvalidExpectedMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);

		logger.info("clearing and typing agentIdInvalidData '"
				+ agentIdInvalidData + "' in agent Id");
		browser.clearAndType(agentIdTextBox, agentIdInvalidData);
		browser.Wait(1);
		logger.info("verifying text in agent id error");
		browser.waitForVisible(formError);
		browser.Wait(1);
		logger.info("verifying text in agent id error");
		browser.assertText(formError, agentIdNotFoundExpectedMessage);
		logger.info("clicking on search button");
		browser.click(searchWorkersButton);
		logger.info("verifying text in agent id error");
		browser.waitForVisible(formError);
		logger.info("verifying text in agent id error");
		browser.assertText(formError, agentIdInvalidExpectedMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);
	}

	// Method to validate worker details
	public void validateWorkerDetails(String csvFileNotUploadedMessage,
			String workersNotAddedMessage, String imageFilePath) throws Exception {
		logger.info("clicking on search button");
		browser.click(searchWorkersButton);
		logger.info("waiting for csv file not uploaded error");
		browser.waitForVisible(formError);
		logger.info("verifying text in file not uploaded error");
		browser.assertText(formError, csvFileNotUploadedMessage);

		logger.info("uploading an image file");
		uploadCsvAttachFile.sendKeys(imageFilePath);
		browser.Wait(5);
		browser.switchToDefault();
		browser.Wait(1);
		logger.info("clicking on search button");
		browser.click(searchWorkersButton);
		logger.info("waiting for csv file not uploaded error");
		browser.waitForVisible(formError);
		logger.info("verifying text in file not uploaded error");
		browser.assertText(formError, csvFileNotUploadedMessage);
		browser.click(formError);

		browser.Wait(5);
		logger.info("selecting Enter Worker Details Manually");
		browser.click(enterWorkerDetailsManuallyRadio);
		logger.info("clicking on search button");
		browser.click(searchWorkersButton);
		logger.info("waiting for workers not added error");
		browser.waitForVisible(formError);
		logger.info("verifying text in workers not added error");
		browser.assertText(formError, workersNotAddedMessage);
		browser.click(formError);
		browser.Wait(2);
	}

	// Method to validate worker passport details
	public void validateWorkerPassport(String passportNumberEmptyMessage,
			String passportNumberSpaceData,
			String passportNumberInvalidMessage,
			String passportNumberSpecialCharData, 
			String workersCannotBeAddedMessage) throws Exception {
		logger.info("selecting Enter Worker Details Manually");
		browser.click(enterWorkerDetailsManuallyRadio);
		logger.info("clicking add workers");
		browser.click(addWorkersManually);
		logger.info("validating passport number field");
		browser.focus(passportEntry);
		logger.info("clicking on search");
		browser.click(searchButton);
		logger.info("waiting for passport entry form error to be visible");
		browser.waitForVisible(formError);
		logger.info("verifying the text in passport entry form error");
		browser.assertText(formError, passportNumberEmptyMessage);

		logger.info("entering space in passport number field");
		browser.type(passportEntry, passportNumberSpaceData);
		logger.info("clicking on search");
		browser.click(searchButton);
		logger.info("waiting for passport entry form error to be visible");
		browser.waitForVisible(formError);
		logger.info("verifying the text in passport entry form error");
		browser.assertText(formError, passportNumberInvalidMessage);
		logger.info("sleeping for 1 second");
		browser.Wait(1);

		logger.info("clearing and typing " + passportNumberSpecialCharData
				+ " in passport number field");
		browser.clearAndType(passportEntry, passportNumberSpecialCharData);
		logger.info("clicking on search");
		browser.click(searchButton);
		logger.info("waiting for passport entry form error to be visible");
		browser.waitForVisible(formError);
		logger.info("verifying the text in passport entry form error");
		browser.assertText(formError, passportNumberInvalidMessage);
		
		browser.click(cancelButton);
		browser.click(enterWorkerDetailsManuallyRadio);
		browser.click(addWorkersManually);
		browser.click(linkAddMoreInPassportEntryWindow);
		logger.info("asserting if a row gets added on clicking add more link");
		browser.assertTrue(workerRowInPassportEntryWindow.size() == 11);
		for(int i=workerRowInPassportEntryWindow.size();i<=25;i++){
			browser.click(linkAddMoreInPassportEntryWindow);
		}
		browser.assertText(formError, workersCannotBeAddedMessage);
		browser.click(cancelButton);
	}

	// Method to verify elements present in passport entry window
	public void verifyElementsPresentInPassportEntryWindow() throws Exception {
		/*logger.info("selecting Enter Worker Details Manually");
		browser.click(enterWorkerDetailsManuallyRadio);
		logger.info("clicking add workers");
		browser.click(addWorkersManually);*/
		
		logger.info("verifying the elements present in passport entry window");
		System.out.println("List Size = "
				+ workerRowInPassportEntryWindow.size());
		browser.assertTrue(workerRowInPassportEntryWindow.size() == 10);
		browser.verifyEditable(textPassportNumber);
		browser.verifyVisible(maleRadioButton);
		browser.verifyChecked(maleRadioButton);
		browser.verifyVisible(femaleRadioButton);
		browser.verifyNotChecked(femaleRadioButton);
		browser.verifyVisible(linkAddMoreInPassportEntryWindow);
		browser.verifyVisible(cancelButton);
		browser.verifyVisible(searchButton);
	}

	// Method to verify workers count in Add More Workers link
	public void verifyWorkersCountInAddMoreLink() {
		logger.info("verifying the workers count in Add More Workers link");
		int listSize = workerRowInWorkerSelectionPage.size();
		int workersCount = 100 - listSize;
		browser.assertTrue(addMoreWorkersLink.getText().contains(
				Integer.toString(workersCount)));
	}

	// Method to verify if worker details pop is displayed on clicking on the
	// worker record
	public void verifyIfWorkerDetailsPopUpIsDisplayed() throws Exception {
		logger.info("verifying if worker details pop up is displayed");
		browser.click(firstWorkerInList);
		new CommonElements(driver).initElements().waitForOverlayToHide();
		browser.verifyVisible(workerDetailsPopup);
		browser.waitForVisible(workerDetailsPopupClose.get(2));
		browser.click(workerDetailsPopupClose.get(2));
	}

	// Method to verify if delete workers button is displayed on saving the vdr
	// application
	public void verifyIfDeleteWorkersButtonIsDisplayed() throws Exception {
		logger.info("verifying if Delete Workers button is displayed on saving the vdr application");
		browser.waitForVisible(deleteWorkersLink);
		browser.assertEditable(deleteWorkersLink);
	}

	// Method to verify if source country embassy is displayed on saving the vdr
	// application
	public void verifyIfSourceCountryEmbassyIsDisplayed() throws Exception {
		logger.info("verifying if Source Country Embassy details are displayed on saving the VDR application");
		browser.waitForVisible(selectedSourceCountryEmbassy);
		browser.assertVisible(selectedSourceCountryEmbassy);
	}

	// Method to click on Add More Workers link
	public void clickAddMoreWorkersLink() throws Exception {
		logger.info("waiting for Add More Workers link to be visible");
		browser.waitForVisible(addMoreWorkersLink);
		logger.info("clicking on add more workers link");
		browser.click(addMoreWorkersLink);
	}

	// Method to verify id add workers screen is displayed on clicking Add More
	// Workers link
	public void verifyPresenceOfAddWorkersOptions() throws Exception {
		logger.info("verifying if add workers options are displayed");
		browser.assertElementPresent(searchWorkerWrapper);
	}

	// Method to retrieve the vdr application id
	public String retrieveApplicationId() throws Exception {
		logger.info("waiting for application id to be visible");
		browser.waitForVisible(applicationIdRendered);
		logger.info("retieving the application id");
		return applicationIdRendered.getText();
	}

	// Method to click on Sign Out link
	public void clickSignOutLink() throws Exception {
		logger.info("clicking on sign out link");
		browser.Wait(2);
		browser.executeJavascript(linkSignOut, "arguments[0].click();");
		browser.Wait(2);
		browser.deleteAllVisibleCookies();
	}
	
	//Method to validate search worker field
	public void validateSearchWorker(String searchWorkerEmptyMessage,
				String searchWorkerSpaceData,
				String searchWorkerInvalidMessage,
				String searchWorkerMinCharData, 
				String searchWokerMinCharMessage,
				String searchWorkerSpecialCharData,
				String SearchWorkerSpecialCharMessage) throws Exception {
		logger.info("validating search worker field");
		logger.info("moving focus to search worker");
		browser.focus(searchWorkerTextBox);
		logger.info("clicking on search link");
		browser.click(searchWorkerLink);
		logger.info("waiting for search worker form error");
		browser.waitForVisible(formError);
		logger.info("verifying text in search worker error");
		browser.assertText(formError, searchWorkerEmptyMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(1);

		logger.info("entering space in serach worker field");
		browser.type(searchWorkerTextBox, searchWorkerSpaceData);
		logger.info("clicking on search link");
		browser.click(searchWorkerLink);
		logger.info("waiting for search worker form error");
		browser.waitForVisible(formError);
		logger.info("verifying text in search worker error");
		browser.assertText(formError, searchWorkerInvalidMessage);
		logger.info("sleeping for 1 second");
		browser.Wait(1);

		logger.info("clearing and typing " + searchWorkerMinCharData
					+ " in ssearch worker field");
		browser.clearAndType(searchWorkerTextBox, searchWorkerMinCharData);
		logger.info("clicking on search link");
		browser.click(searchWorkerLink);
		logger.info("waiting for search worker form error");
		browser.waitForVisible(formError);
		logger.info("verifying the text in search worker form error");
		browser.assertText(formError, searchWokerMinCharMessage);
		
		logger.info("clearing and typing " + searchWorkerSpecialCharData
				+ " in ssearch worker field");
		browser.clearAndType(searchWorkerTextBox, searchWorkerSpecialCharData);
		logger.info("clicking on search link");
		browser.click(searchWorkerLink);
		logger.info("waiting for search worker form error");
		browser.waitForVisible(formError);
		logger.info("verifying the text in search worker form error");
		browser.assertText(formError, SearchWorkerSpecialCharMessage);
		browser.Wait(1);
		browser.clear(searchWorkerTextBox);
		browser.Wait(1);
	}
	
	//Method to validate Source Country Embassy field
	public void validateSourceCountryEmbassy(String sourceEmbassyInvalidData,String sourceEmbassyInvalidMessage) throws Exception {
		browser.Wait(5);
		logger.info("validating source country embassy field");
		logger.info("moving focus to source country embassy");
		browser.focus(enterSourceCountryEmbassy);
		clickOKButton();
		logger.info("waiting for source country embassy form error");
		browser.waitForVisible(sourceEmbassyFormError);
		logger.info("verifying text in source country embassy error");
		browser.assertText(sourceEmbassyFormError, sourceEmbassyInvalidMessage);
		logger.info("sleeping for 1 sec");
		browser.Wait(2);
		
		logger.info("typing " + sourceEmbassyInvalidData
				+ " in source embassy field");
		browser.type(enterSourceCountryEmbassy, sourceEmbassyInvalidData);
		clickOKButton();
		browser.Wait(2);
		logger.info("waiting for source country embassy form error");
		browser.waitForVisible(sourceEmbassyFormError);
		logger.info("verifying text in source country embassy error");
		browser.assertText(sourceEmbassyFormError, sourceEmbassyInvalidMessage);
		browser.click(sourceEmbassyFormError);
	}
	
	//Method to close source country embassy window
	public void closeSourceCountryEmbassy() throws Exception {
		browser.wait(2);
		logger.info("clicking on close");
		browser.click(closeSourceEmbassyWindow);
		logger.info("Clicked on close button");
		browser.wait(2);
		browser.waitForVisible(saveWorkerDetailsButton);
	}
	
	//Method to verify if source country embassy window is displayed on clicking on edit
	public void verifyEditSourceCountryEmbassy() throws Exception{
		logger.info("clicking on edit source country embassy");
		browser.click(editSourceCountryEmbassy);
		browser.waitForVisible(enterSourceCountryEmbassy);
		browser.verifyEditable(enterSourceCountryEmbassy);
		browser.click(closeSourceEmbassyWindow);
	}
	
	//Method to click on Top Previous button
	public void clickTopPreviousButton() throws Exception {
		logger.info("clicking on previous button");
		browser.click(topPreviousButton);
	}
	
	//Method to click on Previous button
	public void clickPreviousButton() throws Exception {
		logger.info("clicking on previous button");
		browser.click(previousButton);
	}
	
	//Method to click on download unmatched workers list
	public void clickDownloadUnmatchedWorkersList() throws Exception {
		logger.info("clicking on download unmatched workers list");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.click(downLoadLink);
		try {
			WebDriverWait wait=new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(statusBar));
			browser.click(statusBar);
		}catch(Exception e){
			FileRename fr= new FileRename();
			String renamedFilePath=fr.renameFile("C:\\DownloadITR\\Unmatched Workers.csv","Unmatched Workers","csv");
			logger.info("Unmatched workers information file is present in the path : "+renamedFilePath);
		}finally{
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}	
	
	//Method to validate the CSV record count error message
	public void verifyCSVRecordCountErrorMessage(String expectedErrorMessage) throws Exception {
		logger.info("verifying the error message displayed when more than 100 workers are uploaded");
		new CommonElements(driver).initElements().waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(expectedErrorMessage);
	}
	
	//Method to verify if quota exceeded error message id displayed
	public void verifyQuotaExceededErrorMessage(String expectedErrorMessage) throws Exception {
		logger.info("verifying the error message displayed when more than 100 valid workers are selected");
		browser.assertText(formError, expectedErrorMessage);
	}
	
	//Method to click on download unmatched workers list
			public String clickDownloadUnmatchedWorkersListAndgetFileName() throws Exception {
				 
				String newFileName= null;
				logger.info("clicking on download unmatched workers list");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				browser.click(downLoadLink);
				try {
					WebDriverWait wait=new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.elementToBeClickable(statusBar));
					browser.click(statusBar);
				}catch(Exception e){
					 
					 File oldName = new File("C:\\DownloadITR\\Unmatched Workers.csv");
					 
					 long millis =System.currentTimeMillis();
					  newFileName="Unmatched Workers"+"_"+millis+"."+"csv";
					 
					 String pathname="C:\\DownloadITR\\"+newFileName;
					 
				     File newName = new File(pathname);
				      if(oldName.renameTo(newName)) {
				         System.out.println("renamed");
				      } else {
				         System.out.println("Error");
				      }   
	 
					logger.info("Unmatched workers information file is present in the path : "+pathname);
					 
				} 
				return newFileName;
			}
			
		//Method to verify if CSV error list is displayed when uploaded csv data is invalid
		public void verifyIfCSVErrorListIsDisplayed() throws Exception {
			logger.info("verifying if the csv error list is displayed when invalid csv data is uploaded");
			browser.verifyVisible(csvErrorListPopUp);
			browser.verifyText(rowNoHeader, "Row No.");
			browser.verifyText(columnNoHeader, "Column No.");
			browser.verifyText(columnNameHeader, "Column Name");
			browser.verifyText(errorMessageHeader, "Error Message");
			browser.verifyEditable(cancelButtonInCSVErrorLsit);
			browser.verifyEditable(exportButtonInCSVErrorList);
			if(csvInvalidDataErrorRows.size()>0)
				flag=true;
			else
				flag=false;
			browser.verifyTrue(flag);
		}
}