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
@Path("/Counselor")
public class CounselorServices{
	
	@GET
	@Path("/getAllCounselors/")
	@Produces("application/json")
	public List<Counselor> getCounselors(){
		CounselorDAO dao= new CounselorDAO();
		List<Counselor> list=dao.getAllCounselors();
		return list;
	}

	
	@POST
	@Path("/acceptRequest/")
	@Consumes("application/json")
	@Produces("application/json")	
	public String acceptRequest(String data)  throws JSONException{
		JSONObject Data = new JSONObject(data);
//		int student_id = (int)Data.getInt("student_id");
//		int slot_id = (int)Data.getInt("slot_id");
		int request_id = (int)Data.getInt("request_id");

		StudentBookingHistoryDAO sbhdao = new StudentBookingHistoryDAO();
		Student_Slot_RequestDAO ssrdao = new Student_Slot_RequestDAO();
		SlotDAO slotdao = new SlotDAO();
		StudentDAO studentdao=new StudentDAO();
		
		Student_Slot_Request ssr=ssrdao.getRequestByRequestId(request_id);
		int slot_id=ssr.getSlot_id();
		int student_id=ssr.getStudent_id();
		
		Slot s= slotdao.getSlotbySlotId(slot_id);
		
		if(s.isStatus()==true) {
			return null;
		}
		else {
			JSONObject details = new JSONObject();
			Slot updatedslot = new Slot();
			updatedslot.setStatus(true);
			
			Student student_object=studentdao.getStudentById(student_id);
			String student_name=student_object.getName();
			
			slotdao.setStatus(updatedslot, slot_id);
			sbhdao.addBooking(new StudentBookingHistory(student_id,slot_id));
			ssrdao.removeAllBySlotId(slot_id);
			
			details.append("student_name",student_name );
			details.append("slot_id",slot_id );
			return details.toString();
			
		}
	}
	
	
}