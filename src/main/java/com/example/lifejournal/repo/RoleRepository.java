package com.example.lifejournal.repo;

import com.example.lifejournal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}