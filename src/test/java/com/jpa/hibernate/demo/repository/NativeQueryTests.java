package com.jpa.hibernate.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.hibernate.demo.JpaHibernateDemoApplication;
import com.jpa.hibernate.demo.entity.Course;

@SpringBootTest(classes = JpaHibernateDemoApplication.class)
class NativeQueryTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	
	/*
	@Test
	void nativeQueryBasic() {
		List nativeQueryBasicList = em.createNativeQuery("select * from Course").getResultList();
		logger.info("nativeQueryBasicList => {}", nativeQueryBasicList);
	}
	@Test
	void nativeQueryBasic2() {
		Query createNativeQuery = em.createNativeQuery("select * from Course", Course.class);
		List nativeQueryBasic2List = createNativeQuery.getResultList();
		logger.info("nativeQueryBasic2List => {}", nativeQueryBasic2List);
	}
	
	@Test
	void nativeQueryBasicParam() {
		Query createNativeQuery = em.createNativeQuery("select * from Course where id = ?", Course.class);
		createNativeQuery.setParameter(1, 1001L);
		List nativeQueryBasicParamList = createNativeQuery.getResultList();
		logger.info("nativeQueryBasicParamList => {}", nativeQueryBasicParamList);
	}
	@Test
	void nativeQueryBasicNamedParam() {
		Query createNativeQuery = em.createNativeQuery("select * from Course where id = :id", Course.class);
		createNativeQuery.setParameter("id", 1001L);
		List nativeQueryBasicNamedParamList = createNativeQuery.getResultList();
		logger.info("nativeQueryBasicNamedParamList => {}", nativeQueryBasicNamedParamList);
	}
	*/

	@Test
	@Transactional
	void nativeQueryBasicNamedParamUpdate() {
		Query createNativeQuery = em.createNativeQuery("Update Course set name = name || :name where id = :id", Course.class);
		createNativeQuery.setParameter("id", 1001L);
		createNativeQuery.setParameter("name", " - TheUpdatedName");
		createNativeQuery.executeUpdate();
		
		createNativeQuery = em.createNativeQuery("select * from Course", Course.class);
		List nativeQueryBasic2List = createNativeQuery.getResultList();
		logger.info("nativeQueryBasic2List => {}", nativeQueryBasic2List);
	}
}
