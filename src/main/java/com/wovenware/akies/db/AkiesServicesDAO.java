package com.wovenware.akies.db;



import com.wovenware.akies.db.to.ScoreTO;

public interface AkiesServicesDAO {
	
	public ScoreTO getCrimeScore(double latitude, double longitude) throws Exception;
	
	public ScoreTO getHospitalScore(double latitude, double longitude) throws Exception;
	
	public ScoreTO getSchoolsScore(double latitude, double longitude) throws Exception;
	
}
