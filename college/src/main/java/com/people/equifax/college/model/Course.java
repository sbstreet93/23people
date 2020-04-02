package com.people.equifax.college.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rodolfo.Quiroz 
 * rquiroz1988@gmail.com 
 * version 1.0
 */

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String nameCourse;

	@Column(name = "code", length = 4)
	private String codeCourse;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return nameCourse;
	}

	public void setName(String name) {
		this.nameCourse = name;
	}

	public String getCode() {
		return codeCourse;
	}

	public void setCode(String code) {
		this.codeCourse = code;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + nameCourse + ", code=" + codeCourse + "]";
	}

}
