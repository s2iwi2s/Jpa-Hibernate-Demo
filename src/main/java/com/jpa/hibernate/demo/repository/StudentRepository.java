package com.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Passport;
import com.jpa.hibernate.demo.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		em.flush();
		student = findById(student.getId()); 
		return student;
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("J79345687P");
		em.persist(passport);
		
		Student student = new Student("Jeremy");
		student.setPassport(passport);
		em.persist(student);
		logger.info("save => {}", student);
		
		Student student2 = findById(student.getId());
		logger.info("findById {}", student2);
	}
	public void createStudentAndCourses() {
		Student student = new Student("Jack Ma");
		Passport passport = new Passport("J79345687M");
		Course course = new Course("Business in 10 Steps");

		em.persist(student);
		em.persist(passport);
		em.persist(course);
		
		student.setPassport(passport);
		student.addCourses(course);
		course.addStudent(student);
		
		em.persist(student);
	}
}
