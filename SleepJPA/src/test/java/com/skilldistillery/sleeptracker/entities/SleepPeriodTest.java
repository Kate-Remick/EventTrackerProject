package com.skilldistillery.sleeptracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SleepPeriodTest {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private SleepPeriod sleep;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SleepJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	@BeforeEach
	void setUp() throws Exception {
	    em = emf.createEntityManager();
	    sleep = em.find(SleepPeriod.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    sleep = null;
	}

	@Test
	@DisplayName("testing basic sleep mappings")
	void test1() {
		assertNotNull(sleep);
		assertEquals(sleep.getQuality(), 7);
		
	}
	@Test
	@DisplayName("testing basic sleep evening activity mappings")
	void test2() {
		assertNotNull(sleep);
		assertEquals(sleep.getEveningActivity().getName(), "Read a Book");
		
	}
	@Test
	@DisplayName("testing basic sleep workout mappings")
	void test3() {
		assertNotNull(sleep);
		assertEquals(sleep.getWorkout().getTimeOfDay(), "Morning");
		
	}
	

}
