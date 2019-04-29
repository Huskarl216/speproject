package org.iiitb.courseproject.model;

public class StudentBookingHistory {
	private int idHistory;
	private int Student_id;
	private int Slot_id;
	
	public StudentBookingHistory() {
		
	}
	
	public StudentBookingHistory(int Student_id,int Slot_id) {
		this.Student_id=Student_id;
		this.Slot_id=Slot_id;
	}


	public int getIdHistory() {
		return idHistory;
	}


	public void setIdHistory(int idHistory) {
		this.idHistory = idHistory;
	}


	public int getStudent_id() {
		return Student_id;
	}


	public void setStudent_id(int student_id) {
		Student_id = student_id;
	}


	public int getSlot_id() {
		return Slot_id;
	}


	public void setSlot_id(int slot_id) {
		Slot_id = slot_id;
	}

}
