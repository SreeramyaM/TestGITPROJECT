package org.fwcms.prop;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoginPageProp {
	
	private static final Logger logger = LogManager.getLogger(LoginPageProp.class.getName());
	
	private static Properties prop = new Properties();
	
	static{
		try {
			if(prop.isEmpty()){
				logger.info("Loading LoginPage.properties");
				String lang = "en";
				if(configProp.containsKey("language")){
					lang = configProp.getProperty("language");
				}
				prop.load(LoginPageProp.class.getResourceAsStream("/language/"+lang+"/LoginPage.properties"));
			}
		} catch (FileNotFoundException e) {
			logger.error("File Not Found in the specified location "+e.getMessage());
		} catch (IOException e) {
			logger.error("Could not able to open file "+e.getMessage());
		}
	}
	
	public static String getexpectedTitle(){
		return prop.getProperty("expectedTitle");
	}
	
	public static String getaccessDenied(){
		return prop.getProperty("accessDenied");
	}
}
