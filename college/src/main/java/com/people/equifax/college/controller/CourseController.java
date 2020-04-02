package com.people.equifax.college.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.people.equifax.college.dto.CourseDTO;
import com.people.equifax.college.exception.GenericException;
import com.people.equifax.college.model.Course;
import com.people.equifax.college.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("GET/ok")
	ResponseEntity<String> hello() {
		return new ResponseEntity<>(HttpStatus.OK + " Respuesta estándar para peticiones correctas.", HttpStatus.OK);
	}

	@GetMapping(value = "GET/courses")
	public ResponseEntity<List<Course>> getCoursesPaginated(@RequestParam(defaultValue = "0") Integer pageNo,
		@RequestParam(defaultValue = "3") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) throws GenericException {
		List<Course> courses = new ArrayList<>();
		ResponseEntity<List<Course>> response;
		try {
			courses = courseService.getAllCoursesPaginated(pageNo, pageSize, sortBy);
			response = new ResponseEntity<>(courses, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<List<Course>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response; 

	}
	
	@GetMapping("GET/courses/all")
	public ResponseEntity<Object> getCourses() {
		ResponseEntity<Object> response;
		try {
			response = new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
		} catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
	}

	@GetMapping("GET/courses/{id}")
	public @ResponseBody ResponseEntity<Object> getCourseById(@PathVariable long id) throws GenericException {
		ResponseEntity<Object> response;
		try {
			response = new ResponseEntity<>(courseService.getCourse(id), HttpStatus.OK);
		} catch (GenericException error) {
			response = new ResponseEntity<>(error.getMessage(), error.getHttpStatus());
		} catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
	}

	@PostMapping("POST/courses")
	public @ResponseBody ResponseEntity<Object> addCourse(@RequestBody CourseDTO courseDto) throws GenericException {
		ResponseEntity<Object> response;
		try {
			response = new ResponseEntity<>(courseService.addNewCourse(courseDto), HttpStatus.CREATED);
		}  catch (GenericException error) {
			response = new ResponseEntity<>(error.getMessage(), error.getHttpStatus());
		} catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
	}

	@PutMapping("PUT/courses/{courseId}")
	public @ResponseBody ResponseEntity<Object> updateCourse(@PathVariable("courseId") long courseId,
			@RequestBody CourseDTO courseDto) throws GenericException {
		ResponseEntity<Object> response;
		try {
			response = new ResponseEntity<>(courseService.updateCourse(courseId, courseDto), HttpStatus.OK);			
		} catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
	}
}
