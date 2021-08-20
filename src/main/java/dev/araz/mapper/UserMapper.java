package dev.araz.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.araz.dto.UserDTO;
import dev.araz.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<UserDTO, User>{

    private final ObjectMapper mapper;

    @Override
    public User toEntity(UserDTO userDTO) {
        return mapper.convertValue(userDTO, User.class);
    }

    @Override
    public UserDTO toDTO(User user) {
        return mapper.convertValue(user, UserDTO.class);
    }
}