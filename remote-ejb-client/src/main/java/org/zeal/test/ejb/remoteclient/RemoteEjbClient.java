package org.zeal.test.ejb.remoteclient;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceException;

import org.tim.dbtester.model.Job;
import org.tim.dbtester.model.JobRequest;
import org.tim.dbtester.model.Note;
import org.tim.dbtester.model.Task;
import org.tim.dbtester.service.PerformanceTestingService;
import org.tim.dbtester.service.api.PerformanceTestingServiceRemote;

public class RemoteEjbClient {

	public static void main(String[] args) throws Exception,
			PersistenceException {
		PerformanceTestingServiceRemote remote = lookupRemoteEJB();
		// remote.generateData(5000);
		// remote.resetData();
		// testNotes(remote);

		testJobRequest(remote);

	}

	private static void testNotes(PerformanceTestingServiceRemote remote) {
		remote.addNotes();
		remote.showNotes();
		remote.removeNotes();
	}

	private static void testJobRequest(PerformanceTestingServiceRemote remote) {

		JobRequest jr = new JobRequest();
		jr.setCustomerName("Bill");
		String jobDescription = "this describes the work at bills house";
		jr.setJobDescription(jobDescription);
		jr.setPreferredCost(4.23);
		jr.setJobName("Bills House");
		jr.setCustomerName("jane");
		jr.setMaxCost(23423.23);

		jr = remote.createJobRequest(jr);

		Note note = new Note();
		note.setAuthor("tjs");
		note.setDescription("we should do something like this..");
		remote.addNoteToJobRequest(note, jr);

		Task task = new Task();
		task.setTitle("Just Do it!");
		Calendar mustBeCompletedBy = Calendar.getInstance();
		Calendar mustBeStartedOn = Calendar.getInstance();
		Date now = new Date();
		mustBeCompletedBy.setTime(now);
		mustBeStartedOn.setTime(now);
		task.setMustBeCompletedBy(mustBeCompletedBy);
		task.setMustBeStartedOn(mustBeStartedOn);
		remote.addTaskToJobRequest(task, jr);

		Job job = remote.acceptJobRequest(jr);

		System.out.print("Created a job!" + job);
	}

	public static PerformanceTestingServiceRemote lookupRemoteEJB()
			throws NamingException {

		Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		// jndiProperties.put("jboss.naming.client.ejb.context", true);

		final Context context = new InitialContext(jndiProperties);

		final String appName = "DBPerformanceTesterEAR-ear";
		final String moduleName = "DBPerformanceTesterEAR-ejb";
		final String distinctName = "";
		final String beanName = PerformanceTestingService.class.getSimpleName();
		final String viewClassName = PerformanceTestingServiceRemote.class
				.getName();

		String lookupString = "ejb:" + appName + "/" + moduleName + "/"
				+ distinctName + "/" + beanName + "!" + viewClassName;
		System.out.println("EJB Config: " + lookupString);
		PerformanceTestingServiceRemote testingSvc = (PerformanceTestingServiceRemote) context
				.lookup(lookupString);
		;
		return testingSvc;
	}

}
