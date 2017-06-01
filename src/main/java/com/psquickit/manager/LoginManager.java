package com.psquickit.manager;

import com.psquickit.pojo.UserLoginResponse;

public interface LoginManager {

	public UserLoginResponse login(String uid) throws Exception;
}
