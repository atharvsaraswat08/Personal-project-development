package com.personalproject.studentinformationportal.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalproject.studentinformationportal.model.StudentInfo;
import com.personalproject.studentinformationportal.service.StudentInfoService;

@RestController
@RequestMapping("/")
public class StudentInfoController {
	private StudentInfo studentInfo;
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	
	@PostMapping("/addStudent")
    public StudentInfo createStudent(@RequestBody StudentInfo studentInfo) {
	studentInfoService.addStudent(studentInfo);
	return this.studentInfo = studentInfo;
	}
	
	@GetMapping("/getStudents")
    public List<StudentInfo> getStudents() {
		return studentInfoService.getStudents();
	}

	@GetMapping("/Student/{id}")
    public StudentInfo getStudent(@PathVariable int id) {
	return this.studentInfo;
	}
	
}
