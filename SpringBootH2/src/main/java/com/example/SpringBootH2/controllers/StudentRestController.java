package com.example.SpringBootH2.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootH2.Repositories.StudentDao;
import com.example.SpringBootH2.entities.Student;

@RestController
public class StudentRestController {
	@Autowired
	private StudentDao autoDao;
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		return (List<Student>) autoDao.findAll();
	}
	@GetMapping("/getStudent/{id}")
	public Student getStudentByid(@PathVariable("id") int theid){
		return autoDao.findById(theid).orElse(null);
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@RequestBody Student thestudent) {
		System.out.println(autoDao.save(thestudent));
		autoDao.save(thestudent);
		return "student saved succesfully";
	}
	
	@GetMapping("/getStudents/{major}")
	public List<Student> findByDept(@PathVariable("major") String themajor){
		return autoDao.findByMajor(themajor);
	}
	
	@PutMapping("/updateName/{id}")
	public String updateName(@PathVariable("id") int theid,
			@RequestParam(name="nameofperson", defaultValue="Pranav") String thename) {
		
		// the RequestParam called nameofperson(which is a key) is sent during put request using postman
		//eg: in put request param if nameofperson = "vishal" then thename="vishal" 
		//if no name is specified in request param then default is "Pranav"
		autoDao.updateStudentName(thename,theid);
		
		return "name updated succesfully";
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@RequestBody Student thestudent) {
		
		//finally call save method and pass student to be updated to it
		//the save method will create new student if not there or will update if he is already there
		// save method will get if the id in requestbody is already in db or not. if there then updates, else creates a new student
		autoDao.save(thestudent);
		
		return "student updated sucessfully";
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deletestudent(@PathVariable("id") int theid) {
		//first retrieve student with the given id and then delete that student
		
		Student StudentTobeDeleted=autoDao.findById(theid).orElse(null);
		autoDao.delete(StudentTobeDeleted);
		return "student with id: "+theid+" deleted successfully ";
	}
	
	@DeleteMapping("/deleteAllStudents")
	public String deleteAll() {
		autoDao.deleteAll();
		return "all students deleted sucessfully";
	}
	
	
	
	
	
}
