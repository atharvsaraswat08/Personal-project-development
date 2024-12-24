package com.personalproject.studentinformationportal.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.personalproject.studentinformationportal.model.StudentInfo;
import com.personalproject.studentinformationportal.service.StudentInfoService;

@RestController
@RequestMapping("/")
public class StudentInfoController {

	@Autowired
	private StudentInfoService studentInfoService;
	
	
	@PostMapping("/student/add")
    public StudentInfo createStudent(@RequestBody StudentInfo studentInfo) {
	studentInfoService.addStudent(studentInfo);
	return studentInfo;
	}
	
	@GetMapping("/student/getall")
    public List<StudentInfo> getStudents() {
		return studentInfoService.getStudents();
	}

	@GetMapping("/student/{id}")
    public Optional<StudentInfo> getStudent(@PathVariable int id) {
	return studentInfoService.getStudent(id);
	}

	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentInfoService.deleteStudent(id);
	}
}
