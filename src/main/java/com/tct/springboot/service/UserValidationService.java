package com.tct.springboot.service;

import com.tct.springboot.CustomMessage;
import com.tct.springboot.exceptions.CommonException;
import com.tct.springboot.repository.UserRepository;
import com.tct.springboot.security.dto.RegistrationRequest;
import com.tct.springboot.utils.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {
    private final UserRepository userRepository;

    private final ExceptionMessage exceptionMessage;

    public void validateUser(RegistrationRequest registrationRequest) {

        final String email = registrationRequest.getEmail();
        final String username = registrationRequest.getUsername();

        checkEmail(email);
        checkUsername(username);
    }

    private void checkUsername(String username) {

        final boolean existsByUsername = userRepository.existsByUsername(null, username).getData();

        if (existsByUsername) {

            log.warn("{} is already being used!", username);

            final String existsUsername = exceptionMessage.getMessage(CustomMessage.USERNAME_ALREADY_EXISTS);
            throw new CommonException(existsUsername);
        }

    }

    private void checkEmail(String email) {

        final boolean existsByEmail = userRepository.existsByEmail(null, email).getData();

        if (existsByEmail) {

            log.warn("{} is already being used!", email);

            final String existsEmail = exceptionMessage.getMessage(CustomMessage.EMAIL_ALREADY_EXISTS);
            throw new CommonException(existsEmail);
        }
    }

}
