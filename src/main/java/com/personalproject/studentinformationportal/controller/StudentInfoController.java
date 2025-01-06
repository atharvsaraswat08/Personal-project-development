package com.personalproject.studentinformationportal.controller;
import java.util.List;
import java.util.Optional;

import com.personalproject.studentinformationportal.model.AddStudentResponse;
import com.personalproject.studentinformationportal.model.DeleteResponse;
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
    public AddStudentResponse addStudent(@RequestBody StudentInfo studentInfo) {
		AddStudentResponse addStudentResponse = new AddStudentResponse();
		List<StudentInfo> students = studentInfoService.searchStudentByName(studentInfo.getName());
		if(students.isEmpty()) {
			StudentInfo response = studentInfoService.addStudent(studentInfo);
			if(response != null) {
				addStudentResponse.setStudentInfo(studentInfo);
				addStudentResponse.setSuccessMessage("Student added successfully");
			} else {
				addStudentResponse.setFailedMessage("Student added failed");
			}
		} else {
			addStudentResponse.setFailedMessage("Student added failed because already exists");
		}
		return addStudentResponse;
	}

	@GetMapping("/student/all")
    public List<StudentInfo> getAllStudents() {
		return studentInfoService.getAllStudents();
	}

	@GetMapping("/student/{id}")
    public Optional<StudentInfo> getStudent(@PathVariable int id) {
	return studentInfoService.getStudentById(id);
	}

	@GetMapping("/student/search/{name}")
	public List<StudentInfo> searchStudentByName(@PathVariable String name) {
		return studentInfoService.searchStudentByName(name);
	}

	@DeleteMapping("/student/{id}")
	public DeleteResponse deleteStudent(@PathVariable int id)  {
		return studentInfoService.deleteStudent(id);
	}
}
