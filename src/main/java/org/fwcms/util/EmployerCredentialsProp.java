package org.fwcms.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class EmployerCredentialsProp {
	
	private static final Logger logger = LogManager.getLogger(EmployerCredentialsProp.class.getName());
	
	private static Properties prop = new Properties();
	
	static{
		try {
			if(prop.isEmpty()){
				logger.info("Loading credentials.properties");
				prop.load(EmployerCredentialsProp.class.getResourceAsStream("/config/credentials/employercredentials.properties"));
			}
		} catch (FileNotFoundException e) {
			logger.error("File Not Found in the specified location "+e.getMessage());
		} catch (IOException e) {
			logger.error("Could not able to open file "+e.getMessage());
		}
	}
	
	 
	
	public static String getVdrEmployerUserName(){
		return prop.getProperty("vdrEmployerUsername");
	}
	
	public static String getVdrEmployerPassword(){
		return prop.getProperty("vdrEmployerPassword");
	}
	
	public static String getVdrEmployerGmailUserName(){
		return prop.getProperty("vdrEmployerGmailUserName");
	}
	
	public static String getVdrEmployerGmailPassword(){
		return prop.getProperty("vdtEmployerGmailPassword");
	}
}
