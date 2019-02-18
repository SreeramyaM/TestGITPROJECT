package org.fwcms.cdc.vdr;


import java.io.FileReader;
import java.util.HashMap;

import org.fwcms.initiators.VDRInitiator;
import org.fwcms.util.MySqlAccess;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FetchApplicationIDUsingPassportNumberAndAbortIt extends VDRInitiator {
	@DataProvider(name="vdrValidationData")
	public Object[][] searchVdrByApplicationStatus() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(VDRApplicationStatusTest.class.getResource("/org/fwcms/cdc/vdr/vdrValidationData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	
	@Test(dataProvider="vdrValidationData")
	public void fetchApplicationIDUsingPassportNumberAndAbortItTest(HashMap<String, String> data) throws Exception {
			 
			String mPassportNum=data.get("passportNumberValidData");
			MySqlAccess mysqlQuery= new MySqlAccess();
			System.out.println("Update the Application status AS ABORTED");
			mysqlQuery.getApplicationNumberUsingPassportNumberAndABortIt(mPassportNum);
			System.out.println("  Application status is changed to ABORTED");
	}
	
	
}

