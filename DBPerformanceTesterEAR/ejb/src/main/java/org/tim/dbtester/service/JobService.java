package org.tim.dbtester.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.tim.dbtester.model.Job;
import org.tim.dbtester.model.JobRequest;
import org.tim.dbtester.model.Note;
import org.tim.dbtester.model.Task;
import org.tim.dbtester.service.api.JobServiceRemote;

@Named
@Stateless
public class JobService extends EntityService implements JobServiceRemote {
	BasicLogger logger = Logger.getLogger(JobService.class);

	public JobService() {
		logger.info("instantiating job service bean");
	};

	public void addNotes() {

		for (int num = 0; num < 25; num++) {

			Note n = new Note();
			n.setId(UUID.randomUUID());
			n.setTitle("test note" + num);
			n.setDescription("this is a very special note.." + num);
			n.setAuthor("tjs");
			getEntityManager().persist(n);
			System.out.println("Added Note:" + n);
		}
	}

	public List<Note> showNotes() {
		logger.info("show notes");
		Query q = getEntityManager().createQuery(
				"Select n From " + Note.TABLE_NAME + " n", Note.class);

		List<Note> notes = new ArrayList<Note>();
		List<?> resultList = q.getResultList();
		for (Object n : resultList) {
			if (n instanceof Note) {
				notes.add((Note) n);
				System.out.println((Note) n);
			}
		}

		return notes;
	}

	@Override
	public void removeNotes() {

		List<Note> noteList;
		noteList = findAll(Note.class);
		for (Note n : noteList) {
			getEntityManager().remove(n);
			System.out
					.println("removing '" + n.getTitle() + "' --" + n.getId());
		}

	}

	@Override
	public JobRequest createJobRequest(JobRequest jr) {
		getEntityManager().persist(jr);
		System.out.println("Added Job Request:" + jr);
		return jr;
	}

	@Override
	public void addNoteToJobRequest(Note note, JobRequest jr) {

		if ((note.getId() == null)
				|| (getEntityManager().find(Note.class, note.getId()) == null)) {
			note.setId(UUID.randomUUID());

		}
		if (jr.getId() == null) {
			throw new PersistenceException(
					"No matching peristed job request for jr: " + jr);
		}

		if (getEntityManager().find(jr.getClass(), jr.getId()) != null) {
			note.setJobRequest(jr);
			getEntityManager().persist(note);
			System.out.println("Added Note:" + note + " to job request:" + jr);
		}
	}

	@Override
	public void addTaskToJobRequest(Task task, JobRequest jr) {
		if (jr.getId() == null) {
			throw new PersistenceException(
					"No matching peristed job request for jr: " + jr);
		}
		if (getEntityManager().find(jr.getClass(), jr.getId()) != null) {
			task.setJobRequest(jr);
			getEntityManager().persist(task);
			System.out.println("Added Task:" + task + " to job request:" + jr);
		}
	}

	@Override
	public Job acceptJobRequest(JobRequest jr) {

		if (getEntityManager().find(jr.getClass(), jr.getId()) != null) {
			Job j = new Job();
			j.setCost(jr.getMaxCost());
			j.setJobName(jr.getJobName());
			j.setOriginatingRequest(jr);
			j.setLocation("3423 23rd street");
			getEntityManager().persist(j);
			System.out.println("Job Accepted:" + j + " for job request:" + jr);
			return j;
		}
		return null;
	}

	public List<Job> getJobs() {
		logger.info("getting jobs");
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Job> c = builder.createQuery(Job.class);
		Root<Job> job = c.from(Job.class);
		c.select(job);
		List<?> results = getEntityManager().createQuery(c).getResultList();

		List<Job> jobs = new ArrayList<Job>();
		for (Object o : results) {
			if (o instanceof Job) {
				jobs.add((Job) o);
			}
		}

		logger.info("jobs" + jobs.size());
		return jobs;
	}

	@Override
	public Job createJob(Job j) {
		if (j != null) {
			getEntityManager().persist(j);
		}
		return j;
	}
}