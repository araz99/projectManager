package dev.araz.service;

import dev.araz.dto.UserDTO;
import dev.araz.entity.User;
import dev.araz.mapper.Mapper;
import dev.araz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Mapper<UserDTO,User> userMapper;
    private final RoleService roleService;
    @Override
    public ResponseEntity<UserDTO> addNewUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            save(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists!");
    }

    private void save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setRoles(roleService.setRole("USER"));
        userRepository.save(user);
    }
}