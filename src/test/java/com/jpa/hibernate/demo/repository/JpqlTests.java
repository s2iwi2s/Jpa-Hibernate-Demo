package com.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.hibernate.demo.JpaHibernateDemoApplication;
import com.jpa.hibernate.demo.entity.Course;

@SpringBootTest(classes = JpaHibernateDemoApplication.class)
class JpqlTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	@Test
	void jpqlSelectBasic() {
		List jpqlSelectBasicList = em.createQuery("select c from Course c").getResultList();
		logger.info("jpqlSelectBasicList => {}", jpqlSelectBasicList);
	}

	@Test
	void jpqlSelectBasic2() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c", Course.class);
		List<Course> jpqlSelectBasic2List = typedQuery.getResultList();
		logger.info("jpqlSelectBasic2List => {}", jpqlSelectBasic2List);
	}


	@Test
	void jpqlSelectBasic3() {
		TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where name like '%100 Steps'", Course.class);
		List<Course> jpqlSelectBasic3List = typedQuery.getResultList();
		logger.info("jpqlSelectBasic3List => {}", jpqlSelectBasic3List);
	}

	@Test
	void jpqlNamedQuery() {
		TypedQuery<Course> namedQuery = em.createNamedQuery("get_all_courses", Course.class);
		List<Course> namedQueryResultList = namedQuery.getResultList();
		logger.info("namedQueryResultList => {}", namedQueryResultList);
	}

	@Test
	void jpqlNamedQuery100() {
		TypedQuery<Course> namedQuery100 = em.createNamedQuery("get_100_courses", Course.class);
		List<Course> namedQuery100ResultList = namedQuery100.getResultList();
		logger.info("namedQuery100ResultList => {}", namedQuery100ResultList);
	}
}
