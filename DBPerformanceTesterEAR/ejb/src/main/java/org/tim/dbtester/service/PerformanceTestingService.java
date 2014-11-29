package org.tim.dbtester.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.tim.dbtester.model.BasicEntity;
import org.tim.dbtester.model.Job;
import org.tim.dbtester.model.JobRequest;
import org.tim.dbtester.model.Note;
import org.tim.dbtester.model.Task;
import org.tim.dbtester.service.api.PerformanceTestingServiceRemote;
import org.zeal.common.persistence.PersistableObject;

@Stateless
@Remote
public class PerformanceTestingService implements
		PerformanceTestingServiceRemote {

	@Inject
	EntityManager em;

	Logger logger = Logger.getLogger(PerformanceTestingService.class);
	private Date startTime = null;
	private Date markTime = null;
	private Date endTime = null;

	public void addNotes() {

		for (int num = 0; num < 25; num++) {

			Note n = new Note();
			n.setId(UUID.randomUUID());
			n.setTitle("test note" + num);
			n.setDescription("this is a very special note.." + num);
			n.setAuthor("tjs");
			em.persist(n);
			System.out.println("Added Note:" + n);
		}
	}

	public void showNotes() {
		Query q = em.createQuery("Select n From " + Note.TABLE_NAME + " n",
				Note.class);
		List<?> resultList = q.getResultList();
		for (Object n : resultList) {
			if (n instanceof Note)
				System.out.println((Note) n);
		}
		;

	}

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

	@Override
	public void removeNotes() {

		List<Note> noteList;
		noteList = findAll(Note.class);
		for (Note n : noteList) {
			em.remove(n);
			System.out
					.println("removing '" + n.getTitle() + "' --" + n.getId());
		}

	}

	@SuppressWarnings("unchecked")
	private <E> List<E> findAll(Class<E> o) {

		try {
			E result;
			result = o.newInstance();
			Query q = em.createQuery("Select o from "
					+ ((PersistableObject) result).getMappedTableName() + " o",
					o);
			List<E> list = q.getResultList();
			return list;
		} catch (InstantiationException e) {
			PersistenceException pe = new PersistenceException("");
			throw pe;

		} catch (IllegalAccessException e) {
			PersistenceException pe = new PersistenceException("");
			throw pe;
		}

	}

	@Override
	public JobRequest createJobRequest(JobRequest jr) {
		em.persist(jr);
		System.out.println("Added Job Request:" + jr);
		return jr;
	}

	@Override
	public void addNoteToJobRequest(Note note, JobRequest jr) {

		if ((note.getId() == null)
				|| (em.find(Note.class, note.getId()) == null)) {
			note.setId(UUID.randomUUID());

		}
		if (jr.getId() == null) {
			throw new PersistenceException(
					"No matching peristed job request for jr: " + jr);
		}

		if (em.find(jr.getClass(), jr.getId()) != null) {
			note.setJobRequest(jr);
			em.persist(note);
			System.out.println("Added Note:" + note + " to job request:" + jr);
		}
	}

	@Override
	public void addTaskToJobRequest(Task task, JobRequest jr) {
		if (jr.getId() == null) {
			throw new PersistenceException(
					"No matching peristed job request for jr: " + jr);
		}
		if (em.find(jr.getClass(), jr.getId()) != null) {
			task.setJobRequest(jr);
			em.persist(task);
			System.out.println("Added Task:" + task + " to job request:" + jr);
		}
	}

	@Override
	public Job acceptJobRequest(JobRequest jr) {

		if (em.find(jr.getClass(), jr.getId()) != null) {
			Job j = new Job();
			j.setCost(jr.getMaxCost());
			j.setTitle(jr.getJobName());
			j.setOriginatingRequest(jr);
			j.setLocation("3423 23rd street");
			em.persist(j);
			System.out.println("Job Accepted:" + j + " for job request:" + jr);
			return j;
		}
		return null;
	}
}
