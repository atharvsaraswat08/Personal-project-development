package com.personalproject.studentinformationportal.db.repository;

//import javax.transaction.Transactional;

import com.personalproject.studentinformationportal.model.StudentInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Transactional
@Repository
public interface StudentInfoRepository extends CrudRepository<StudentInfo, Integer> {
	public List<StudentInfo> findAll();
    public List<StudentInfo> findByName(String name);
}
