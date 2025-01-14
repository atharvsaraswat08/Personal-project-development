package com.personalproject.studentinformationportal.service;

import java.util.List;
import java.util.Optional;

import com.personalproject.studentinformationportal.db.repository.StudentInfoRepository;
import com.personalproject.studentinformationportal.model.DeleteResponse;
import com.personalproject.studentinformationportal.model.UpdateResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.personalproject.studentinformationportal.model.StudentInfo;
import org.springframework.util.ObjectUtils;

@Service
public class StudentInfoService {
	@Autowired
	private StudentInfoRepository studentInfoRepository;

	@Value("${x-admin-key}")
	private String adminKey;

	public StudentInfo addStudent(StudentInfo studentInfo) {
		return studentInfoRepository.save(studentInfo);
	}
	
	public List<StudentInfo> getAllStudents() {
		return studentInfoRepository.findAll();
	}

	public Optional<StudentInfo> getStudentById(Integer id) {
		return studentInfoRepository.findById(id);
	}

	public DeleteResponse deleteStudent(Integer studentId, String adminKeyInput) {
		DeleteResponse deleteResponse = new DeleteResponse();
		Object response = studentInfoRepository.findById(studentId);
		if (adminKeyInput == null || !adminKeyInput.equalsIgnoreCase(adminKey)) {
			deleteResponse.setFailedMessage("Invalid admin key or not authorized to add student");
		} else {
			if (((Optional) response).isEmpty() ) {
				return new DeleteResponse("", "Request not succeeded as student with "+ studentId+" does not exist") ;
			}
			studentInfoRepository.deleteById(studentId);
			deleteResponse.setSuccessMessage("Successfully deleted student with "+ studentId+" from database"); ;
		}
		return deleteResponse;
	}

	public List<StudentInfo> searchStudentByName(String name) {
		return studentInfoRepository.findByName(name);
	}

	public UpdateResponse updateStudent(int id, StudentInfo updatedStudentInfo, String adminKeyInput) {
		UpdateResponse response = new UpdateResponse();

		if (adminKeyInput == null || !adminKeyInput.equalsIgnoreCase(adminKey)) {
			response.setFailedMessage("Invalid admin key or not authorized to add student");
		} else {
			Optional<StudentInfo> existingStudentOptional = studentInfoRepository.findById(id);
			if(existingStudentOptional.isPresent()) {
				if(!StringUtils.isEmpty(updatedStudentInfo.getName())) {
					existingStudentOptional.get().setName(updatedStudentInfo.getName());
				}
				if (!StringUtils.isEmpty(updatedStudentInfo.getFatherName())) {
					existingStudentOptional.get().setFatherName(updatedStudentInfo.getFatherName());
				}
				if (!ObjectUtils.isEmpty(updatedStudentInfo.getAge())) {
					existingStudentOptional.get().setAge(updatedStudentInfo.getAge());
				}
				if (!StringUtils.isEmpty(updatedStudentInfo.getMotherName())) {
					existingStudentOptional.get().setMotherName(updatedStudentInfo.getMotherName());
				}
				if(!StringUtils.isEmpty(updatedStudentInfo.getAddress())) {
					existingStudentOptional.get().setAddress(updatedStudentInfo.getAddress());
				}
			};
			studentInfoRepository.save(existingStudentOptional.get());
			response.setSuccessMessage("Student updated successfully");
			response.setStudentInfo(studentInfoRepository.findById(id).get());
		}
		return response;
	}
}
