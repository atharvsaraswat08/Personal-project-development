package com.personalproject.studentinformationportal.service;

import java.util.List;
import java.util.Optional;

import com.personalproject.studentinformationportal.db.repository.StudentInfoRepository;
import com.personalproject.studentinformationportal.model.DeleteResponse;
import com.personalproject.studentinformationportal.model.UpdateResponse;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalproject.studentinformationportal.model.StudentInfo;
import org.springframework.util.ObjectUtils;

@Service
public class StudentInfoService {
	@Autowired
	private StudentInfoRepository studentInfoRepository;
	
	public StudentInfo addStudent(StudentInfo studentInfo) {
		return studentInfoRepository.save(studentInfo);
	}
	
	public List<StudentInfo> getAllStudents() {
		return studentInfoRepository.findAll();
	}

	public Optional<StudentInfo> getStudentById(Integer id) {
		return studentInfoRepository.findById(id);
	}

	public DeleteResponse deleteStudent(Integer studentId) {
		Object response = studentInfoRepository.findById(studentId);
		if (((Optional) response).isEmpty() ) {
			return new DeleteResponse("", "Request not succeeded as student with "+ studentId+" does not exist") ;
		}
		studentInfoRepository.deleteById(studentId);
		return new DeleteResponse("Successfully deleted student with "+ studentId+" from database", "Student deleted successfully") ;

	}

	public List<StudentInfo> searchStudentByName(String name) {
		return studentInfoRepository.findByName(name);
	}

	public UpdateResponse updateStudent(int id, StudentInfo updatedStudentInfo) {
		UpdateResponse response = new UpdateResponse();
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

		return response;
	}
}
