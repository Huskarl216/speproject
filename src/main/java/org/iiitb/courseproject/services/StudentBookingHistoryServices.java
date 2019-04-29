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
@Path("/StudentBookingHistory")
public class StudentBookingHistoryServices {

	@POST
	@Path("/getBookingsByStudentIdAndCounselorId")
	@Consumes("application/json")
	@Produces("application/json")
	public String getBookingsByStudentIdAndCounselorId(String data) throws JSONException {
		JSONObject object = new JSONObject(data);
		int counselor_id = (int)object.getInt("counselor_id");
		int student_id = (int)object.getInt("student_id");
		
		StudentBookingHistoryDAO sbhdao = new StudentBookingHistoryDAO();
		SlotDAO s=new SlotDAO();
		List<StudentBookingHistory> list= sbhdao.getBookingsByStudentId(student_id);
		JSONArray first = new JSONArray();
		
		
		try {
			System.out.println("Size:");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++) {
				StudentBookingHistory sbh=list.get(i);
				JSONObject details= new JSONObject();
				int slot_id=sbh.getSlot_id();
				System.out.print("slot_id ");
				System.out.println(slot_id);
				int cid=s.getCounselorIdBySlotId(slot_id);
				
				System.out.print("Counselor id ");
				System.out.println(counselor_id);
				System.out.print("Cid ");
				System.out.println(cid);
				
				if(counselor_id==cid) {
					details.append("slot_id", sbh.getSlot_id());
					details.append("request_id", sbh.getIdHistory());
					first.put(details);
				}
			}
			return first.toString();
		}catch(Exception e) {	
			e.printStackTrace();
			return null;
		}

	}


	@POST
	@Path("/getBookingBySlotIdWithStudentNames/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String getBookingBySlotIdWithStudentNames(@PathParam("id") int slot_id) throws JSONException {
		
		System.out.print("HELLOOOOOOOOOOOOOOO ");
		System.out.println(slot_id);

		StudentBookingHistoryDAO sbhdao = new StudentBookingHistoryDAO();
		StudentDAO s=new StudentDAO();
		StudentBookingHistory sbh= sbhdao.getBookingBySlotId(slot_id);
		JSONArray first = new JSONArray();
		
		
		try {
			JSONObject details= new JSONObject();
				
			int booking_id=sbh.getIdHistory();
			int student_id=sbh.getStudent_id();
			Student student_object=s.getStudentById(student_id);
			String student_name=student_object.getName();
				
				
			details.append("slot_id", slot_id);
			details.append("student_id", student_id);
			details.append("student_name", student_name);
			details.append("booking_id",booking_id );
				
			return details.toString();
		}catch(Exception e) {	
			e.printStackTrace();
			return null;
		}

	}

}