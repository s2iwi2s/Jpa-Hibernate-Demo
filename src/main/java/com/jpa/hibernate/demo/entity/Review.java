package com.jpa.hibernate.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "UK_REVIEW", columnNames = {"COURSE_ID", "STUDENT_ID"}))
public class Review {
	@Id
	@GeneratedValue
	private Long id;

	private String rating;
	private String description;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_REVIEW_COURSE_ID"))
	private Course course;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_REVIEW_STUDENT_ID"))
	private Student student;
	
	public Review(String rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Review [id=%s, rating=%s, description=%s]", id, rating, description);
	}
}
