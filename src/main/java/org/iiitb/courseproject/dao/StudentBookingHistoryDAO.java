package org.iiitb.courseproject.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.courseproject.model.*;

public class StudentBookingHistoryDAO extends HibernateDAO<StudentBookingHistory> {
	String entity="StudentBookingHistory";

	public int addBooking(StudentBookingHistory S) {
		try {
			int history_id = super.add(S);
			return history_id;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<StudentBookingHistory> getBookingsByStudentId(int student_id){
		return super.findAll(entity, "Student_id", student_id);
	}

	public List<StudentBookingHistory> getBookingsBySlotId(int slot_id){
		return super.findAll(entity, "Slot_id", slot_id);
	}
	
	public StudentBookingHistory getBookingBySlotId(int slot_id) {
		return super.find(entity, "Slot_id", slot_id);
	}
}