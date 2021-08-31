package dev.araz.service;

import dev.araz.dto.UserDTO;
import dev.araz.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<UserDTO> addNewUser(UserDTO userDTO);

    User getUserByName(String username);

    User getAuthenticationUser();
}