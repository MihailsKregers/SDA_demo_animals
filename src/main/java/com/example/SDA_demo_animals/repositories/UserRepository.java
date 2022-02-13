package com.example.SDA_demo_animals.repositories;

import com.example.SDA_demo_animals.security.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
