package com.personalproject.studentinformationportal.controller;
import java.util.List;
import java.util.Optional;

import com.personalproject.studentinformationportal.model.AddStudentResponse;
import com.personalproject.studentinformationportal.model.DeleteResponse;
import com.personalproject.studentinformationportal.model.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.personalproject.studentinformationportal.model.StudentInfo;
import com.personalproject.studentinformationportal.service.StudentInfoService;

@RestController
@RequestMapping("/")
public class StudentInfoController {

	@Autowired
	private StudentInfoService studentInfoService;

	@Value("${x-admin-key}")
	private String adminKey;
	
	@PostMapping("/student/add")
    public AddStudentResponse addStudent(@RequestHeader(value="x-admin-key") String adminKeyInput, @RequestBody StudentInfo studentInfo) {
		AddStudentResponse addStudentResponse = new AddStudentResponse();
		if (adminKeyInput == null || !adminKeyInput.equalsIgnoreCase(adminKey)) {
			addStudentResponse.setFailedMessage("Invalid admin key or not authorized to add student");
		} else {
			StudentInfo response = studentInfoService.addStudent(studentInfo);
			if (response != null) {
				addStudentResponse.setStudentInfo(studentInfo);
				addStudentResponse.setSuccessMessage("Student added successfully");
			} else {
				addStudentResponse.setFailedMessage("Student added failed");
			}
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
	public DeleteResponse deleteStudent(@RequestHeader(value="x-admin-key") String adminKeyInput, @PathVariable int id)  {
		return studentInfoService.deleteStudent(id, adminKeyInput);
	}

	@PatchMapping("/student/{id}")
	public UpdateResponse updateStudent(@PathVariable int id, @RequestBody StudentInfo updatedStudentInfo, @RequestHeader(value="x-admin-key") String adminKeyInput)  {
		return studentInfoService.updateStudent(id, updatedStudentInfo, adminKeyInput);
	}

}
