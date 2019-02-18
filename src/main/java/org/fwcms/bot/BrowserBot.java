package org.fwcms.bot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserBot extends com.olo.bot.BrowserBot{
	
	private WebDriver driver;
	public BrowserBot(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	public void waitForElementSize(final List<WebElement> element, final int size){
		new WebDriverWait(driver, getTimeOut()) {
    	}.until(new ExpectedCondition<Boolean>() {
    		public Boolean apply(WebDriver driver) {
    			return element.size() == size;
    		}
    	});
	}
	
	
	public static WebElement replaceValues(WebDriver driver, WebElement element, String...values){
		
		String locatorValue= element.toString();
		 
		String[] valueArray = locatorValue.split("By.");
		 
		String[] locValue = valueArray[1].split(":");
		String elementType=locValue[0].trim();
		String elementValue=locValue[1].trim();
		 
		elementValue = elementValue.replaceAll("]$","");
		 
		 if (elementValue != null) {
	            
	            for (int i = 0; i < values.length; i++) {
	            	 
	                String value = values[i];
	                
	                elementValue = elementValue.trim().replace("{" + (i + 1) + "}", value);       
	            }
	        }
	
	 elementValue = elementValue.replaceAll("'$","");
	 System.out.println("elementValue after "+elementValue);
		 
		 WebElement newElement = driver.findElement(byLocator(elementType,elementValue));
	 
			return newElement;
		 
		 
	}
	
	
	public static By byLocator(String elementType, String elementValue) {
		By value=null;
		switch (elementType) {
		case "css selector": value = By.cssSelector(elementValue);
							break;
		case "xpath":  
								value =  By.xpath(elementValue);
							 break;
		case "class name": value=  By.className(elementValue);
							break;
		case "id": value= By.id(elementValue);
							break;
		case "link text": value=  By.linkText(elementValue);
							break;
		case "partial link text": value=  By.partialLinkText(elementValue);
							break;
		case "name": value= By.name(elementValue);
						break;
		default:
			By.xpath(elementValue);;
		}
		return value;	
		
	}
	
	
}
