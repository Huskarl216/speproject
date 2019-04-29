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
@Path("/Student")
public class StudentServices{
	
	@POST
	@Path("/requestSlot/")
	@Consumes("application/json")
	@Produces("application/json")	
	public int requestSlot(String data)  throws JSONException{
		JSONObject Data = new JSONObject(data);
		int student_id = (int)Data.getInt("student_id");
		int slot_id = (int)Data.getInt("slot_id");
		
		Student_Slot_Request R = new Student_Slot_Request();
		R.setSlot_id(slot_id);
		R.setStudent_id(student_id);
		
		Student_Slot_RequestDAO S=new Student_Slot_RequestDAO();
		return S.addRequest(R);
	}
	
	
}