package com.tct.springboot.security.service;

import com.tct.springboot.model.users.User;
import com.tct.springboot.model.users.UserRole;
import com.tct.springboot.repository.UserRepository;
import com.tct.springboot.security.dto.AuthenticatedUserDto;
import com.tct.springboot.security.dto.RegistrationRequest;
import com.tct.springboot.security.dto.RegistrationResponse;
import com.tct.springboot.security.mapper.UserMapper;
import com.tct.springboot.service.UserValidationService;
import com.tct.springboot.utils.GeneralMessage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserValidationService userValidationService;

    private final GeneralMessage generalMessage;

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(null, username).getData();
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {

        userValidationService.validateUser(registrationRequest);

        final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);

        try (EntityManager entityManager = userRepository.getEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }

        final String username = registrationRequest.getUsername();
        final String registrationSuccessMessage = generalMessage.getMessage(REGISTRATION_SUCCESSFUL, username);

        log.info("{} registered successfully!", username);

        return new RegistrationResponse(registrationSuccessMessage);
    }

    @Override
    public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

        final User user = findByUsername(username);

        return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
    }
}
