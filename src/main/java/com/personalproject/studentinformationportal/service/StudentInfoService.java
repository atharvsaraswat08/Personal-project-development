package com.personalproject.studentinformationportal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.personalproject.studentinformationportal.model.StudentInfo;

@Service
public class StudentInfoService {

	private List<StudentInfo> studentInfoList;
	
	public void addStudent(StudentInfo studentInfo) {
		if(studentInfoList == null) {
			studentInfoList = new ArrayList<>();
		}
		
		studentInfoList.add(studentInfo);
	}
	
	public List<StudentInfo> getStudents() {
		return studentInfoList;
	}

}
