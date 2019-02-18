package org.fwcms.pages.cdc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fwcms.bot.BrowserBot;
import org.fwcms.prop.LoginPageProp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.olo.util.PropertyReader.configProp;


public class LoginPage {
	
	private static final Logger logger = LogManager.getLogger(LoginPage.class.getName());
	
	private BrowserBot browser;
	private WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		browser = new BrowserBot(driver);
	}
	
	public LoginPage initElements()
	{
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	@FindBy(id="userName")
	private WebElement localEnvUserNameText;
	
	@FindBy(xpath="//span[text()='Login']")
	private WebElement localEnvLoginButton;
	
	@FindBy(id="username")
	private WebElement userNameText;
	
	@FindBy(id="password")
	private WebElement passwordText;
	
	@FindBy(css="input[value='LOGIN']")
	private WebElement logginButton;
	
	@FindBy(className="hyperlink")
	private WebElement forgotPasswordLink;
	
	@FindBy(id="error")
	private WebElement errorMessage;
	
	public void loginToCDC(String username,String password) throws Exception{
		if(configProp.getProperty("environment").contains("local")){
			logger.info("typing username '"+username+"'");
			browser.type(localEnvUserNameText, username);
			logger.info("typing password '"+password+"'");
			browser.type(passwordText, password);
			logger.info("clicking login button");
			browser.click(localEnvLoginButton); 
		} else {
			logger.info("typing username '"+username+"'");
			browser.type(userNameText, username);
			logger.info("typing password '"+password+"'");
			browser.type(passwordText, password);
			logger.info("clicking login button");
			browser.click(logginButton); 
		}
	}
	
	public void verifyForgotPasswordLinkVisible() throws Exception{
		logger.info("verifying forgot password link");
		browser.verifyEditable(forgotPasswordLink);
	}
	
	public void clickForgotPassword() throws Exception{
		logger.info("clicking forgot password link");
		browser.click(forgotPasswordLink);
	}
	
	public void verifyPageTitle() throws Exception{
		logger.info("verifying page title");
		browser.Wait(1);
		String title = browser.getTitle();
		String expectedTitle = LoginPageProp.getexpectedTitle();
		if(!title.contains(expectedTitle)){
			browser.verifyFail("Page Title Not Matching Expected : "+expectedTitle+" but found Actual : "+title);
		}
	}
	
	public void verifyInvaliedLoginAccess() throws Exception{
		logger.info("verifying invalied login");
		logger.info("waiting for error message to visible");
		browser.waitForVisible(errorMessage);
		logger.info("verifying text in error message");
		browser.verifyText(errorMessage, LoginPageProp.getaccessDenied());
	}
	
}
