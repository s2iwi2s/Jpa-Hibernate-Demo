package com.jpa.hibernate.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.repository.CourseRepository;
import com.jpa.hibernate.demo.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateDemoApplication implements CommandLineRunner{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Course course = repository.findById(1001L);
		logger.info("findById {}", course);
		//repository.deleteById(1001L);
		
		course.setName("JPA in 1000 Steps");
//		logger.info("findById {}", repository.findById(1002L));
		
		course = repository.save(new Course("Microservices in 100 Step"));
		logger.info("save {}", repository.save(course));

		course.setName("Microservices in 200 Step");
		logger.info("save {}", repository.save(course));
		*/
		
		//courseRepository.addReviewsForCourseTest();
		//studentRepository.saveStudentWithPassport();
		//courseRepository.playWithEntityManager();
		
		//studentRepository.createStudentAndCourses();
	} 

}
