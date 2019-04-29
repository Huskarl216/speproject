package org.iiitb.courseproject.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.courseproject.model.Date;
import org.iiitb.courseproject.model.Slot;

public class SlotDAO extends HibernateDAO<Slot> {
	String entity="Slot";

	public List<Slot> getSlotsByDateId(int id){
		return super.findAll(entity, "Date_id", id);
	}
	
	
	public int getDateIdBySlotId(int slotid) {
		Slot s=super.find(entity, "idSlot", slotid);
		return s.getDate_id();		
	}
	
	public Slot getSlotbySlotId(int slotid) {
		return super.find(entity,"idSlot", slotid);
	}
	
	public int getCounselorIdBySlotId(int slotid) {
		int date_id=this.getDateIdBySlotId(slotid);
		System.out.print("Date_id=");
		System.out.println(date_id);
		if(date_id==0) {
			return 0;
		}
		DateDAO d=new DateDAO();
		return d.getCounselorIdByDateId(date_id);
	}
	
	public int setStatus(Slot slot,int slot_id) {
		try {
			List<Field> fields = new ArrayList<Field>();
			Field status_field = slot.getClass().getDeclaredField("Status");
			status_field.setAccessible(true);
			fields.add(status_field);

			if(super.update(slot, "idSlot", slot_id, fields)==1) {
				return 1;
			}
			else {
				return 0;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
}