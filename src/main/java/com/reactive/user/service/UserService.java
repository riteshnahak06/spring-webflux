package com.reactive.user.service;

import com.reactive.user.dto.CreateUserRequest;
import com.reactive.user.dto.UserDto;
import com.reactive.user.entity.User;
import com.reactive.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Mono<User> createUser(CreateUserRequest createUserRequest){
        User user =new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        return userRepository.save(user);
    }

    public Mono<User> findUserById(Integer id){
        return userRepository.findById(id);
    }
}
