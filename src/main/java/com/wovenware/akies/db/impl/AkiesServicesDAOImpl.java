package com.wovenware.akies.db.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.logging.Logger;

import com.wovenware.akies.db.AkiesServicesDAO;
import com.wovenware.akies.db.to.ScoreTO;
import com.wovenware.akies.util.ConnectionUtil;

public class AkiesServicesDAOImpl implements AkiesServicesDAO {

	
	//Attributes
	private Connection conn = null;
	private Logger _log = Logger.getLogger(this.getClass().getName());
	
	//Constructor
	public AkiesServicesDAOImpl(){}
	
	//Methods
	@Override
	public ScoreTO getCrimeScore(double latitude, double longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreTO getHospitalScore(double latitude, double longitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreTO getSchoolsScore(double latitude, double longitude) throws Exception {
		
		//Get Connection from JBOSS Connection pool using JNDI
		conn = ConnectionUtil.createConnection("jboss/datasources/MySQLDS", true);
		boolean hadResults = false;
		ScoreTO to = null;
		
		try
		{
		
			//Create sql statement 
			CallableStatement cStmt = conn.prepareCall("{call SP_GET_SCHOOL_SCORE(?, ?, ?)}");
			//Set input params
			cStmt.setDouble(1, latitude);
			cStmt.setDouble(2, longitude);
			
			//Set output params
			cStmt.registerOutParameter(3, Types.VARCHAR);
			
			//Execute statement
			hadResults = cStmt.execute();
	
		    // Process all returned result sets
		    while (hadResults) {
		    	
		        ResultSet rs = cStmt.getResultSet();
		        to = new ScoreTO();
		        to.setScore(46); //Dummy
		        to.setDetail(rs.getString(2)); //Testing DB
		        to.setDescription(rs.getString(3)); //Testing DB
	
		        hadResults = cStmt.getMoreResults();
		    }//end while
		}//end try
	    catch(Exception ex)
	    {
	    	_log.severe("Failed data access object: " + ex.getMessage() + ex.getStackTrace().toString());
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
		
		conn.close();
		
		return to;
	}

}
