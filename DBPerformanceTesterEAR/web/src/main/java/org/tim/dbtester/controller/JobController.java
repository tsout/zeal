package org.tim.dbtester.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Produces;

import org.jboss.logging.BasicLogger;
import org.tim.dbtester.model.Job;
import org.tim.dbtester.model.Note;
import org.tim.dbtester.service.api.JobServiceRemote;

@Model
public class JobController {

	@Inject
	private BasicLogger log;

	@Inject
	private FacesContext facesContext;

	@Inject
	private JobServiceRemote jobRemote;

	private List<Job> jobs;

	private List<Note> notes;

	private Job newJob;

	// public JobController() {
	// notes = jobRemote.showNotes();
	// jobs = jobRemote.getJobs();
	// log.info("constructing jobs controller");
	// };

	public void doStuff() {
		log.info("doing stuff..." + newJob);
		jobRemote.createJob(newJob);
	}

	@PostConstruct
	public void initNewJob() {
		log.info("constructing jobs controller");
		newJob = new Job();
		notes = jobRemote.showNotes();
		jobs = jobRemote.getJobs();
		log.info("initializing a new job .. " + newJob);
	}

	// @PostConstruct
	// public void initNewJob() {
	// newJob = new Job();
	// log.info("creating a new job in PostConstruct");
	// }

	@Produces
	@Named
	public List<Job> getJobs() {
		log.info("getting jobs");
		return jobs;
	}

	@Produces
	@Named
	public List<Note> getNotes() {
		log.info("getting notes");
		return notes;
	}

	@Produces
	@Named
	public Job getNewJob() {
		log.info("getting a new job");
		return newJob;
	}
}
