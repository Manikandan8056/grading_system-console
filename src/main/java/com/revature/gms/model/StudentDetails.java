package com.revature.gms.model;

public class StudentDetails {
	
	private String studName;
	private int regNo;
	private int sub1;
	private int sub2;
	private int sub3;
	private int sub4;
	private int sub5;
	private int subject;

	private int total;
	private float avg;
	
	private String grade;
	
		
	@Override
	public String toString() {
		return "StudentDetails [STUDENT NAME=" + studName + "  , REG-NO=" + regNo + "  , SUB1=" + sub1 + "  , SUB2=" + sub2
				+ "  , SUB3=" + sub3 + "  , SUB4=" + sub4 + "  , SUB5=" + sub5 + "  , PERCENTAGE=" + avg
				+ "%  , GRADE= " + grade + "]";
	}

	public String toString1() {
		return "StudentDetails [studName =" + studName + ",   regNo =" + regNo + ",   Percentage =" + avg + ",   grade =" + grade + "]";
	}
	
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public int getRegNo() {
		return regNo;
	}
	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}
	public int getSub1() {
		return sub1;
	}
	public void setSub1(int sub1) {
		this.sub1 = sub1;
	}
	public int getSub2() {
		return sub2;
	}
	public void setSub2(int sub2) {
		this.sub2 = sub2;
	}
	public int getSub3() {
		return sub3;
	}
	public void setSub3(int sub3) {
		this.sub3 = sub3;
	}
	public int getSub4() {
		return sub4;
	}
	public void setSub4(int sub4) {
		this.sub4 = sub4;
	}
	public int getSub5() {
		return sub5;
	}
	public void setSub5(int sub5) {
		this.sub5 = sub5;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}
	

}
