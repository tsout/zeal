package org.gem.java8.scanner;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VBCCDirectoryService {

	private static final String DIRECTORY_PATH = "./TextFiles/VBCC-Directory.txt";
	private static final String RAW_ADDRESS_REGEX = "\\d+.*";
	private static final String CITY_STATE_ZIP_REGEX = ".*\\d{5}";
	private static final String NAME_RECORD_REGEX = ".*,\\s+\\w+";
	private static final String PHONE_REGEX = "((\\d{3}-\\d{3}-\\d{4})|(\\d{3}-\\d{4})|(\\d{1}-\\d{3}-\\d{3}-\\d{4}))";

	Logger log = Logger.getLogger(this.getClass().getTypeName());

	private List<Member> mList = new ArrayList<Member>();
	private final Map<String, String> emailMap = new HashMap<String, String>();
	private Set<String> emails = new HashSet<String>();

	public VBCCDirectoryService() {
		load();

	}

	public Map<String, String> getEmails() {
		if (emailMap.isEmpty()) {
			for (Member m : mList) {
				emailMap.put(m.firstName + " " + m.lastName, m.email);
			}
		}
		Map<String, String> m = new HashMap<String, String>();
		m.putAll(emailMap);
		return m;
	}

	public Set<String> getEmailSet() {

		if (emails.isEmpty()) {
			for (Member m : mList) {
				emails.add(m.email);
			}
		}
		return emails;
	}

	private Member createNewMemberRecord(String line) {
		log.info("adding name: " + line);
		Member m = new Member();
		m.firstName = line.split(",")[1];
		m.lastName = line.split(",")[0];
		return m;
	}

	private void parseAndUpdateMember(String line, Member memberToUpdate) {

		if (line.matches(CITY_STATE_ZIP_REGEX)) {
			// parse city state
			log.info("adding city: " + line);
			parseCityStateZip(line, memberToUpdate);
		}

		if (Pattern.compile(PHONE_REGEX).matcher(line).find()) {
			// parse phone
			log.info("adding phone: " + line);
			parsePhone(line, memberToUpdate);
		}
		if (line.matches(RAW_ADDRESS_REGEX)) {
			parseAddress(line, memberToUpdate);

		}
		if (line.contains("@")) {
			parseEmail(line, memberToUpdate);
		}

	}

	private void parseEmail(String line, Member m) {
		log.info("adding email: " + line);
		String[] contactInfo = line.split("\\|");
		m.email = contactInfo[0];
	}

	private void parseAddress(String line, Member m) {
		String rawAddress = line.trim();
		m.street1 = rawAddress;
	}

	/**
	 * Parse a phone record from string like: cwatkins5@cox.net | Home 428-0574
	 * 
	 * @param line
	 * @param m
	 */
	private void parsePhone(String line, Member m) {
		log.info("adding phone: " + line);

		String[] contactInfo = line.split("\\|");
		String phoneInfo;
		if (contactInfo.length > 1) {
			phoneInfo = contactInfo[1];
		}
		phoneInfo = contactInfo[0];
		Pattern p = Pattern.compile(PHONE_REGEX);
		Matcher matcher = p.matcher(phoneInfo);

		// search for cwatkins5@cox.net | Home 428-0574 | Cell Phone
		// 1-444-333-3333
		while (matcher.find()) {
			String phoneType = line.substring(0, matcher.start() - 1);
			String phoneNumber = line.substring(matcher.start(), matcher.end());
			m.phoneNumbers.put(phoneType, phoneNumber);
		}
	}

	private void parseCityStateZip(String line, Member m) {
		// Virginia Beach, VA 23456
		m.city = line.split(",")[0];
		m.state = line.split(",")[1].trim().split(" ")[0];
		m.zip = line.split(",")[1].trim().split(" ")[1];

	}

	public void printDirectory() {
		for (Member m : this.mList) {
			System.out.println(m);
		}
	}

	private void load() {
		Path p = FileSystems.getDefault().getPath("TextFiles", "VBCC-Directory.txt");

		mList = new ArrayList<Member>();

		try {
			Scanner scanner = new Scanner(Files.newBufferedReader(p));

			Member m = new Member();
			boolean firstPass = true;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				if (line.matches(NAME_RECORD_REGEX)) {
					if (firstPass) {
						firstPass = false;
					} else {
						mList.add(m);
					}
					;
					m = createNewMemberRecord(line);
					continue;
				} else {
					parseAndUpdateMember(line, m);
				}
			}
			scanner.close();

		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (NoSuchElementException nse) {

		}
	}

	public List<Member> getMembers() {
		List<Member> cloneList = new ArrayList<Member>();
		cloneList.addAll(mList);
		return cloneList;
	}
}
