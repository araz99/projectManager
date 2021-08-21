package dev.araz.service;

import dev.araz.entity.Role;
import dev.araz.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Set<Role> setRole(String roleName) {
        return Set.of(roleRepository.findByName(roleName));
    }
}