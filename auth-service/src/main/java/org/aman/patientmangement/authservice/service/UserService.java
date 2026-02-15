package org.aman.patientmangement.authservice.service;

import org.aman.patientmangement.authservice.model.User;
import org.aman.patientmangement.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

      UserRepository userRepository;

    public Optional<User> findByEmail(String email){

        return userRepository.findByEmail(email);
    }
}
