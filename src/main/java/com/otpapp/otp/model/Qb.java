package com.otpapp.otp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="qbs")
public class Qb {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 100, nullable = false)
	private String year;
	
	@Column(length = 2000, nullable = false)
	private String question;
	
	@Column(length = 2000, nullable = false)
	private String a; 
	
	@Column(length = 2000, nullable = false)
	private String b;
	
	@Column(length = 2000, nullable = false)
	private String c;
	
	@Column(length = 2000, nullable = false)
	private String d;
	
	
	@Column(length = 5, nullable = false)
	private String correct;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getA() {
		return a;
	}


	public void setA(String a) {
		this.a = a;
	}


	public String getB() {
		return b;
	}


	public void setB(String b) {
		this.b = b;
	}


	public String getC() {
		return c;
	}


	public void setC(String c) {
		this.c = c;
	}


	public String getD() {
		return d;
	}


	public void setD(String d) {
		this.d = d;
	}


	public String getCorrect() {
		return correct;
	}


	public void setCorrect(String correct) {
		this.correct = correct;
	}
	
	
}
