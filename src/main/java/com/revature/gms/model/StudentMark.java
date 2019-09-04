package com.revature.gms.model;

public class StudentMark {

	private Integer id;
	
	@Override
	public String toString() {
		return "StudentMark [id=" + id + ", studentDetail=" + studentDetail + ", subjectName=" + subjectName + ", mark="
				+ mark + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private StudentDetails studentDetail;
	
	private String subjectName;
	
	private Integer mark;

	public StudentDetails getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetails studentDetail) {
		this.studentDetail = studentDetail;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}
	
	
}
