package org.iiitb.courseproject.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.courseproject.model.*;


public class StudentDAO extends HibernateDAO<Student> {
	String entity="Student";
	
	public List<Student> getAllStudents(){
		return super.list(new Student());
	}
	
	public boolean checkStudentByEmail(String email) {
		 Student s=super.find(entity, "email", email);
		 if(s==null) {
			 return false;
		 }
		 return true;
	}
	
	public Student getStudentByEmail(String email) {
		return super.find(entity, "email", email);
	}

	public Student getStudentById(int id) {
		return super.find(entity, "idStudent", id);
	}

	public int addStudent(Student user)
	{
		try {
		return super.add(user);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

}