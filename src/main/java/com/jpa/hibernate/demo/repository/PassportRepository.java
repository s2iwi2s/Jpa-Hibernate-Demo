package com.jpa.hibernate.demo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jpa.hibernate.demo.entity.Passport;

@Repository
@Transactional
public class PassportRepository {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityManager em;

	public Passport findById(Long id) {
		return em.find(Passport.class, id);
	}

	public Passport save(Passport passport) {
		if (passport.getId() == null) {
			em.persist(passport);
		} else {
			em.merge(passport);
		}
		em.flush();
		passport = findById(passport.getId()); 
		return passport;
	}

	public void deleteById(Long id) {
		Passport passport = findById(id);
		em.remove(passport);
	}
}
