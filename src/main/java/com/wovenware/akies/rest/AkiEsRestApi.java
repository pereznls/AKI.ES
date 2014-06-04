package com.wovenware.akies.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Path("/score")
public class AkiEsRestApi {
	@GET
	@Path("/{lon}/{lat}")
	public Response printMessage(@PathParam("lon") String lon,
			@PathParam("lat") String lat) {
 
		JSONObject jsonObject = new JSONObject();
		
		JSONArray jsonArray = new JSONArray();
		
		JSONObject jsonObjectDetail = null;
		
		// Police
		jsonObjectDetail = new JSONObject();
		jsonObjectDetail.put("detail", "police");
		jsonObjectDetail.put("score", 80);
		
		jsonArray.add(jsonObjectDetail);
		
		// Schools
		jsonObjectDetail = new JSONObject();
		jsonObjectDetail.put("detail", "school");
		jsonObjectDetail.put("score", 40);
		
		jsonArray.add(jsonObjectDetail);
		
		// Hospitals
		jsonObjectDetail = new JSONObject();
		jsonObjectDetail.put("detail", "hospital");
		jsonObjectDetail.put("score", 90);
		
		jsonArray.add(jsonObjectDetail);
		
		jsonObject.put("details", jsonArray);
		
		return Response.status(200).entity(jsonObject.toJSONString()).build();
 
	}
 
}
