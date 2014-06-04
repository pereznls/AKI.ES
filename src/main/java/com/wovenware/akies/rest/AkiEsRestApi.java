package com.wovenware.akies.rest;

import java.sql.Connection;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.wovenware.akies.util.ConnectionUtil;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Path("/score")
public class AkiEsRestApi {
	private Logger _log = Logger.getLogger(this.getClass().getName());
	
	@GET
	@Path("/{lon}/{lat}")
	public Response printMessage(@PathParam("lon") String lon, @PathParam("lat") String lat) {
		
		JSONObject jsonObject = new JSONObject();
		
		Connection conn = null;
		
		try
		{
			conn = ConnectionUtil.createConnection("jboss/datasources/MySQLDS", true);
			
			JSONArray jsonArray = new JSONArray();
			
			JSONObject jsonObjectDetail = null;
			
			// Police
			jsonObjectDetail = new JSONObject();
			jsonObjectDetail.put("detail", "police");
			jsonObjectDetail.put("score", 80);
			jsonObjectDetail.put("description", "Police score description.");
			
			jsonArray.add(jsonObjectDetail);
			
			// Schools
			jsonObjectDetail = new JSONObject();
			jsonObjectDetail.put("detail", "school");
			jsonObjectDetail.put("score", 40);
			jsonObjectDetail.put("description", "School score description.");
			
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
			_log.severe("Failed to setup connection " + ex.getMessage());
			
			jsonObject.put("errorMessage", "An error has ocurred! Please try again later.");
			jsonObject.put("errorDetails", ex.getMessage());
		}
		finally
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
		
		return Response.status(200).entity(jsonObject.toJSONString()).build();
 
	}
 
}
