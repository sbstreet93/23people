/**
 * 
 */
package com.people.equifax.college.dto;

import java.io.Serializable;

import com.people.equifax.college.model.Course;

/**
 * @author Rodolfo.Quiroz 
 * rquiroz1988@gmail.com 
 * version 1.0
 */

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = -9207923003929471131L;
	
	private String rut;
	private String name;
	private String lastName;
	private int age;
	private Course course;

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "StudentDTO [rut=" + rut + ", name=" + name + ", lastName=" + lastName + ", age=" + age + ", course="
				+ course + "]";
	}

}
