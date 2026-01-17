package com.reactive.user.repo;

import com.reactive.user.dto.UserDto;
import com.reactive.user.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
