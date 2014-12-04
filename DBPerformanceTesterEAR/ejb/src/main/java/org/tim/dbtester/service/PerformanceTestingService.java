package org.tim.dbtester.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.tim.dbtester.model.BasicEntity;
import org.tim.dbtester.service.api.PerformanceTestingServiceRemote;

@Stateless
@Remote
public class PerformanceTestingService extends EntityService implements
		PerformanceTestingServiceRemote {

	Logger logger = Logger.getLogger(PerformanceTestingService.class);

	private Date startTime = null;
	private Date markTime = null;
	private Date endTime = null;

	public void generateRecords(Integer numRecords) {

		Integer x = 0;

		while (x < numRecords) {
			BasicEntity be = new BasicEntity();
			be.setName("Basic Entity #" + x);
			be.setCost(x);
			be.setDescription(be.getName() + " description");
			em.persist(be);

			// logger.info("persisted: " + be);
			x++;
		}
		em.flush();
	}

	public void generateData(Integer num) {
		startTest();
		mark("Generating Data");
		generateRecords(num);
		mark("Test data Generated");
		endTest();

	}

	public void resetData() {
		startTest();
		mark("Starting Reset");
		reset();
		mark("ending reset");
		endTest();
	}

	private void reset() {

		List<?> entities = null;
		// find all Entities
		Query q = em.createQuery("Select be from BasicTable be",
				BasicEntity.class);
		entities = q.getResultList();
		for (Object be : entities) {

			if (be instanceof BasicEntity) {
				BasicEntity b = (BasicEntity) be;
				em.remove(b);
				// logger.info("removing: " + be.toString());
			}
		}

		em.flush();

	}

	public void startTest() {
		startTime = new Date();
	}

	public void endTest() {
		endTime = new Date();
	}

	public void mark(String message) {
		long duration = 0;
		Date currentTime = new Date();
		markTime = currentTime;
		duration = currentTime.getTime() - startTime.getTime();
		logger.info("mark@" + duration + "ms\t" + message);
	}

	// public Member findByEmail(String email) {
	// CriteriaBuilder cb = em.getCriteriaBuilder();
	// CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
	// Root<Member> member = criteria.from(Member.class);
	// // Swap criteria statements if you would like to try out type-safe
	// criteria queries, a new
	// // feature in JPA 2.0
	// // criteria.select(member).where(cb.equal(member.get(Member_.email),
	// email));
	// criteria.select(member).where(cb.equal(member.get("email"), email));
	// return em.createQuery(criteria).getSingleResult();
	// }
	//
	// public List<Member> findAllOrderedByName() {
	// CriteriaBuilder cb = em.getCriteriaBuilder();
	// CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
	// Root<Member> member = criteria.from(Member.class);
	// // Swap criteria statements if you would like to try out type-safe
	// criteria queries, a new
	// // feature in JPA 2.0
	// // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
	// criteria.select(member).orderBy(cb.asc(member.get("name")));
	// return em.createQuery(criteria).getResultList();
	// }
}
