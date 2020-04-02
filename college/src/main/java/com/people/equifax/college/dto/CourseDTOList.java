/**
 * 
 */
package com.people.equifax.college.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodolfo.Quiroz 
 * rquiroz1988@gmail.com 
 * version 1.0
 */

public class CourseDTOList implements Serializable {
	private static final long serialVersionUID = -7884637212401733876L;
	
	private List<CourseDTO> coursesDTO;
	
	public CourseDTOList() {
		this.coursesDTO = new ArrayList<>();
	}

	public List<CourseDTO> getCourseDTO() {
		return coursesDTO;
	}

	public void setCourseDTO(List<CourseDTO> courseDTO) {
		this.coursesDTO = courseDTO;
	}
}
