package org.fwcms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.SkipException;

public class MySqlAccess {
	
	private static final Logger logger = LogManager.getLogger(MySqlAccess.class.getName());
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet  = null;
	
	
	private void close(){
		try {
			if(resultSet!=null){
				resultSet.close();
			}
			if(statement!=null){
				statement.close();
			}
			if(connect!=null){
				connect.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	//Method to create an agent
	public void createTestAgent(String companyName, String country) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DatabaseProp.getmysqlUrl(), DatabaseProp.getmysqlUserName(), DatabaseProp.getmysqlPassword());
			statement = connect.createStatement();
			statement.execute("INSERT INTO `AGENT_LKUP` (`CMPNY_NM`, `CMPNY_ADR`, `CNTRY`,`CMPNY_PHN_NO`,`OWNR_EMAIL`,`CNTC_NM`, `CNTC_EMAIL`, `CMPNY_REG_NO`, `CNTC_NO`) VALUES( '"+companyName+"', 'Test Address', '"+country+"', '9090909090', 'fwcmstestagent@gmail.com', 'Mr.Test Agent' , 'fwcmstestagent@gmail.com', '' , '9090909090');");
			resultSet = statement.executeQuery("select last_insert_id() as last_id from AGENT_LKUP");
			resultSet.next();
			String resultId=resultSet.getString("last_id");
			System.out.println(resultId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SkipException(e.getMessage(),e.getCause());
		}finally{
			close();
		}
	}
	
	//Method to retrieve OTP of employer for vdr submission
	public String getOTPOfEmpForVdrSubmission(String userName) throws Exception
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DatabaseProp.getmysqlUrl(), DatabaseProp.getmysqlUserName(), DatabaseProp.getmysqlPassword());
			statement = connect.createStatement();
			resultSet=statement.executeQuery("select OTP_PSSWRD_TX from USR_LKUP where USER_NAME='"+userName+"'");
			resultSet.next();
			return resultSet.getString(1);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new SkipException(e.getMessage(),e.getCause());
		}finally{
			close();
		}
	}
	

	//Method to retrieve application ID and Abort it
			public void  getApplicationNumberUsingPassportNumberAndABortIt(String passportNumber) throws Exception
			{
				try {
					String appId=null;
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection(DatabaseProp.getmysqlUrl(), DatabaseProp.getmysqlUserName(), DatabaseProp.getmysqlPassword());
					statement = connect.createStatement();
					resultSet = statement.executeQuery("select vm.VISA_APPLICATION_NO from VISA_MASTER vm, VISA_WRKR_DTL vw, WRKR_REG_DTL wr where vm.VISA_MASTER_ID = vw.VISA_MASTER_ID and wr.WRKR_REG_DTL_ID = vw.WRKR_REG_DTL_ID and wr.WRKR_PSSPRT_NR = '"+passportNumber+"' and vm.VISA_STATUS_CD !='VDR_INACTIVE'");
					 
					 if (resultSet.next()) {
					    	resultSet.last();
							int totalRows= resultSet.getRow();
							resultSet.beforeFirst();
							System.out.println("totalRows "+totalRows);
							
							
							 for(int i=1;i<=totalRows;i++){
								 System.out.println("Inside for loop "+i);
					  					ResultSet resultSet1 = statement.executeQuery("select vm.VISA_APPLICATION_NO from VISA_MASTER vm, VISA_WRKR_DTL vw, WRKR_REG_DTL wr where vm.VISA_MASTER_ID = vw.VISA_MASTER_ID and wr.WRKR_REG_DTL_ID = vw.WRKR_REG_DTL_ID and wr.WRKR_PSSPRT_NR = '"+passportNumber+"' and vm.VISA_STATUS_CD !='VDR_INACTIVE'");
										System.out.println("after 1st query");
										resultSet1.next();
					  					System.out.println("ApplicationNumber which is not in abort status "+resultSet1.getString(1));
					   					appId= resultSet1.getString(1);
					   			 statement.executeUpdate("UPDATE VISA_MASTER vm , VISA_WRKR_DTL vw , WRKR_REG_DTL wr SET vm.VISA_STATUS_CD = 'VDR_INACTIVE', vm.VISA_STAT_IN = 0, vw.WRKR_VISA_STATUS_CD = 'UNTAGGED', wr.REG_STAT_CD = 'REG_VALIDATED' where vm.VISA_APPLICATION_NO = '"+appId+"' and vm.VISA_MASTER_ID = vw.VISA_MASTER_ID and wr.WRKR_REG_DTL_ID = vw.WRKR_REG_DTL_ID");
							 
				  				}
 		 
					} else {
					   System.out.println("Result Set is Empty. No Data available from abortApplicationNumberUsingPassportNumber");
					}  

				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new SkipException(e.getMessage(),e.getCause());
				}finally{
					close();
				}
				
			}
			

			//Update Application Status to Abort
				public void updateApplicationIDToAbort(String appId) throws Exception
				{
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(DatabaseProp.getmysqlUrl(), DatabaseProp.getmysqlUserName(), DatabaseProp.getmysqlPassword());
						statement = connect.createStatement();
						resultSet = statement.executeQuery("select vp.VDR_PRA_STAT_IN from VDR_PRA_MASTER vp where vp.VDR_PRA_APPLICATION_NO ='"+appId+"'");
						resultSet.next();
					 
	  					System.out.println("ApplicationNumber which is not in abort status "+resultSet.getString(1));
	   					appId= resultSet.getString(1);
						 statement.executeUpdate("UPDATE VISA_MASTER vm , VISA_WRKR_DTL vw , WRKR_REG_DTL wr SET vm.VISA_STATUS_CD = 'VDR_INACTIVE', vm.VISA_STAT_IN = 0, vw.WRKR_VISA_STATUS_CD = 'UNTAGGED', wr.REG_STAT_CD = 'REG_VALIDATED' where vm.VISA_APPLICATION_NO = '"+appId+"' and vm.VISA_MASTER_ID = vw.VISA_MASTER_ID and wr.WRKR_REG_DTL_ID = vw.WRKR_REG_DTL_ID");
						 
						  
					} catch (Exception e) {
						logger.error(e.getMessage());
						throw new SkipException(e.getMessage(),e.getCause());
					}finally{
						close();
					}
				}
	
}
