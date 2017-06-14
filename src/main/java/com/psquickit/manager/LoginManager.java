package com.psquickit.manager;

import com.psquickit.pojo.user.UserLoginRequest;
import com.psquickit.pojo.user.UserLoginResponse;

public interface LoginManager {

	public UserLoginResponse login(String secretToken, UserLoginRequest request) throws Exception;
}
