package dev.araz.service;

import dev.araz.dto.UserDTO;
import dev.araz.entity.User;
import dev.araz.exception.UserNotExist;
import dev.araz.mapper.Mapper;
import dev.araz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final Mapper<UserDTO, User> userMapper;
    private final RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }

    @Override
    public ResponseEntity<UserDTO> addNewUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            save(userDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists!");
    }

    @Override
    public User getUserByName(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null)
            return user;
        throw new UserNotExist("User with name" + username + " not exists!");
    }

    @Override
    public User getAuthenticationUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }


    private void save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.setRoles(roleService.setRole("ROLE_USER"));
        userRepository.save(user);
    }
}