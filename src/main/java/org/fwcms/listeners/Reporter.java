package org.fwcms.listeners;

import static com.olo.util.PropertyReader.configProp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.internal.Utils;
import org.testng.xml.XmlSuite;

import com.olo.reporter.Utility;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Reporter implements IReporter{
	
	private static final Logger logger = LogManager.getLogger(Reporter.class.getName());
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		try {
			generateNewIndex(suites,outputDirectory);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	private void generateNewIndex(List<ISuite> suites,String outputDirectory){
	    
		int totalFailedTests = 0;
		int totalPassedTests = 0;
		int totalSkippedTests = 0;
	    long startTimeOfSuites = 0;
	    long endTimeOfSuites = 0;
	    String title="Test Results";
	    
	    /*finding the start time to sort the list
	     */
	    for (ISuite suite : suites) {
	    	long suiteStartTime=0;
	    	long temp = 0;
	    	int ctr = 0;
	    	Map<String, ISuiteResult> results = suite.getResults();
	    	for (ISuiteResult suiteResult : results.values()) {
	    		ITestContext suiteTestContext = suiteResult.getTestContext();
	    		if(ctr == 0){
	    			suiteStartTime=suiteTestContext.getStartDate().getTime();
	    			ctr++;
	    		}else{
	    			temp = suiteTestContext.getStartDate().getTime();
	    			if(temp < suiteStartTime)
	    				suiteStartTime = temp;
				}
	    	}
	    	suite.setAttribute("suiteStartTime_sort", suiteStartTime);
	    }
	    
	    Collections.sort(suites, Utility.suiteStartComp);
	    
	    StringBuffer suiteListDetails = new StringBuffer();
	    
	    int totalSuitesSize = suites.size();
	    int suiteCounter = 1;
	    for (ISuite suite : suites) {
	    	if (suite.getResults().size() == 0) {
				continue;
			}
	    	
			
			int suiteFailedTests=0;
			int suitePassedTests=0;
			int suiteSkippedTests=0;
			long suiteStartTime=0;
			long suiteEndTime=0;
			long temp = 0;
			int ctr = 0;
			
			Map<String, ISuiteResult> results = suite.getResults();
			for (ISuiteResult suiteResult : results.values()) {
				ITestContext suiteTestContext = suiteResult.getTestContext();
				suiteFailedTests+= suiteTestContext.getFailedTests().size();
				suitePassedTests+= suiteTestContext.getPassedTests().size();
				suiteSkippedTests+=suiteTestContext.getSkippedTests().size();
				if(ctr == 0){
					suiteStartTime=suiteTestContext.getStartDate().getTime();
					suiteEndTime=suiteTestContext.getEndDate().getTime();
					ctr++;
				}else{
					temp = suiteTestContext.getStartDate().getTime();
					if(temp < suiteStartTime)
						suiteStartTime = temp;
					temp = suiteTestContext.getEndDate().getTime();
					if(temp > suiteEndTime)
						suiteEndTime = temp;
				}
			}
			if(suiteCounter == 1){
				startTimeOfSuites=suiteStartTime;
	    	}
			if(suiteCounter == totalSuitesSize){
				endTimeOfSuites = suiteEndTime;
			}
			suiteListDetails.append(Utility.suiteListTableDetailsRow(false,suite.getName(),suiteStartTime,suiteEndTime,suitePassedTests,suiteFailedTests,suiteSkippedTests));
			ctr=0;
			totalFailedTests+=suiteFailedTests;
			totalPassedTests+=suitePassedTests;
			totalSkippedTests+=suiteSkippedTests;
			suiteCounter++;
	    }
	    
	    StringBuffer suitesSummaryHtml = new StringBuffer();
	    suitesSummaryHtml.append(Utility.getHtmlToHead());
	    suitesSummaryHtml.append(Utility.suitesSummaryHead(title, totalPassedTests, totalFailedTests, totalSkippedTests));
	    suitesSummaryHtml.append(Utility.endHeadAndStartBody());
	    suitesSummaryHtml.append(Utility.startContainer());
	    suitesSummaryHtml.append(Utility.headerTitle(title));
	    suitesSummaryHtml.append(Utility.startRow());
	    
	    suitesSummaryHtml.append("<div class='col-md-7'><div class='table-responsive'>");
	    suitesSummaryHtml.append("<table class='table table-bordered col-md-6'>");
	    suitesSummaryHtml.append("<thead><tr><th colspan='2'><p class='text-center'>Configuration</p></th></tr></thead>");
		if(configProp.containsKey("cdcUser")){
			suitesSummaryHtml.append("<tr><th>CDC</th><td>"+configProp.getProperty("cdcUser")+"</td></tr>");
		}
		if(configProp.containsKey("tcUser")){
			suitesSummaryHtml.append("<tr><th>TC</th><td>"+configProp.getProperty("tcUser")+"</td></tr>");
		}
		if(configProp.containsKey("cdcEmployer")){
			suitesSummaryHtml.append("<tr><th>Employer Registration</th><td>"+configProp.getProperty("cdcEmployer")+"</td></tr>");
		}
		suitesSummaryHtml.append("<tr><th>Browser</th><td>"+configProp.getProperty("browser")+"</td></tr>");

		suitesSummaryHtml.append("</table>");
		suitesSummaryHtml.append("</div></div>");
	    
	    suitesSummaryHtml.append(Utility.chartDiv());
	    suitesSummaryHtml.append(Utility.endRow());
	    suitesSummaryHtml.append(Utility.startResponsiveTableDiv());
	    suitesSummaryHtml.append(Utility.startTableWithHover());
	    suitesSummaryHtml.append(Utility.suiteListTableHeaderRow());
	    suitesSummaryHtml.append(suiteListDetails);
	    suitesSummaryHtml.append(Utility.suitesSummaryRow(startTimeOfSuites, endTimeOfSuites, totalPassedTests, totalFailedTests, totalSkippedTests));
	    suitesSummaryHtml.append(Utility.endTable());
	    suitesSummaryHtml.append(Utility.endResponsiveTableDiv());
	    suitesSummaryHtml.append(Utility.endContainerToHtml());
	    
	    String fileName = "FWCMS-Suites-Summary-Index.html";
	    Utils.writeFile(outputDirectory, fileName, suitesSummaryHtml.toString());
	    
	}
	
}
