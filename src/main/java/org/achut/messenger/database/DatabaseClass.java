package org.achut.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.achut.messenger.model.Message;
import org.achut.messenger.model.Profile;

public class DatabaseClass {

	private static Map <Long , Message > messages = new HashMap<>();
	private static Map <Long , Profile > profiles = new HashMap<>();
	
	
	public static Map<Long , Message> getAllMessages() {
		return messages;
	}
	
	public static Map<Long, Profile> getAllProfiles() {
		return profiles;
	}
} 
