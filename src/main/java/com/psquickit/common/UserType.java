package com.psquickit.common;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum UserType {
	
	IndividualUser(1),
	
	DoctorUser(2);
	
	private final int id;

	private UserType(int id) {
		this.id = id;
	}

	static {
		ensureUniqueIDs();
	}

	private static void ensureUniqueIDs() throws AssertionError {
		Set<Integer> ids = new HashSet<>();
		for (UserType t : values()) {
			if (!ids.add(t.id)) {
				throw new RuntimeException("Duplicate UserType ID: " + t.id);
			}
		}
	}

	/**
	 * Returns the ID of UserType. This is the value that represents this ID in the
	 * database.
	 */
	public int getId() {
		return id;
	}

	public static UserType fromId(int id) {
		for (UserType s : values()) {
			if (s.id == id) {
				return s;
			}
		}
		throw new IllegalArgumentException("Invalid UserType ID: " + id);
	}

	public static Optional<UserType> tryId(int id) {
		for (UserType s : values()) {
			if (s.id == id) {
				return Optional.of(s);
			}
		}
		return Optional.empty();
	}
	
}

