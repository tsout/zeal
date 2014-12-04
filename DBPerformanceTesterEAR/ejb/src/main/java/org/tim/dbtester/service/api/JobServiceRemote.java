package org.tim.dbtester.service.api;

import java.util.List;

import org.tim.dbtester.model.Job;
import org.tim.dbtester.model.JobRequest;
import org.tim.dbtester.model.Note;
import org.tim.dbtester.model.Task;

public interface JobServiceRemote {

	public void addNotes();

	public List<Note> showNotes();

	public void removeNotes();

	public JobRequest createJobRequest(JobRequest jr);

	public void addTaskToJobRequest(Task task, JobRequest jr);

	public void addNoteToJobRequest(Note note, JobRequest jr);

	public Job acceptJobRequest(JobRequest jr);

	public List<Job> getJobs();

}