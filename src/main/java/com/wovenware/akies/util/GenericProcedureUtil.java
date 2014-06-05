package com.wovenware.akies.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.logging.Logger;

import com.wovenware.akies.db.to.ScoreTO;

public class GenericProcedureUtil {

	
	//Constants
	private static final String MySQLDS = "jboss/datasources/MySQLDS";
	
	//Attributes
	
	private static Logger _log = Logger.getLogger(GenericProcedureUtil.class.getName());
	
	
	//Methods
	public static ScoreTO execAkiesProcedure(String procedureName,double latitude , double longitude) throws Exception
	{
		
				
				Connection conn = null;
				//boolean hadResults = false;
				ScoreTO to = null;
				
				try
				{
					//Get Connection from JBOSS Connection pool using JNDI
					conn = ConnectionUtil.createConnection(MySQLDS, true);
				
					//Create sql statement 
					CallableStatement cStmt = conn.prepareCall("{call " + procedureName + "(?, ?, ?)}");
					//Set input params
					cStmt.setDouble(1, latitude);
					cStmt.setDouble(2, longitude);
					
					//Set output params
					cStmt.registerOutParameter(3, Types.VARCHAR);
					
					//Execute statement
					cStmt.execute();
					//Get Results set
					ResultSet rs = cStmt.getResultSet();
				    // Process all returned result sets
				    while (rs.next()) {
				    	
				        to = new ScoreTO();
				        to.setScore(rs.getInt(1)); 
				        to.setDetail(rs.getString(2)); 
				        to.setDescription(rs.getString(3)); 
				        //Output message
				        to.setResultMessage(cStmt.getString(3));

				    }//end while
				}//end try
			    catch(Exception ex)
			    {
			    	_log.severe("Failed data access object: " + ex.getMessage());
			    	throw ex;
			    	
			    }finally
			    {
			    	try
					{
						if (conn != null && ! conn.isClosed())
						{
							conn.close();
						}
					}
					catch (Exception ex)
					{
						_log.severe("Failed to close connection " + ex.getMessage());
					}			
			    	
			    }
				
				//If result is empty is going to return null
				return to;
	}
	
	
}
