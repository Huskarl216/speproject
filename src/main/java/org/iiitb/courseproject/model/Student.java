package org.iiitb.courseproject.model;

public class Student{
	private int idStudent;
	private String Studentemail;
	private String name;
	private String password;
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public String getStudentemail() {
		return Studentemail;
	}
	public void setStudentemail(String studentemail) {
		Studentemail = studentemail;
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