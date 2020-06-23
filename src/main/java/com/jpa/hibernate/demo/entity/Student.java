package com.jpa.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name="UK_STUDENT", columnNames = {"ID", "PASSPORT_ID"}))
public class Student {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_STUDENT_PASSPORT_ID"))
	private Passport passport;

	@Column(nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "STUDENT_COURSES", joinColumns = {
			@JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_STUDENT_COURSES_STUDENT_ID")) }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_STUDENT_COURSES_COURSE_ID")) })
	private List<Course> courses = new ArrayList<Course>();
	
	@OneToOne(mappedBy = "student")
	private Review review;
	
	public Student(String name) {
		this.name = name;
	}

	public void addCourses(Course course) {
		courses.add(course);
	}

	public void removeCourses(Course course) {
		courses.remove(course);
	}

	@Override
	public String toString() {
		return String.format("Student [id=%s, name=%s]", id, name);
	}

}
