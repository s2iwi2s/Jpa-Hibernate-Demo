package com.jpa.hibernate.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.hibernate.demo.JpaHibernateDemoApplication;
import com.jpa.hibernate.demo.entity.Passport;
import com.jpa.hibernate.demo.entity.Student;
import com.jpa.hibernate.demo.repository.PassportRepository;
import com.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootTest(classes = JpaHibernateDemoApplication.class)
class StudentRepositoryTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository studentRepository; 

	@Autowired
	private PassportRepository passportRepository; 
	
	
	@Test
	@Transactional
	void testFindById() {
		//logger.info("testFindById...");
		Student student = studentRepository.findById(2001L);
		assertNotNull(student);
		assertEquals("Winston Pidor", student.getName());
		
		logger.info("testFindById Student -> {}", student);
		logger.info("testFindById Passport -> {}", student.getPassport());
	}

	@Test
	@Transactional
	void testFindByIdPassort() {
		//logger.info("testFindById...");
		Passport passport = passportRepository.findById(3001L);
		assertEquals("W123121", passport.getNumber());
		assertNotNull(passport.getStudent());
		assertEquals("Winston Pidor", passport.getStudent().getName());

		logger.info("testFindByIdPassort Passport -> {}", passport);
		logger.info("testFindByIdPassort Student -> {}", passport.getStudent());
	}
	@Test
	@Transactional
	void testRetrieveStudentAndCourses() {
		Long studentId = 2001L;
		Student student = studentRepository.findById(studentId);
		assertNotNull(student, "Error retrieving student id " + studentId);
		assertEquals(studentId.longValue(), student.getId().longValue(), "Invalid student id " + studentId);
		logger.info("testRetrieveStudentAndCourses Student -> {}", student);
		logger.info("testRetrieveStudentAndCourses Student Courses -> {}", student.getCourses());
	}
}
