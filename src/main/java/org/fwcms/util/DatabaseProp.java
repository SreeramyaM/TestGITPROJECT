package org.fwcms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class DatabaseProp {
	
	private static final Logger logger = LogManager.getLogger(DatabaseProp.class.getName());
	
	private static Properties prop = new Properties();
	
	static{
		try {
			if(prop.isEmpty()){
				logger.info("Loading database.properties");
				prop.load(DatabaseProp.class.getResourceAsStream("/config/database.properties"));
			}
		} catch (FileNotFoundException e) {
			logger.error("File Not Found in the specified location "+e.getMessage());
		} catch (IOException e) {
			logger.error("Could not able to open file "+e.getMessage());
		}
	}
	
	public static String getmysqlUrl(){
		return prop.getProperty("mysql.url");
	}
	
	public static String getmysqlUserName(){
		return prop.getProperty("mysql.username");
	}
	
	public static String getmysqlPassword(){
		return prop.getProperty("mysql.password");
	}
	
}
