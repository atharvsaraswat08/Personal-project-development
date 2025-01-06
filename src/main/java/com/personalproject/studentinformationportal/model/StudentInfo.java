package com.personalproject.studentinformationportal.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudentInfo {
	private String name; 
	private String fatherName;
	private int age;
	private String motherName;
	private String address;
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
