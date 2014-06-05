package com.wovenware.akies.db.impl;

import com.wovenware.akies.db.AkiesServicesDAO;
import com.wovenware.akies.db.to.ScoreTO;
import com.wovenware.akies.util.GenericProcedureUtil;

public class AkiesServicesDAOImpl implements AkiesServicesDAO {

	//Constants
	private final String SCHOOL_PROC = "SP_GET_SCHOOL_SCORE";
	private final String CRIME_PROC = "SP_GET_CRIME_SCORE";
	private final String HOSP_PROC = "SP_GET_HOSP_SCORE";
	
	//Attributes
	
	//Constructor
	public AkiesServicesDAOImpl(){}
	
	//Methods
	@Override
	public ScoreTO getCrimeScore(double latitude, double longitude) throws Exception {
		// TODO Auto-generated method stub
		return GenericProcedureUtil.execAkiesProcedure(CRIME_PROC, latitude, longitude);
	}

	@Override
	public ScoreTO getHospitalScore(double latitude, double longitude)throws Exception {
		// TODO Auto-generated method stub
		return GenericProcedureUtil.execAkiesProcedure(HOSP_PROC, latitude, longitude);
	}

	@Override
	public ScoreTO getSchoolsScore(double latitude, double longitude) throws Exception {
		
		return GenericProcedureUtil.execAkiesProcedure(SCHOOL_PROC, latitude, longitude);
	}
	 

}
