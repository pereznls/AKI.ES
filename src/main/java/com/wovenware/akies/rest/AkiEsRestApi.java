package com.wovenware.akies.rest;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.wovenware.akies.db.AkiesServicesDAO;
import com.wovenware.akies.db.impl.AkiesServicesDAOImpl;
import com.wovenware.akies.db.to.ScoreTO;

@Path("/score")
public class AkiEsRestApi {
	private Logger _log = Logger.getLogger(this.getClass().getName());
	
	@GET
	@Path("/{lon}/{lat}")
	public Response printMessage(@PathParam("lon") String lon, @PathParam("lat") String lat) {
		
		JSONObject jsonObject = new JSONObject();
		AkiesServicesDAO dao = null;
		ScoreTO to = null;
		
		
		try
		{
			
			JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObjectDetail = null;
			
			dao = new AkiesServicesDAOImpl();
			to = dao.getSchoolsScore(18.486639999999994, -66.97359599999999);
			
			// Police
			jsonObjectDetail = new JSONObject();
			jsonObjectDetail.put("detail", "police");
			jsonObjectDetail.put("score", 80);
			jsonObjectDetail.put("description", "Police score description.");
			
			jsonArray.add(jsonObjectDetail);
			
			// Schools
			jsonObjectDetail = new JSONObject();
			jsonObjectDetail.put("detail", to.getDetail());
			jsonObjectDetail.put("score", to.getScore());
			jsonObjectDetail.put("description", to.getDescription());
			
			jsonArray.add(jsonObjectDetail);
			
			// Hospitals
			jsonObjectDetail = new JSONObject();
			jsonObjectDetail.put("detail", "hospital");
			jsonObjectDetail.put("score", 90);
			jsonObjectDetail.put("description", "Hospital score description.");
			
			jsonArray.add(jsonObjectDetail);
			
			jsonObject.put("score", 100);
			jsonObject.put("description", "This is the main description.");
			jsonObject.put("details", jsonArray);
		}
		catch (Exception ex)
		{
			_log.severe("Failed executing api: " + ex.getMessage());
			
			jsonObject.put("errorMessage", "An error has ocurred! Please try again later.");
			jsonObject.put("errorDetails", ex.getMessage());
		}
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonObject.toJSONString()).build();
 
	}
 
}
