package com.tct.springboot.security.service;

import com.tct.springboot.model.users.User;
import com.tct.springboot.security.dto.AuthenticatedUserDto;
import com.tct.springboot.security.dto.RegistrationRequest;
import com.tct.springboot.security.dto.RegistrationResponse;


public interface UserService {

    User findByUsername(String username);

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
