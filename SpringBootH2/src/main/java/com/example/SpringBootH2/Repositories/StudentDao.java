package com.example.SpringBootH2.Repositories;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.SpringBootH2.entities.Student;

public interface StudentDao extends CrudRepository<Student, Integer> {
		
		//naming convention is important 
		public List<Student> findByMajor(String major);
		
		public List<Student> findByName(String name);
		
		//use JPQL for customized querying
		
		@Transactional
		@Modifying(clearAutomatically=true)
		@Query("update Student s set s.name=?1 where s.id=?2")
		public void updateStudentName(String name,int id);
}
