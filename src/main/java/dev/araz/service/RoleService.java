package dev.araz.service;

import dev.araz.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Set<Role> setRole(String user);
}