package org.fwcms.initiators;

import static com.olo.util.PropertyReader.configProp;

import com.olo.initiator.InitiatorUtil;

public class VDRConfiguration extends InitiatorUtil{
	
	public static final String cdcUrl = configProp.getProperty("cdcUser");
	
}
