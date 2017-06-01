package com.psquickit.manager;

public interface AuthenticationManager {
	
	public String generateAuthToken(long userId);
	
	public void removeauthToken(String authToken);
	
	public long getUserId(String authToken) throws Exception;
}
