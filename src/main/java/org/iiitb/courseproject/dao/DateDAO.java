package org.iiitb.courseproject.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.iiitb.courseproject.model.Date;
import org.iiitb.courseproject.model.Student;

public class DateDAO extends HibernateDAO<Date> {
	String entity="Date";

	public List<Date> getAllDatesbyCounselorID(int id){
		return super.findAll(entity, "Counselor_id", id);
	}

	public Date getDateByDateId(int id) {
		return super.find(entity, "idDate", id);
	}
	
	public int getCounselorIdByDateId(int date_id) {
		Date d=super.find(entity, "idDate", date_id);		
		return d.getCounselor_id();
	}
}