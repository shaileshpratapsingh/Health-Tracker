package com.psquickit.common;

import java.util.HashSet;
import java.util.Set;

public enum UserType {
	
	INDIVIDUAL_USER("IndividualUser"),
	DOCTOR_USER("DoctorUser");
	
	private final String name;
	private UserType(String name){
		this.name=name;
	}
	 
	static {
		ensureUniqueIDs();
	}

	private static void ensureUniqueIDs() throws AssertionError {
		Set<String> names = new HashSet<>();
		for (UserType t : values()) {
			if (!names.add(t.name)) {
				throw new RuntimeException("Duplicate user type " + t.name);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public static UserType fromName(String name) {
		for (UserType s : values()) {
			if (s.name.equalsIgnoreCase(name)) {
				return s;
			}
		}
		throw new IllegalArgumentException("Invalid user type: " + name);
	}
	
}

