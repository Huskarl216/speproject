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
@Path("/Student_Slot_Request")
public class Student_Slot_RequestServices{
	@POST
	@Path("/getRequestsBySlotId/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Student_Slot_Request> getRequestsBySlotId(@PathParam("id") int slot_id){
		Student_Slot_RequestDAO ssrdao = new Student_Slot_RequestDAO();
		StudentDAO studentdao = new StudentDAO();
		
		List<Student_Slot_Request> requests = ssrdao.getRequestsBySlotId(slot_id);
		if(requests==null)
			return null;
		else
			return requests;
	}
	
	@POST
	@Path("/getRequestsByStudentId/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Student_Slot_Request> getRequestsByStudentId(@PathParam("id") int student_id){
		Student_Slot_RequestDAO ssrdao = new Student_Slot_RequestDAO();
		StudentDAO studentdao = new StudentDAO();
		
		List<Student_Slot_Request> requests = ssrdao.getRequestsByStudentId(student_id);
		if(requests==null)
			return null;
		else
			return requests;
	}
	

	@POST
	@Path("/getRequestsByStudentIdAndCounselorId")
	@Consumes("application/json")
	@Produces("application/json")
	public String getRequestsByStudentIdAndCounselorId(String data) throws JSONException {
		JSONObject object = new JSONObject(data);
		int counselor_id = (int)object.getInt("counselor_id");
		int student_id = (int)object.getInt("student_id");
		
		Student_Slot_RequestDAO ssrdao = new Student_Slot_RequestDAO();
		SlotDAO s=new SlotDAO();
		List<Student_Slot_Request> list= ssrdao.getRequestsByStudentId(student_id);
		JSONArray first = new JSONArray();
		
		
		try {
//			System.out.println(list.size());
			for(int i=0;i<list.size();i++) {
				Student_Slot_Request ssr=list.get(i);
				JSONObject details= new JSONObject();
				int slot_id=ssr.getSlot_id();
//				System.out.print("slot_id ");
//				System.out.println(slot_id);
				int cid=s.getCounselorIdBySlotId(slot_id);
/*				
				System.out.print("Counselor id ");
				System.out.println(counselor_id);
				System.out.print("Cid ");
				System.out.println(cid);
*/				
				if(counselor_id==cid) {
					details.append("slot_id", ssr.getSlot_id());
					details.append("request_id", ssr.getIdRequest());
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
	@Path("/getRequestsBySlotIdWithStudentNames/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public String getRequestsBySlotIdWithStudentNames(@PathParam("id") int slot_id) throws JSONException{
		Student_Slot_RequestDAO ssrdao = new Student_Slot_RequestDAO();
		StudentDAO studentdao = new StudentDAO();
		
		List<Student_Slot_Request> requests = ssrdao.getRequestsBySlotId(slot_id);
		
		JSONArray allrequests = new JSONArray();
		
		try {
			for(int i=0;i<requests.size();i++) {
				Student_Slot_Request Request = requests.get(i);
				JSONObject object= new JSONObject();
				object.append("Student_id", Request.getStudent_id());
				object.append("Slot_id",Request.getSlot_id());
				object.append("idRequest",Request.getIdRequest());
				
				Student s = studentdao.getStudentById(Request.getStudent_id());
				object.append("name",s.getName());
				
				allrequests.put(object);
				
			}
			return allrequests.toString();
		}catch(Exception e) {	
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/addRequest")
	@Consumes("application/json")
	@Produces("application/json")
	public String addRequest(String data) throws JSONException{
		JSONObject object = new JSONObject(data);
		int slot_id = (int)object.getInt("slot_id");
		int student_id = (int)object.getInt("student_id");
		
		System.out.println("HELOOOOOO");
		System.out.println(slot_id);
		

		SlotDAO sdao=new SlotDAO();
		Slot slot=sdao.getSlotbySlotId(slot_id);
		if(slot==null) {
			return "0";
		}
		if(slot.isStatus()) {
			return "0";
		}
		
		Student_Slot_RequestDAO ssrdao = new Student_Slot_RequestDAO();

		
		Student_Slot_Request S=new Student_Slot_Request();
		S.setSlot_id(slot_id);
		S.setStudent_id(student_id);
		try {
			int x=ssrdao.addRequest(S);
			if(x==-1) {
				return "0";
			}
			return "1";
		}catch(Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
}