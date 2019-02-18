package org.fwcms.cdc.vdr;

import org.fwcms.util.MySqlAccess;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import java.io.FileReader;
import java.util.HashMap;

public class CreateTestAgent {
	
	@DataProvider(name="createAgentData")
	public Object[][] createAgent() throws Exception{
		JSONArray ja = (JSONArray) new JSONParser().parse(new FileReader(CreateTestAgent.class.getResource("/org/fwcms/cdc/vdr/createAgentData.json").getFile()));
		Object[][] result = new Object[ja.size()][1];
		for(int i=0;i<ja.size();i++){
			result[i][0] = ja.get(i);
		}
		return result;
	}
	
	@Test(dataProvider="createAgentData")
	public void testCreateAgent(HashMap<String, String> data) throws Exception {
		new MySqlAccess().createTestAgent(data.get("agentName"), data.get("agentCountry"));
		
	}

}
