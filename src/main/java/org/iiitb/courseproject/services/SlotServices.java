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
@Path("/Slot")
public class SlotServices{

	@POST
	@Path("/getSlotsByDateId/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Slot> getSlotsByDateId(@PathParam("id") int date_id){
		
		SlotDAO dao = new SlotDAO();
		List<Slot> slots = dao.getSlotsByDateId(date_id);
		if(slots==null)
			return null;
		else
			return slots;
	}
	
	@POST
	@Path("/getSlotsByStudentId/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<StudentBookingHistory> getSlotsByStudentId(@PathParam("id") int student_id){
		
		StudentBookingHistoryDAO dao = new StudentBookingHistoryDAO();
		List<StudentBookingHistory> bookings = dao.getBookingsByStudentId(student_id);
		if(bookings==null)
			return null;
		else
			return bookings;
	}
	
	@POST
	@Path("/getDateIdBySlotId/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public int getDateIdBySlotId(@PathParam("id") int slot_id){
		SlotDAO s=new SlotDAO();
		return s.getDateIdBySlotId(slot_id);
	}
	
	
	
}