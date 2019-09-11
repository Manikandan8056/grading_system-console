package com.revature.gradingsystem.model;

public class StudentMark {

	private Integer id;
	
	private StudentDetail studentDetail;

	private String subjectName;
	
	private Subject subject;
	
	private Integer mark;

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

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

	public StudentDetail getStudentDetail() {
		return studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
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
