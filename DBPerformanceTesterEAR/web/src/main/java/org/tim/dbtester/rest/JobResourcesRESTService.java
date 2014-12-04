package org.tim.dbtester.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tim.dbtester.model.Note;
import org.tim.dbtester.service.api.JobServiceRemote;

@Path("/jobs")
@RequestScoped
public class JobResourcesRESTService {

	@Inject
	JobServiceRemote jobRemote;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Note> getNotes() {
		return jobRemote.showNotes();
	}
}
