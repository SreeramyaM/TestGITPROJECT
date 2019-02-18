package org.fwcms.pages.cdc.vdr;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewVdrCreatePage {
	
	private static final Logger logger = LogManager.getLogger(NewVdrCreatePage.class.getName());
	
	private BrowserBot browser;
	private WebDriver driver;
	
	public NewVdrCreatePage(WebDriver driver){
		this.driver = driver;
		browser = new BrowserBot(driver);
	}
	
	public NewVdrCreatePage initElements()
	{
		return PageFactory.initElements(driver, NewVdrCreatePage.class);
	}
	
	@FindBy(className="searchWorkerWrapper")
	private WebElement searchWorkerWrapper;
	
	
	@FindBy(css="div[class='steps_nav']>div")
	private List<WebElement> navigationStepsList;
	
	@FindBy(className="nav_step_mid")
	private List<WebElement> navigationStepNameList;
	
	
	//New WebElements
	@FindBy(css="div.pageHeading>span")
	private  WebElement  pageHeader;
	
	@FindBy(xpath="//div[contains(@title,'{1}')]")
	private static WebElement selectKDN;
	@FindBy(className="vdr_step_instructions")
	private WebElement vdrStepInstruction;
	
	/**
	 * Verify KDN headers
	 * @throws Exception
	 */
	public void verifyNavigationStepAreVisibleAndNameOfStep() throws Exception{
		logger.info("verifying navigation step names");
		logger.info("verifying size of the steps");
		browser.verifyEquals(navigationStepNameList.size(), 5);
		browser.verifyVisible(navigationStepNameList.get(0));
		browser.verifyEquals(browser.getText(navigationStepNameList.get(0)), "WORKER\nSELECTION");
		browser.verifyVisible(navigationStepNameList.get(1));
		browser.verifyEquals(browser.getText(navigationStepNameList.get(1)), "WORKER\nPOINT OF ENTRY");
		browser.verifyVisible(navigationStepNameList.get(2));
		browser.verifyEquals(browser.getText(navigationStepNameList.get(2)), "SUBMIT\nVDR");
		browser.verifyVisible(navigationStepNameList.get(3));
		browser.verifyEquals(browser.getText(navigationStepNameList.get(3)), "PURCHASE\nINSURANCE");
		browser.verifyVisible(navigationStepNameList.get(4));
		browser.verifyEquals(browser.getText(navigationStepNameList.get(4)), "GOVERNMENT\nFEES");
		 
	}
	
	/**
	 * Verify the Page header
	 * @throws Exception
	 */
	public void verifyVDRApplicationHeader() throws Exception{
		logger.info("Verify VDR Application header");
		browser.waitForEditable(pageHeader);
		String header=browser.getText(pageHeader);
		browser.verifyEquals(header.trim(), "VDR Application");
	}
	/**
	 * Verify VDR Step instruction
	 * @throws Exception
	 */
	public void verifyVDRStepInstruction() throws Exception{
		logger.info("Verify VDR Step instruction");
		browser.waitForEditable(vdrStepInstruction);
		String value =browser.getText(vdrStepInstruction);
		browser.verifyEquals(value.trim(), "Please click on the preferred KDN details row to proceed further.");
	}
 
	
	

}
