package com.jpa.hibernate.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NamedQueries(value = { @NamedQuery(name = "get_all_courses", query = "select c from Course c"),
		@NamedQuery(name = "get_100_courses", query = "select c from Course c where name like '%100 Steps'") })
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(nullable = false)
	private String name;

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@OneToMany(mappedBy = "course") // OneToMany defaults to fetch=LAZY
	private List<Review> reviews = new ArrayList<Review>();

	@ManyToMany(mappedBy = "courses")
	private List<Student> students = new ArrayList<Student>();

	public Course(String name) {
		this.name = name;
	}

	public void addReview(Review review) {
		reviews.add(review);
		review.setCourse(this);
	}

	public void removeReview(Review review) {
		reviews.remove(review);
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void removeStudent(Student student) {
		students.remove(student);
	}

	@Override
	public String toString() {
		return String.format("Course [id=%s, lastUpdatedDate=%s, createdDate=%s, name=%s]", id, lastUpdatedDate,
				createdDate, name);
	}

}
