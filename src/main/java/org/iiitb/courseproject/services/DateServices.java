package org.iiitb.courseproject.services;

import java.util.ArrayList;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.courseproject.model.*;
import org.iiitb.courseproject.dao.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


@SuppressWarnings("unused")
@Path("/Date")
public class DateServices{
	// API to get the su-category with id
	@POST
	@Path("/getDatesByCounselorId/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Date> getDatesByCounselorId(@PathParam("id") int category_id){
		
//		System.out.println("Hello");
		DateDAO dao = new DateDAO();
		List<Date> dates = dao.getAllDatesbyCounselorID(category_id);
		if(dates==null)
			return null;
		else
			return dates;
	}
	
	
}
