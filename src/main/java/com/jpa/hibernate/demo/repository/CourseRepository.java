package com.jpa.hibernate.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Review;

@Repository
@Transactional
public class CourseRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		em.flush();
		course = findById(course.getId()); 
		return course;
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		Course course = new Course("Microservices in 100 Step");
		course = save(course);
		logger.info("save => {}", course);
		course.setName("Microservices in 200 Step - Updated");
		
		Course course2 = findById(1001L);
		logger.info("findById {}", course2);
		course2.setName("JPA in 1000 Steps - Updated");
	}

	
	public Course addReviewsForCourse(Course course, List<Review> reviews) {		
		for (Review review : reviews) {
			course.addReview(review);//review.setCourse(course);
			em.persist(review);
			logger.info("a review => {}", review);
		}
		return course;
	}
	public Course addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);
		return addReviewsForCourse(course, reviews);
	}
	
	public void addReviewsForCourseTest() {
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review("3", "Good"));
		reviews.add(new Review("4", "Great job"));
		Long courseId = 1002L;
		Course course = addReviewsForCourse(courseId, reviews);
		logger.info("NEW Reviews => {}", course.getReviews());
	}
}
