package org.iiitb.courseproject.model;

public class Student_Slot_Request {
	private int idRequest;
	private int Student_id;
	private int Slot_id;
	public int getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
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