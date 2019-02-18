package org.fwcms.pages.cdc.vdr;
 

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class PurchaseInsurancePage {
 
		private static final Logger logger = LogManager
				.getLogger(PurchaseInsurancePage.class.getName());

		private BrowserBot browser;
		private WebDriver driver;

		public PurchaseInsurancePage(WebDriver driver) {
			this.driver = driver;
			browser = new BrowserBot(driver);
		}

		public PurchaseInsurancePage initElements() {
			return PageFactory.initElements(driver, PurchaseInsurancePage.class);
		}
		
		
		@FindBy(id="generate_transaction_id")
		private WebElement buttonGenerateTransactionID;
		
		@FindBy(xpath="//div[@class='vdr_itr_generation_ack_popup popups']//span[@class='confirm_alert']/strong")
		private WebElement confirmITRGenerationPopUpMessage;
		
		@FindBy(id="confirm_itr_generation")
		private WebElement buttonIUnderstand;
		
		@FindBy(id="cancel_itr_generation")
		private WebElement buttonCancel;
		
		@FindBy(id="confirm_insurance_refno")
		private WebElement buttonProceed;
		
		@FindBy(css = "div#abortVDRProcess>span:nth-child(2)>span")
		private WebElement abortButton;
		
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
		
		@FindBy(css = "div[class='steps_nav']>div")
		private List<WebElement> navigationStepsList;
		
		@FindBy(className="applicationId")
		private WebElement applicationId;
		
		@FindBy(className="sector")
		private WebElement sector;
		
		@FindBy(id="agentNationality")
		private WebElement nationality;
		
		@FindBy(id="selectedOccupationFromList")
		private WebElement occupation;
		
		@FindBy(id="selectedEmbassyFromList")
		private WebElement sourceCountryEmbassy;
		
		@FindBy(id="expiryVDRDate")
		private WebElement vdrExpiryDate;
		
		@FindBy(css="div[class^='workers_list_container']>div[class='listRow']")
		private List<WebElement> workersList;
		
		@FindBy(id="worker_name_search")
		private WebElement searchWorkerTextBox;
		
		@FindBy(className="checkAll")
		private WebElement workersCheckBox;
		
		@FindBy(id="thankyouMessage")
		private WebElement thankYouMessage;
		
		@FindBy(css="div[class='vdr_itr_generation_online_popup popups']")
		private WebElement purchaseInsuranceOptionsPopUp;
		
		@FindBy(id="radio_online_purchase")
		private WebElement onlinePurchaseRadioButton;
		
		@FindBy(id="offline_assist_option")
		private WebElement offlineAssistPurchaseRadioButton;
		
		@FindBy(id="offline_purchase_option")
		private WebElement offlineSelfPurchaseRadioButton;
		
		@FindBy(id="cancel_itr_online_popup")
		private WebElement cancelButtonInPurchaseInsuranceOptionsPopUp;
		
		@FindBy(id="confirm_itr_purchase")
		private WebElement proceedButtonInPurchaseInsuranceOptionsPopUp;
		
		@FindBy(id="radio_online_subOptions")
		private WebElement onlinePurchaseSubOptions;
		
		@FindBy(css="div#radio_online_subOptions>div:nth-child(1)>input")
		private WebElement firstOnlineInsuranceCompanyRadioButton;
		
		@FindBy(css="div[class='purchase_insurance_confirmation_popup popups']")
		private WebElement confirmInsurancePurchasePopUp;
		
		@FindBy(xpath="//div[@class='purchase_insurance_confirmation_popup popups']//span[@class='confirm_alert']/strong")
		private WebElement confirmInsurancePurchasePopUpMessage;
		
		@FindBy(xpath="//div[@class='purchase_insurance_confirmation_popup popups']//div[contains(@class,'cancel')]")
		private WebElement cancelButtonInConfirmPurchaseInsurancePopUp;
		
		@FindBy(id="confirm_insurance_purchase")
		private WebElement confirmButtonInConfirmInsurancePurchasePopUp;
		
		//Method to generate transaction ids
		public void generateTransactionIds() throws Exception {
			logger.info("Waiting for Generate Transaction ID button to be visible");
			browser.Wait(1);
			browser.waitForVisible(buttonGenerateTransactionID);
			logger.info("Clicking on Generate Transaction ID button");
			browser.click(buttonGenerateTransactionID);
		}
		
		//Method to verify the ITR generation confirmation pop up message
		public void assertITRGenerationConfirmationPopUp(String confirmationMsg) throws Exception {
			logger.info("Asserting the message in ITR Generation Confirmation Pop Up");
			browser.assertText(confirmITRGenerationPopUpMessage, confirmationMsg);
			browser.assertEditable(buttonIUnderstand);
			browser.assertEditable(buttonCancel);
			browser.click(buttonCancel);
		}
		
		//Method to click on I Understand button in confirmation pop up
		public void clickIUnderstandButton() throws Exception {
			logger.info("Waiting for I Understand button to be visible");
			browser.waitForVisible(buttonIUnderstand);
			logger.info("Clicking on I Understand button");
			browser.click(buttonIUnderstand);
		}
		
		//Method to click on Proceed button in Download Transaction Details Pop Up
		public void clickProceedButton() throws Exception {
			logger.info("Waiting for Proceed button to be visible");
			browser.waitForVisible(buttonProceed);
			logger.info("Clicking on Proceed button");
			browser.click(buttonProceed);
		}
		
		//Method to click on Abort button
		public void clickAbortButton() throws Exception {
			logger.info("Clicking Abort Button");
			browser.waitForVisible(abortButton);
			abortButton.click();
		}
		
		//Method to verify the abort pop up
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
		
		//Method to click Yes in abort pop up
		public void clickYesInAbortPopUp() throws Exception {
			logger.info("Clicking Abort YES Button");
			browser.waitForVisible(abortYesButton);
			abortYesButton.click();
		}
		
		//Method to verify if Purchase Insurance is active
		public void verifyPurchaseInsuranceIsActive() throws Exception {
			logger.info("verifying if Purchase Insurance is active");
			Iterator<WebElement> navigationSteps = navigationStepsList.iterator();
			int i = 0;
			while (navigationSteps.hasNext()) {
				WebElement eachNavigation = navigationSteps.next();
				String className = browser.getAttribute(eachNavigation, "class");
				if (i == 3) {
					if (!className.contains("active")) {
						browser.verifyFail("Purchase insurance is not active");
					}
				} else {
					if (!className.contains("inactive")) {
						logger.info("Multiple steps are active where only Purchase Insurance should be active");
					}
				}
				i++;
			}
		}
		
		//Method to verify elements present in Purchase Insurance page
		public void verifyElementsPresentInPurchaseInsurancePage() throws Exception {
			logger.info("verifying the elements present in Purchase Insurance page");
			browser.assertVisible(applicationId);
			browser.assertVisible(sector);
			browser.assertVisible(nationality);
			browser.assertVisible(occupation);
			browser.assertVisible(sourceCountryEmbassy);
			browser.assertVisible(vdrExpiryDate);
			browser.assertTrue(workersList.size()>0);
			browser.assertEditable(searchWorkerTextBox);
			browser.assertEditable(buttonGenerateTransactionID);
			browser.assertEditable(abortButton);
		}
		
		//Method to verify if worker checkbox is disabled
		public void verifyIfWorkersCheckboxIsDisabled() throws Exception {
			logger.info("verifying if workers checkbox is disabled");
			browser.assertAttribute(workersCheckBox, "disabled", "true");
			browser.assertAttribute(workersCheckBox, "checked", "true");
		}
		
		//Method to verify if Thank You message is displayed
		public void verifyIfThankYouMessageIsDisplayed() throws Exception {
			logger.info("verifying if thank you message is displayed on clicking on proceed button");
			browser.verifyVisible(thankYouMessage);
		}
		
		//Method to verify the elements present in purchase insurance options pop up
		public void verifyElementsInPurchaseInsuranceOptionsPopUp() throws Exception {
			logger.info("verifying the elements present in purchase insurance options pop up");
			browser.verifyVisible(purchaseInsuranceOptionsPopUp);
			browser.verifyEditable(onlinePurchaseRadioButton);
			browser.verifyEditable(offlineAssistPurchaseRadioButton);
			browser.verifyEditable(offlineSelfPurchaseRadioButton);
			browser.verifyVisible(cancelButtonInPurchaseInsuranceOptionsPopUp);
			browser.verifyVisible(proceedButtonInPurchaseInsuranceOptionsPopUp);
			browser.click(cancelButtonInPurchaseInsuranceOptionsPopUp);
		}
		
		//Method to select an insurance purchase option
		public void selectPurchaseInsuranceOption(String option) throws Exception {
			logger.info("selecting an insurance purchase option");
			if(option.equalsIgnoreCase("Online")){
				browser.click(onlinePurchaseRadioButton);
			}else if(option.equalsIgnoreCase("Offline With Assistance")){
				browser.click(offlineAssistPurchaseRadioButton);
			}else if(option.equalsIgnoreCase("Offline Self Assistance")){
				browser.click(offlineSelfPurchaseRadioButton);
			}else
				System.out.println("Incorrect option");
		}
		
		//Method to click on proceed in purchase insurance options pop up
		public void clickOnProceedInPurchaseInsuranceOptionsPopUp() throws Exception {
			logger.info("clicking on proceed in purchase insurance options pop up");
			browser.click(proceedButtonInPurchaseInsuranceOptionsPopUp);
		}
		
		//Method to verify elements in online purchase insurance pop up
		public void verifyElementsInOnlinePurchaseInsuranceOption() throws Exception {
			browser.verifyVisible(onlinePurchaseSubOptions);
			browser.verifyVisible(cancelButtonInPurchaseInsuranceOptionsPopUp);
			browser.verifyVisible(proceedButtonInPurchaseInsuranceOptionsPopUp);
			browser.click(cancelButtonInPurchaseInsuranceOptionsPopUp);
		}
		
		//Method to select first online purchase insurance company
		public void selectFirstOnlinePurchaseInsuranceCompany() throws Exception {
			logger.info("selecting the first online purchase insurance company");
			browser.click(firstOnlineInsuranceCompanyRadioButton);
			browser.verifyEditable(proceedButtonInPurchaseInsuranceOptionsPopUp);
			browser.click(proceedButtonInPurchaseInsuranceOptionsPopUp);
		}
		
		//Method to verify confirm insurance purchase pop up
		public void verifyConfirmInsurancePurchasePopUp() throws Exception {
			logger.info("verifying the confirm insurance purchase pop up");
			browser.verifyVisible(confirmInsurancePurchasePopUp);
			browser.verifyText(confirmInsurancePurchasePopUpMessage, "Are you sure you want to confirm Insurance Purchase?");
			browser.verifyEditable(cancelButtonInConfirmPurchaseInsurancePopUp);
			browser.verifyEditable(confirmButtonInConfirmInsurancePurchasePopUp);
			browser.click(cancelButtonInConfirmPurchaseInsurancePopUp);
		}
		
		//Method to click on confirm in confirm insurance purchase pop up
		public void clickConfirmInConfirmInsurancePurchasePopUp() throws Exception {
			logger.info("clicking on the confirm button");
			browser.waitForVisible(confirmButtonInConfirmInsurancePurchasePopUp);
			browser.click(confirmButtonInConfirmInsurancePurchasePopUp);
		}
}	