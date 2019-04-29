package org.iiitb.courseproject.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.iiitb.courseproject.model.*;

public class CounselorDAO extends HibernateDAO<Counselor> {
	String entity="Counselor";
	
	public List<Counselor> getAllCounselors(){
		return super.list(new Counselor());
	}
	
	public int addCounselor(Counselor user)
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