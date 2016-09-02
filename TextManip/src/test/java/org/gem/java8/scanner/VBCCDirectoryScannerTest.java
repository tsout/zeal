package org.gem.java8.scanner;

import static org.junit.Assert.*;

import org.junit.Test;

public class VBCCDirectoryScannerTest {

	VBCCDirectoryService svc = new VBCCDirectoryService();

	@Test
	public void test() {
		svc.printDirectory();
		assertNotNull(svc.getMembers().toString());
	}

	@Test
	public void test1() throws Exception {
		System.out.println(svc.getEmailSet());
	}

}
