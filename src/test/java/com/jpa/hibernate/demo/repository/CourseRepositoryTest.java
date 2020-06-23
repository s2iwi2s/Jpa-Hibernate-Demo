package com.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.jpa.hibernate.demo.JpaHibernateDemoApplication;
import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Review;
import com.jpa.hibernate.demo.repository.CourseRepository;

@SpringBootTest(classes = JpaHibernateDemoApplication.class)
class CourseRepositoryTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	@Autowired
	private EntityManager em;

	@Test
	void testFindById() {
		// logger.info("testFindById...");
		Course course = repository.findById(1001L);
		assertNotNull(course);
		assertEquals("JPA in 100 Steps", course.getName());
	}

	@Test
	@DirtiesContext
	void testDeleteById() {
		// logger.info("testDeleteById...");
		repository.deleteById(1002L);
		assertNull(repository.findById(1002L));
	}

	@Test
	@DirtiesContext
	void testSave() {
		// logger.info("testSave...");
		Course course = repository.findById(1001L);
		assertEquals("JPA in 100 Steps", course.getName());

		String updatedName = "JPA in 100 Steps - updated";
		course.setName(updatedName);
		repository.save(course);

		Course updatedCourse = repository.findById(1001L);
		assertEquals(updatedName, updatedCourse.getName());
	}

	@Test
	@DirtiesContext
	void testAddReviewsForCourse() {
		// logger.info("testSave...");
		Course course = new Course("Angular in 16 Steps");
		repository.save(course);
		assertNotNull(course.getId());

		List<Review> reviews = new ArrayList<Review>();
		reviews.add(new Review("3", "Good"));
		reviews.add(new Review("4", "Great job"));
		course = repository.addReviewsForCourse(course, reviews);
		logger.info("testAddReviewsForCourse 1 NEW Reviews => {}", course.getReviews());

		Course courseNew = repository.findById(course.getId());
		logger.info("testAddReviewsForCourse 2 NEW Reviews => {}", courseNew);
		assertEquals(course.getReviews().size(), course.getReviews().size(), "Review size should be equal");
	}

	@Test
	@Transactional
	// @DirtiesContext
	void testRetrieveReviewsForCourse() {
		Course course = em.find(Course.class, 1003L);
		logger.info("testRetrieveReviewsForCourse course =>{}, students -> {}, student.size => {}", course,
				course.getStudents(), course.getStudents().size());
//		List<Review> reviews = course.getReviews();
//		assertNotEquals(0, reviews.size(), "Size should be greater than 0");
//		logger.info("testRetrieveReviewsForCourse Reviews => {}", reviews);
//		for (Review review : reviews) {
//			logger.info("testRetrieveReviewsForCourse review => {}, student -> {}", review, review.getStudent());
//		}

	}

	@Test
	// @Transactional
	// @DirtiesContext
	void testRetrieveReviewsForCourseEm() {
		Review review = em.find(Review.class, 4001L);
		logger.info("testRetrieveReviewsForCourseEm course => {}", review.getCourse());
//		assertNotNull(review, "Should retrieve review 4001");
//		assertEquals("Great!", review.getDescription());
//		logger.info("testRetrieveReviewsForCourseEm review => {}", review);
//		Course course = review.getCourse();
//		logger.info("testRetrieveReviewsForCourseEm course => {}", course);
	}
}
