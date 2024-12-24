package com.personalproject.studentinformationportal.service;

import java.util.List;
import java.util.Optional;

import com.personalproject.studentinformationportal.db.repository.StudentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalproject.studentinformationportal.model.StudentInfo;

@Service
public class StudentInfoService {
	@Autowired
	private StudentInfoRepository studentInfoRepository;
	
	public void addStudent(StudentInfo studentInfo) {
		studentInfoRepository.save(studentInfo);
	}
	
	public List<StudentInfo> getStudents() {
		return studentInfoRepository.findAll();
	}

	public Optional<StudentInfo> getStudent(Integer id) {
		return studentInfoRepository.findById(id);
	}

	public void deleteStudent(Integer studentId) {
		studentInfoRepository.deleteById(studentId);
	}
}
