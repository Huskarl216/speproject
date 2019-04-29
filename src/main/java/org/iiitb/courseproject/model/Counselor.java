package org.iiitb.courseproject.model;

public class Counselor {
	private int idCounselor;
	private String Counseloremail;
	private String name;
	private String password;
	
	public Counselor() {
		
	}

	public int getIdCounselor() {
		return idCounselor;
	}

	public void setIdCounselor(int idCounselor) {
		this.idCounselor = idCounselor;
	}

	public String getCounseloremail() {
		return Counseloremail;
	}

	public void setCounseloremail(String counseloremail) {
		Counseloremail = counseloremail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}