/**
 * 
 */
package com.people.equifax.college.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.people.equifax.college.dto.CourseDTO;
import com.people.equifax.college.dto.CourseDTOList;
import com.people.equifax.college.exception.GenericException;
import com.people.equifax.college.model.Course;
import com.people.equifax.college.repository.CourseRepository;

/**
 * @author Rodolfo.Quiroz rquiroz1988@gmail.com version 1.0
 */

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCoursesPaginated(Integer pageNo, Integer pageSize, String sortBy)
			throws GenericException {
		Pageable paging  = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Course> pagedResultCourse = courseRepository.findAll(paging);
		
		if(pagedResultCourse.hasContent()) {
			return pagedResultCourse.getContent();
		} else {
			return new ArrayList<Course>();
		}
	}

	public CourseDTOList getAllCourse(){
		CourseDTOList courses = new CourseDTOList();
		CourseDTO courseDTO;
		Iterable<Course> coursesIterable;

		coursesIterable = courseRepository.findAll();

		for (Course course : coursesIterable) {
			courseDTO = getCourseData(course);
			courses.getCourseDTO().add(courseDTO);

		}
		return courses;
	}

	public CourseDTO getCourse(Long courseId) throws GenericException {
		CourseDTO courseDtoLocal = new CourseDTO();
		Optional<Course> optionalCourse;
		Course courseLocal = null;

		optionalCourse = courseRepository.findById(courseId);
		if (!optionalCourse.isPresent()) {
			throw new GenericException("Client Errors", HttpStatus.NOT_FOUND);
		}

		courseLocal = optionalCourse.get();
		courseDtoLocal = getCourseData(courseLocal);
		return courseDtoLocal;
	}

	public CourseDTO addNewCourse(CourseDTO courseDto) throws GenericException {
		if (isNull(courseDto) || isNull(courseDto.getCode()) || isNull(courseDto.getName()) ||courseDto.getCode().isEmpty() || courseDto.getName().isEmpty()){
			throw new GenericException("Client Errors", HttpStatus.BAD_REQUEST);
		}
		
		Course courseLocal = setCourseAndCourseDto(courseDto);
		courseRepository.save(courseLocal);
		return courseDto;

	}

	/**
	 * @param courseDto
	 * @return
	 */
	private Course setCourseAndCourseDto(CourseDTO courseDto) {
		CourseDTO courseDtoLocal = new CourseDTO();
		Course courseLocal = new Course();

			courseLocal.setId(courseDto.getId());
			courseDtoLocal.setId(courseDto.getId());

			courseLocal.setName(courseDto.getName());
			courseDtoLocal.setName(courseDto.getName());

			courseDtoLocal.setCode(courseDto.getCode());
			courseLocal.setCode(courseDto.getCode());
		return courseLocal;
	}

	public CourseDTO updateCourse(Long courseId, CourseDTO courseDto) {
		Optional<Course> optionalCourse = null;
		Course course;
		Course courseLocal;
		CourseDTO courseDtoLocal = null;
		try {
			optionalCourse = courseRepository.findById(courseId);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (optionalCourse.isPresent()) {
			courseLocal = optionalCourse.get();
			course = getCourse(courseLocal, courseDto);
			courseRepository.saveAndFlush(course);
			courseDtoLocal = getCourseData(course);
		}
		return courseDtoLocal;
	}

	private Course getCourse(Course course, CourseDTO courseDto) {
		Course courseLocal = new Course();
		courseLocal.setId(courseDto.getId());
		courseLocal.setName(courseDto.getName());
		courseLocal.setCode(courseDto.getCode());
		return courseLocal;
	}

	private CourseDTO getCourseData(final Course course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(course.getId());
		courseDTO.setName(course.getName());
		courseDTO.setCode(course.getCode());
		return courseDTO;
	}
	
	private static boolean isNull(Object object) {
        return null == object;
    }
	
	private static boolean isEmpty(String text) {
        return text.isEmpty();
    }

}
