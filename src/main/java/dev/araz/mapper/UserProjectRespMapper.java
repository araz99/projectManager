package dev.araz.mapper;

import dev.araz.dto.UserProjectRespDTO;
import dev.araz.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserProjectRespMapper implements MapperToDTO<UserProjectRespDTO, User> {
    @Override
    public UserProjectRespDTO toDTO(User user) {
        return new UserProjectRespDTO(
                user.getId(),
                user.getUsername(),
                user.getRegistrationDate()
        );
    }
}
