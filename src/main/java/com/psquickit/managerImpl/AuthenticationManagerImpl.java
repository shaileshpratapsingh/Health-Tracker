package com.psquickit.managerImpl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.PreDestroy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.psquickit.common.HandledException;
import com.psquickit.manager.AuthenticationManager;
import com.psquickit.util.CommonProperties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class AuthenticationManagerImpl implements AuthenticationManager {

	private final CacheManager manager;
	private final Cache authTokenCache;
	
	private final List<String> secretTokens = Lists.newArrayList("c27afac7-1e3b-4e99-9351-88772f288132", 
			"ef491d53-a373-46f4-9626-96d0df979ba2");

	@Autowired
	public AuthenticationManagerImpl() {
		manager = CacheManager.newInstance(CommonProperties.CONFIG_DIR_PATH + File.separator + "ehcache.xml");
		authTokenCache = manager.getCache("AuthToken");
	}
	
	@Override
	public String generateAuthToken(long userId) {
		String authToken = UUID.randomUUID().toString();
		authTokenCache.put(new Element(authToken, userId));
		return authToken;
	}

	@Override
	public void removeauthToken(String authToken) {
		authTokenCache.remove(authToken);
	}

	@Override
	public long getUserId(String authToken) throws Exception {
		Element cachedResult = authTokenCache.get(authToken);
		if (cachedResult == null) {
			throw new HandledException("UNAUTHORIZED_ACCESS", "Unauthorized access");
		} else {
			//replenish the cache
			long userId = ((Long) cachedResult.getObjectValue()).longValue();
			authTokenCache.put(new Element(authToken, userId));
		}
		cachedResult = authTokenCache.get(authToken);
		return ((Long) cachedResult.getObjectValue()).longValue();
	}

	@PreDestroy
	public void destroy() {
		authTokenCache.dispose();
		manager.shutdown();
	}
	
	@Override
	public void validateSecretToken(String secretToken) throws Exception {
		if (!secretTokens.contains(secretToken)) {
			throw new HandledException("UNAUTHORIZED_ACCESS", "Unauthorized access");
		}
	}
}
