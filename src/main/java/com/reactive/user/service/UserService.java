package com.reactive.user.service;

import com.reactive.user.dto.CreateUserRequest;
import com.reactive.user.entity.User;
import com.reactive.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Mono<List<User>> createUsers(List<CreateUserRequest> requests) {

        return Flux.fromIterable(requests)
                .map(req -> {
                    User user = new User();
                    user.setFirstName(req.getFirstName());
                    user.setLastName(req.getLastName());
                    user.setEmail(req.getEmail());
                    user.setPassword(req.getPassword());
                    return user;
                })
                .flatMap(userRepository::save)
                .collectList();
    }


    public Mono<User> findUserById(Integer id){
        return userRepository.findById(id);
    }
}
