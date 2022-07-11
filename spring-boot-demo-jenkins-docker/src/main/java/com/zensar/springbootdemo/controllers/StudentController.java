package com.zensar.springbootdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.springbootdemo.dto.StudentDto;
import com.zensar.springbootdemo.service.StudentService;

@RestController
@RequestMapping(value = "/student-api", produces = { MediaType.APPLICATION_JSON_VALUE,
		MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE })
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/students/{studentId}")
	public ResponseEntity<StudentDto> getStudent(@PathVariable("studentId") int studentId) {
		return new ResponseEntity<StudentDto>(studentService.getStudent(studentId), HttpStatus.OK);

		// return studentService.getStudent(studentId);

	}

// http://localhost:6060/students
// @RequestMapping(value = { "/students", "/listOfStudents" },method =
// RequestMethod.GET)
	@GetMapping(value = { "/students", "/listOfStudents" })
	public ResponseEntity<List<StudentDto>> getAllStudents(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

		return new ResponseEntity<List<StudentDto>>(studentService.getAllStudents(pageNumber, pageSize), HttpStatus.OK);

	}

	@PostMapping(value = "/students")
	public ResponseEntity<StudentDto> insertStudent(@RequestBody StudentDto studentDto) {

		return new ResponseEntity<StudentDto>(studentService.insertStudent(studentDto), HttpStatus.CREATED);
	}

//@RequestMapping(value ="/students/{studentId}",method =RequestMethod.PUT)
	@PutMapping(value = "/students/{studentId}")
	public ResponseEntity<String> updateStudent(@PathVariable("studentId") int studentId,
			@RequestBody StudentDto studentDto) {
		studentService.updateStudent(studentId, studentDto);

		return new ResponseEntity<String>("Student updated Succesfully ", HttpStatus.OK);

	}

// http://localhost:6060/students -> Delete
// @RequestMapping(value = "/students/{studentId}",method =
// RequestMethod.DELETE)
	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable("studentId") int studentId) {

		studentService.deleteStudent(studentId);
		return new ResponseEntity<String>("Student deleted successfully ", HttpStatus.OK);

	}

	// http://localhost:6060/student-api/students/Ram
	@GetMapping("/students/studentname{studentName}")
	public List<StudentDto> getByStudentName(@PathVariable("studentName") String studentName) {
		return studentService.getByStudentName(studentName);

	}

	@GetMapping("/students/{studentName}/{studentAge}")
	public List<StudentDto> findByStudentNameandStudentAge(@PathVariable("studentName") String studentName,
			@PathVariable("studentAge") int age) {
		return studentService.findByStudentNameAndStudentAge(studentName, age);
	}

}