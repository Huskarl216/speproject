package org.iiitb.courseproject.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.courseproject.model.*;

public class Student_Slot_RequestDAO extends HibernateDAO<Student_Slot_Request> {
	String entity="Student_Slot_Request";

	public int addRequest(Student_Slot_Request S) {
		try {
			int request_id = super.add(S);
			return request_id;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public void removeAllBySlotId(int slot_id) {
		super.deleteRow(entity, "slot_id", slot_id);
	}
	
	public Student_Slot_Request getRequestByRequestId(int request_id) {
		return super.find(entity, "idRequest", request_id);
	}
	
	public List<Student_Slot_Request> getRequestsBySlotId(int slot_id){
		return super.findAll(entity, "Slot_id", slot_id);
	}
	
	public List<Student_Slot_Request> getRequestsByStudentId(int student_id){
		return super.findAll(entity, "Student_id", student_id);		
	}

	
}