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

public class StudentDTOList implements Serializable {
	private static final long serialVersionUID = 5381810967999536138L;

	private List<StudentDTO> studentsDTO;

	public StudentDTOList() {
		this.studentsDTO = new ArrayList<>();
	}

	public List<StudentDTO> getStudentsDTO() {
		return studentsDTO;
	}

	public void setStudentsDTO(List<StudentDTO> studentsDTO) {
		this.studentsDTO = studentsDTO;
	}

}
