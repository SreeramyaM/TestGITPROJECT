package org.fwcms.pages.cdc.vdr;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.fwcms.pages.CommonElements;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitVDRPage {

	private static final Logger logger = LogManager
			.getLogger(SubmitVDRPage.class.getName());

	private BrowserBot browser;
	private WebDriver driver;

	public SubmitVDRPage(WebDriver driver) {
		this.driver = driver;
		browser = new BrowserBot(driver);
	}

	public SubmitVDRPage initElements() {
		return PageFactory.initElements(driver, SubmitVDRPage.class);
	}

	@FindBy(css = "input.declaration_check")
	private WebElement selectDeclarationCheck;

	@FindBy(css = "div[class='steps_nav']>div")
	private List<WebElement> navigationStepsList;

	@FindBy(css = "div#abortVDRProcess>span:nth-child(2)>span")
	private WebElement abortButton;

	@FindBy(css = "div#cancel_declaration>span:nth-child(2)>span")
	private WebElement previousButton;

	@FindBy(css = "a#termsConditionsId")
	private WebElement termsAndCondition;

	@FindBy(id = "footer")
	private WebElement footerTag;

	@FindBy(css = "span.footer-icons.link-home")
	private WebElement footerHomeLink;

	@FindBy(css = ".footer-icons.link-contact")
	private WebElement footerContactLink;

	@FindBy(css = ".footer-icons.link-download")
	private WebElement footerEmployerInstructionManualLink;

	@FindBy(css = "span.link-name-footer-terms")
	private WebElement footerTermsAndConditionLink;

	@FindBy(css = ".footer_logo")
	private WebElement footerLogo;

	@FindBy(css = "div#submit_declaration>span:nth-child(2)>span")
	private WebElement selectSubmitButton;

	@FindBy(css = "span#agentNationality")
	private WebElement headerNationality;

	@FindBy(css = "span#nationality")
	private WebElement formNationality;

	@FindBy(css = "div[id^='submitVDRDeclarationPop']")
	private WebElement confirmationBox;

	@FindBy(css = "div[id='abortVDRPopup']")
	private WebElement abortBox;

	@FindBy(css = "span.confirm_alert>strong")
	private WebElement confirmationBoxMessage;

	@FindBy(css = "p.msg_descr")
	private WebElement confirmationBoxMessageDescription;

	@FindBy(css = "span.confirm_alert>strong")
	private WebElement abortBoxMessage;

	@FindBy(css = "p.msg_descr")
	private WebElement abortBoxMessageDescription;

	@FindBy(css = "div#cancel_worker_deletion>span:nth-child(2)>span")
	private WebElement confirmationNoButton;

	@FindBy(css = "div#submitVDRYes>span:nth-child(2)>span")
	private WebElement confirmationYesButton;

	@FindBy(css = "div#cancel_worker_deletion>span:nth-child(2)>span")
	private WebElement abortNoButton;

	@FindBy(css = "div#approve_worker_deletion>span:nth-child(2)>span")
	private WebElement abortYesButton;

	@FindBy(css = "div[class='addMoreWorkers']>div>a")
	private WebElement addMoreWorkersLink;

	@FindBy(css = "a.editEmbassy")
	private WebElement editEmbassyLink;

	@FindBy(className = "sector")
	private WebElement sector;

	@FindBy(id = "agentNationality")
	private WebElement nationality;

	@FindBy(id = "selectedOccupationFromList")
	private WebElement occupations;

	@FindBy(id = "selectedEmbassyFromList")
	private WebElement sourceCountryEmbassy;

	@FindBy(id = "OTP")
	private WebElement textOneTimePassword;

	@FindBy(id = "vdr_submit_otp")
	private WebElement buttonOkInConfirmOTPPopUp;
	
	@FindBy(className = "searchWorkerWrapper")
	private WebElement searchWorkerWrapper;
	
	@FindBy(id="regenerate_otp")
	private WebElement buttonRegenarateOTP;
	
	@FindBy(className="close_overlay_embassy")
	private WebElement closeSourceEmbassyWindow;
	
	@FindBy(className="editEmbassy")
	private WebElement editSourceCountryEmbassy;
	
	@FindBy(id = "worker_overlay_embassy_id")
	private WebElement enterSourceCountryEmbassy;
	
	@FindBy(id="reg_pic_mobile_number")
	private WebElement otpMobileNumber;
	
	@FindBy(id="reg_pic_email")
	private WebElement otpEmailId;

	public void isDeclarationCheckboxUnSelectedByDefault() throws Exception {
		logger.info("Verify Declaration check box Defalut Status");
		Assert.assertFalse(selectDeclarationCheck.isSelected());
		logger.info(" Declaration check box is unselected By Defalut");
	}

	public void selectDeclarationCheckBox() throws Exception {
		logger.info("Select Declaration Check Box");
		selectDeclarationCheck.click();
	}

	public void clickSubmitButton() throws Exception {
		logger.info("Clicking Submit Button");
		browser.waitForVisible(selectSubmitButton);
		selectSubmitButton.click();
	}

	public void clickAbortButton() throws Exception {
		logger.info("Clicking Abort Button");
		browser.waitForVisible(abortButton);
		abortButton.click();
	}

	public void verifySubmitButtonDisplay() throws Exception {
		logger.info("Verify Submit Button Display");
		if (selectDeclarationCheck.isSelected()) {
			Assert.assertTrue(selectSubmitButton.isDisplayed());
		} else {
			Assert.assertFalse(selectSubmitButton.isDisplayed());
		}
		

	}

	public void verifyAbortButtonDisplay() throws Exception {
		logger.info("Verify Abort Button Display");
		browser.assertTrue(abortButton.isDisplayed());

	}

	public void verifyPreviousButtonDisplay() throws Exception {
		logger.info("Verify Previous Button Display");
		browser.assertTrue(previousButton.isDisplayed());

	}

	public void verifyTermsAndConditionsDisplay() throws Exception {
		logger.info("Verify Terms and Conditions link Display");
		browser.assertTrue(termsAndCondition.isDisplayed());

	}

	public void verifyFooterDisplay() throws Exception {
		logger.info("Verify Footer Display");
		logger.info("Verify footerTag ");
		browser.assertTrue(footerTag.isDisplayed());
		logger.info("Verify FfooterHomeLink ");
		browser.assertTrue(footerHomeLink.isDisplayed());
		logger.info("Verify footerContactLink ");
		browser.assertTrue(footerContactLink.isDisplayed());
		logger.info("Verify footer Employer Instruction ManualLink ");
		browser.assertTrue(footerEmployerInstructionManualLink.isDisplayed());
		logger.info("Verify footer TermsAndCondition Link");
		browser.assertTrue(footerTermsAndConditionLink.isDisplayed());
	}

	public void verifyNationality() throws Exception {
		logger.info("Verifying Nationality");
		browser.waitForVisible(formNationality);
		String headerNationalityValue = browser.getText(headerNationality);
		String formNationalityValue = browser.getText(formNationality);
		browser.assertEquals(headerNationalityValue, formNationalityValue);
	}

	public void verifySubmitButtonConfirmationPopup() throws Exception {
		logger.info("verifying confirmation Pop up ");

		browser.waitForVisible(confirmationBox);
		browser.verifyText(confirmationBoxMessage,
				"Please ensure that you have added as many Worker(s) needed.");
		browser.verifyText(
				confirmationBoxMessageDescription,
				"Once submitted you will not be able to add any more Worker(s) in this VDR application.Click 'Yes' to proceed further or 'No' to cancel.");
		Assert.assertTrue(confirmationNoButton.isDisplayed());
		Assert.assertTrue(confirmationYesButton.isDisplayed());

		browser.click(confirmationYesButton);

	}

	public void clickYesInConfirmationPopUp() throws Exception {
		logger.info("Clicking Yes confirmation Pop up ");
		browser.waitForVisible(confirmationBox);
		confirmationYesButton.click();
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

	/**
	 * Verify worker selection tab is active
	 * 
	 * @throws Exception
	 */
	public void verifySubmitVDRIsActive() throws Exception {
		logger.info("verifying SUBMIT VDR is active");
		Iterator<WebElement> navigationSteps = navigationStepsList.iterator();
		int i = 0;
		while (navigationSteps.hasNext()) {
			WebElement eachNavigation = navigationSteps.next();
			String className = browser.getAttribute(eachNavigation, "class");
			if (i == 2) {
				if (!className.contains("active")) {
					browser.verifyFail("SUBMIT VDR is not active");
				}
			} else {
				if (!className.contains("inactive")) {
					logger.info("Multiple steps are active where only SUBMIT VDR should be active");
				}
			}
			i++;
		}
	}

	public void verifyElementsPresentInSubmitVDRPage() throws Exception {
		logger.info("verifying elements in Submit VDR page ");
		browser.verifyVisible(addMoreWorkersLink);
		browser.verifyEditable(editEmbassyLink);
		browser.verifyVisible(sector);
		browser.verifyVisible(nationality);
		browser.verifyVisible(occupations);
		browser.verifyVisible(sourceCountryEmbassy);
	}

	public void clickYesInAbortPopUp() throws Exception {
		logger.info("Clicking Abort YES Button");
		browser.waitForVisible(abortYesButton);
		abortYesButton.click();
	}

	public void enterAndConfirmOTP(String OTP) throws Exception {
		logger.info("Entering the OTP");
		browser.type(textOneTimePassword, OTP);
		logger.info("Clicking OK in Confirm OTP Pop Up");
		browser.Wait(4); //changed from 1 to 4 seconds
		browser.click(buttonOkInConfirmOTPPopUp);
		browser.Wait(2);
	}

	// Method to click on Add More Workers link
	public void clickAddMoreWorkersLink() throws Exception {
		logger.info("waiting for Add More Workers link to be visible");
		browser.waitForVisible(addMoreWorkersLink);
		logger.info("clicking on add more workers link");
		browser.click(addMoreWorkersLink);
	}
	
	//Method to regenerate OTP
	public void regenerateOTP() throws Exception {
		logger.info("clicking on regenerate OTP");
		browser.click(buttonRegenarateOTP);
	}
	
	//Method to validate the error message when invalid OTP is entered
	public void validateInvalidOTP(String invalidOTPExpectedErrorMessage) throws Exception {
		logger.info("verifying the error message when invalid OTP is entered");
		browser.Wait(2);
		new CommonElements(driver).initElements().waitForStatusBarToBeVisibleAndVerifyTextAndCloseStatusBarAndWaitForOverlayToHIde(invalidOTPExpectedErrorMessage);
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
	
	//Method to verify elements present in Confirm OTP pop up
	public void verifyElementsPresentInConfirmOTPPopUp() throws Exception {
		logger.info("verifying the elements present in Confirm OTP pop up");
		browser.assertVisible(otpMobileNumber);
		browser.assertVisible(otpEmailId);
		browser.assertEditable(textOneTimePassword);
		browser.assertEditable(buttonOkInConfirmOTPPopUp);
		browser.assertEditable(buttonRegenarateOTP);
	}

}
