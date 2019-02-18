package org.fwcms.prop.cdc;

import static com.olo.util.PropertyReader.configProp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class vdrApplicationPageProp {
	
	private static final Logger logger = LogManager.getLogger(vdrApplicationPageProp.class.getName());
	
	private static Properties prop = new Properties();
	
	static{
		try {
			if(prop.isEmpty()){
				logger.info("Loading vdrApplicationPage.properties");
				String lang = "en";
				if(configProp.containsKey("language")){
					lang = configProp.getProperty("language");
				}
				prop.load(vdrApplicationPageProp.class.getResourceAsStream("/language/"+lang+"/vdrApplicationPage.properties"));
			}
		} catch (FileNotFoundException e) {
			logger.error("File Not Found in the specified location "+e.getMessage());
		} catch (IOException e) {
			logger.error("Could not able to open file "+e.getMessage());
		}
	}
	
		
	public static String getVdrSubmissionMessage(){
		return prop.getProperty("vdrSubmissionPage");
	}
	
	public static String getConfirmITRGenerationMessage() {
		return prop.getProperty("confirmITRGeneration");
	}
	
	public static String getITRGenerationSuccessMessage() {
		return prop.getProperty("itrGenerationSuccessMessage");
	}
	
	public static String getSearchKDNEmptyExpectedErrorMessage(){
		return prop.getProperty("searchKDNEmptyMessage");
	}
	
	public static String getSearchKDNMinCharExpectedErrorMessage() {
		return prop.getProperty("searchKDNMinCharDataExpectedMessage");
	}
	
	public static String getSearchKDNInvalidExpectedErrorMessage() {
		return prop.getProperty("searchKDNInvalidDataExpectedMessage");
	}
	
	public static String getAgentIdInvalidExpectedErrorMessage(){
		return prop.getProperty("agentIdInvalidMessage");
	}
	
	public static String getAgentIdNotFoundExpectedErrorMessage() {
		return prop.getProperty("agentIdNotFoundMessage");
	}
	
	public static String getCSVFileNotUploadedExpectedErrorMessage() {
		return prop.getProperty("csvFileNotUploadedMessage");
	}
	
	public static String getWorkersNotAddedExpectedErrorMessage() {
		return prop.getProperty("workersNotAddedMessage");
	}
	
	public static String getPassportEmptyExpectedErrorMessage() {
		return prop.getProperty("passportNumberEmptyMessage");
	}
	
	public static String getPassportSpaceSpecialCharNotAllowedExpectedErrorMessage(){
		return prop.getProperty("passportNumberSpaceSpecialCharNotAllowedMessage");
	}
	
	public static String getWorkersCannotBeAddedExpectedErrorMessage(){
		return prop.getProperty("workersCannotBeAddedMessage");
	}
	
	public static String getSearchWorkerEmptyExpectedErrorMessage(){
		return prop.getProperty("searchWorkerEmptyMessage");
	}
	
	public static String getSearchWorkerSpaceExpectedErrorMessage() {
		return prop.getProperty("searchWorkerSpaceOnlyMessage");
	}
	
	public static String getSearchWorkerMinCharExpectedErrorMessage(){
		return prop.getProperty("searchWorkerMinCharMessage");
	}
	
	public static String getSearchWorkerSpecialCharExpectedErrorMessage(){
		return prop.getProperty("searchWorkerSpecialCharMessage");
	}
	
	public static String getWorkersSelectionPageBottomInfoMessage(){
		return prop.getProperty("workerSelectionPageBottomInfoMessage");
	}
	
	public static String getWorkersSavedSuccessMessage() {
		return prop.getProperty("workersSavedSuccessMessage");
	}
	
	public static String getSourceEmbassyInvalidExpectedErrorMessage(){
		return prop.getProperty("sourceEmbassyInvalidMessage");
	}
	
	public static String getDeleteWorkersSuccessMessage() {
		return prop.getProperty("deleteWorkersSuccessMessage");
	}
	
	public static String getSelectWorkersToDeleteMessage() {
		return prop.getProperty("selectWorkersToDeleteMessage");
	}
	
	public static String getRegenerateOTPSuccessMessage() {
		return prop.getProperty("regenerateOTPSuccessMessage");
	}
	
	public static String getOTPInvalidExpectedErrorMessage(){
		return prop.getProperty("otpInvalidMessage");
	}
	
	public static String getWorkerNotFoundExpectedErrorMessage(){
		return prop.getProperty("workerNotFoundMessage");
	}

	public static String getCSVRecordCountExceededErrorMessage(){
		return prop.getProperty("csvRecordCountExceededMessage");
	}
	
	public static String getQuotaExceededErrorMessage(){
		return prop.getProperty("quotaExceededMessage");
	}
	
	public static String getInvalidCSVDataErrorMessage(){
		return prop.getProperty("csvDataInvalidMessage");
	}
}
