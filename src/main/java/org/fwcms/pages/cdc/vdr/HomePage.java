package org.fwcms.pages.cdc.vdr;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	private static final Logger logger = LogManager.getLogger(HomePage.class.getName());
	
	private BrowserBot browser;
	private WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		browser = new BrowserBot(driver);
	}
	
	public HomePage initElements()
	{
		return PageFactory.initElements(driver, HomePage.class);
	}
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	@FindBy(className="active_user")
	private WebElement userNameInHeader;
	
	@FindBy(css="div[class='fwcms_logo']>div:nth-child(1)")
	private WebElement organizationInHeader;
	
	@FindBy(css="div[class='footer-container']>a:nth-child(2)>span:nth-child(2)")
	private WebElement updatesLink;
	
	@FindBy(css="div[class='footer-container']>a:nth-child(3)>span:nth-child(2)")
	private WebElement announcementsLink;
	
	@FindBy(css="div[class='footer-container']>a:nth-child(4)>span:nth-child(2)")
	private WebElement contactsLink;
	
	@FindBy(css="div[class='footer-container']>a:nth-child(5)>span:nth-child(2)")
	private WebElement helpLink;
	
	@FindBy(css="div[class^='active_user_info']")
	private WebElement userInfo;
	
	@FindBy(css="div[class='active_user_block']>p:nth-child(2)")
	private WebElement contactNumberInUserInfo;
	
	@FindBy(css="div[class='active_user_block']>p:nth-child(3)")
	private WebElement emailInUserInfo;
	
	@FindBy(className="sticky_nav_text")
	private WebElement stickyActions;
	
	@FindBy(className="sticky_navigation_menu")
	private WebElement stickyNavigationMenu;
	
	@FindBy(className="sticky_nav_backmenu")
	private WebElement stickyNavigationBackMenu;
	
	@FindBy(id="menuitem_employer_registration")
	private WebElement managePicCredentialsLink;
	
	@FindBy(id="menuitem_VDR_process")
	private WebElement vdrProcessLink;
	
	@FindBy(css="div[id='nav-container-wrapper'] div[class='navcontainer']>ul>li:nth-child(2)>a")
	private WebElement navigationVdrProcessLink;
	
	@FindBy(id="menuitem_change_password")
	private WebElement changePasswordLink;
	
	@FindBy(id="menuitem_quota_counter")
	private WebElement quotaCounterLink;
	
	@FindBy(id="menuitem_plks")
	private WebElement plksLink;
	
	@FindBy(id="menuitem_checkout_com")
	private WebElement checkOutMemoLink;
	
	@FindBy(css="div[class$='new_VDR']")
	private WebElement createNewVdrLinkInActions;
	
	@FindBy(css="div[id='nav-container-wrapper'] div[class='subnavcontainer']>ul>li:nth-child(1)>a")
	private WebElement navigationCreateNewVdrLinkInActions;
		
	@FindBy(css="div[class$='cancel_VDR']")
	private WebElement cancelVdrLinkInActions;
	
	@FindBy(css="div[class$='replace_VDR']")
	private WebElement replaceVdrLinkInActions;
	
	@FindBy(css="div[class$='search_VDR']")
	private WebElement searchVdrLinkInActions;
	
	@FindBy(className="pageHeading")
	private WebElement pageHeadingAction;
	
	@FindBy(className="sticky_nav_headertext")
	private WebElement stickyNavHeader;
	
	@FindBy(css="div[id='nav-container-wrapper'] div[class='navcontainer']>ul>li:nth-child(6)>a>div:nth-child(2)")
	private WebElement reportsAndAccountManagementMenu;
	
	@FindBy(css="div[class='nav-list-img change_password']")
	private WebElement changePasswordMenu;
	
	@FindBy(css="div[id='nav-container-wrapper'] div[class='subnavcontainer']>ul>li:nth-child(3)>a>div:nth-child(2)")
	private WebElement managePICcredentialsMenu;
	
//New webElements
	@FindBy(css="div[id='nav-container-wrapper'] div[class='subnavcontainer']>ul>li:nth-child(4)>a")
	private WebElement navigationSearchVdrLinkInActions;
	
	@FindBy(linkText="Check status of all the applications")
	private WebElement linkCheckStatusOfAllApplications;
	
	@FindBy(css="div[class='latest_block notsubmitted VDR']")
	private WebElement savedVdrBloack;
	
	@FindBy(css="div[class^='latest_block Inactive VDR']")
	private WebElement abortedVdrBloack;
	
	@FindBy(css="span[title=' View Workers List']")
	private WebElement iconViewWorkerDetails;
	
	@FindBy(css="span[title=' Abort the VDR application']")
	private WebElement iconAbortApplication;
	
	@FindBy(css="span[title=' Continue the VDR application']")
	private WebElement iconResumeApplication;
	
	@FindBy(id="menuitem_appstatus")
	private WebElement linkApplicationStatusInActions;
	
	@FindBy(css="div[id='nav-container-wrapper'] div[class='navcontainer']>ul>li:nth-child(5)>a")
	private WebElement navigationApplicationStatusLink;
	
	@FindBy(id="menuitem_accmanagement")
	private WebElement reportsAndAccountManagementLinkInActions;
	
	@FindBy(css="div[class$='change_password']")
	private WebElement changePasswordLinkInActions;
	
	@FindBy(css="div[class$='employer_registration']")
	private WebElement managePICCredentialsLinkInActions;
	
	@FindBy(id="nav-content")
	private WebElement latestApplicationsBlock;
			
	//Clicking on 'MENU'
	public void clickStickyActions() throws Exception{
		logger.info("clicking sticky actions");
		browser.waitForEditable(stickyActions);
		browser.click(stickyActions);
	}
 
	//Clicking on 'VDR Applications' from MENU	 
	public void clickVDRApplicationsLink() throws Exception {
		logger.info("Clicking VDR Applications Link");
		browser.waitForEditable(vdrProcessLink);
		browser.click(vdrProcessLink);
		 
	}
	 
	//Verifying if Create New action is present in the MENU
	public void verifyCreateNewLink() throws Exception {
		logger.info("Verify Create New Link");
		browser.verifyElementPresent(createNewVdrLinkInActions);
	}
	
	//Clicking on Create New action in MENU
	public void clickCreateNewLink() throws Exception {
		logger.info("Clicking Create New Link");
		browser.waitForEditable(createNewVdrLinkInActions);
		browser.click(createNewVdrLinkInActions);
		browser.Wait(3);
	}
	
	//Clicking on  Menu -> VDR Applications -> Create New
	public void clickNewVdrLinkInActions() throws Exception{
		logger.info("clicking new vdr link in sticky actions");
		browser.Wait(1);
		if(browser.isVisible(stickyActions)){
			clickStickyActions();
			browser.waitForEditable(vdrProcessLink);
			browser.click(vdrProcessLink);
			browser.waitForEditable(createNewVdrLinkInActions);
			browser.click(createNewVdrLinkInActions);
			browser.Wait(3);
		}else{
			browser.waitForEditable(navigationVdrProcessLink);
			browser.click(navigationVdrProcessLink);
			browser.waitForEditable(navigationCreateNewVdrLinkInActions);
			browser.click(navigationCreateNewVdrLinkInActions);
		}
	}
	
	//Clicking on  Menu -> VDR Applications -> Search / Continue / Print / Abort
	public void clickSearchContinuePrintAbortLinkInActions() throws Exception{
		logger.info("Clicking Search / Continue / Print / Abort link in sticky actions");
		browser.Wait(1);
		if(browser.isVisible(stickyActions)){
			clickStickyActions();
			browser.waitForEditable(vdrProcessLink);
			browser.click(vdrProcessLink);
			browser.waitForEditable(searchVdrLinkInActions);
			browser.click(searchVdrLinkInActions);
			browser.Wait(3);
		}else{
			browser.waitForEditable(navigationVdrProcessLink);
			browser.click(navigationVdrProcessLink);
			browser.waitForEditable(navigationSearchVdrLinkInActions);
			browser.click(navigationSearchVdrLinkInActions);
		}
	}
	
	//Clicking on  Menu -> Application Status
	public void clickApplicationStatusInActions() throws Exception{
		logger.info("Clicking Application Status link in sticky actions");
		browser.Wait(1);
		if(browser.isVisible(stickyActions)){
			clickStickyActions();
			browser.waitForEditable(linkApplicationStatusInActions);
			browser.click(linkApplicationStatusInActions);
			}else{
				browser.waitForEditable(navigationApplicationStatusLink);
				browser.click(navigationApplicationStatusLink);
			}
	}
	
	//Verifying if sign out link is visible
	public void assertSignOutLinkVisible() throws Exception{
		logger.info("checking sign out link is visible");
		browser.assertVisible(signOutLink);
	}
	
	//Clicking on sign out link
	public void clickSignOutLink() throws Exception{
		logger.info("clicking signout link");
		logger.info("sleeping for 2 sec");
		browser.Wait(2);
		browser.click(signOutLink);
	}
	
	//Verifying the visibility of actions tab on expanding and collapsing
	public void verifyActionsTabCollapsedAndVisible() throws Exception{
		logger.info("verifying actions tab collapsed and visible");
		browser.click(stickyActions);
		browser.Wait(1);
		browser.verifyVisible(stickyNavigationMenu);
		browser.click(stickyActions);
		browser.Wait(1);
		browser.verifyNotVisible(stickyNavigationMenu);
	}
 
	//Verifying the actions present in MENU
	public void verifyVdrOptionsInMenuActionsTab() throws Exception{
		logger.info("asserting vdr options in actions tab");
		logger.info("asserting the presence of MENU tab");
		browser.assertElementPresent(stickyActions);
		browser.assertText(stickyActions, "M E N U");
		browser.click(stickyActions);
		logger.info("verifying the header text of sticky actions");
		browser.assertElementPresent(stickyNavHeader);
		browser.assertText(stickyNavHeader, "Select An Action");
	 
		browser.waitForVisible(stickyNavigationMenu);
		logger.info("asserting the presence of VDR Applications link");
		browser.assertElementPresent(vdrProcessLink);
		browser.waitForVisible(vdrProcessLink);
		browser.click(vdrProcessLink);
		browser.Wait(1);
		logger.info("asserting the presence of Create New link");
		browser.assertElementPresent(createNewVdrLinkInActions);
		logger.info("asserting the presence of Search/Continue/Print/Abort VDR");
		browser.assertElementPresent(searchVdrLinkInActions);
		browser.assertVisible(stickyNavigationBackMenu);
		browser.click(stickyNavigationBackMenu);
		browser.Wait(1);
		browser.assertNotVisible(stickyNavigationBackMenu);
		browser.click(vdrProcessLink);
		browser.waitForVisible(stickyNavigationBackMenu);
		browser.assertVisible(createNewVdrLinkInActions);
		browser.assertVisible(searchVdrLinkInActions);
		 
		browser.click(stickyActions);
		browser.waitForNotVisible(stickyNavigationBackMenu);
		browser.click(stickyActions);
		logger.info("asserting the presece of Application Status link");
		browser.assertElementPresent(linkApplicationStatusInActions);
		
		logger.info("verifying the presence of Reports & Account Management link");
		browser.assertElementPresent(reportsAndAccountManagementLinkInActions);
		browser.waitForVisible(reportsAndAccountManagementLinkInActions);
		browser.click(reportsAndAccountManagementLinkInActions);
		browser.Wait(1);
		logger.info("asserting the presence of Change Password link");
		browser.assertElementPresent(changePasswordLinkInActions);
		logger.info("asserting the presence of Manage PIC Credentials link");
		browser.assertElementPresent(managePICCredentialsLinkInActions);
		browser.assertVisible(stickyNavigationBackMenu);
		browser.click(stickyNavigationBackMenu);
		browser.Wait(1);
		browser.assertNotVisible(stickyNavigationBackMenu);
		browser.click(reportsAndAccountManagementLinkInActions);
		browser.waitForVisible(stickyNavigationBackMenu);
		browser.assertVisible(changePasswordLinkInActions);
		browser.assertVisible(managePICCredentialsLinkInActions);
		 
		browser.click(stickyActions);
		browser.waitForNotVisible(stickyNavigationBackMenu);
	}
	
	//Clicking on the link 'Check status of all applications'
	public void clickCheckStatusOfApplicationsLink() throws Exception {
		logger.info("Clicking on the link 'Check Status Of All Applications'");
		browser.click(linkCheckStatusOfAllApplications);
	}
	
	//Verifying the options available for saved vdr application
	public void verifyOptionsOfSavedVdrApplication() throws Exception {
		logger.info("Verifying the options available for saved vdr application");
		try{
				if(browser.isElementPresent(savedVdrBloack)){
				browser.actions().moveToElement(savedVdrBloack).perform();
				browser.Wait(2);
				browser.verifyVisible(iconViewWorkerDetails);
				browser.verifyVisible(iconAbortApplication);
				browser.verifyVisible(iconResumeApplication);
				}
		}catch(NoSuchElementException e){
			System.out.println("There are no vdr applications with 'Saved' status on Home Page");
		}
	}
	
	//Verifying the options available for aborted vdr application
	public void verifyOptionsOfAbortedVdrApplication() throws Exception {
		logger.info("Verifying the options available for aborted vdr application");
		browser.Wait(2);
		try{
			if(browser.isElementPresent(abortedVdrBloack)){
				browser.actions().moveToElement(abortedVdrBloack).build().perform();
				browser.verifyVisible(iconViewWorkerDetails);
				browser.verifyNotVisible(iconAbortApplication);
				browser.verifyNotVisible(iconResumeApplication);
			}
		}catch(NoSuchElementException e){
			System.out.println("There are no vdr applications with 'Aborted' status on Home Page");
		}
	}
	
	//Method to click on the saved vdr application
	public void clickSavedVdrApplication(String applicationID) throws Exception {
		logger.info("clicking on saved vdr application");
		browser.actions().moveToElement(browser.findElement(By.xpath("//div[contains(text(),'"+applicationID+"')]"))).perform();
		browser.waitForVisible(iconResumeApplication);
		browser.click(iconResumeApplication);
	}
	
	//Method to verify if vdr application is aborted
	public void verifyIfVdrApplicationIsAborted(String applicationId) throws Exception {
		logger.info("waiting for home page to load");
		browser.waitForVisible(latestApplicationsBlock);
		logger.info("verifying if the status of the application is aborted");
		browser.verifyText(browser.findElement(By.xpath("//div[contains(text(),'"+applicationId+"')]/following-sibling::div[@id='status']")), "Aborted");
	}
	
	
		
}
