package com.reactive.user.service;

import com.reactive.user.dto.CreateUserRequest;
import com.reactive.user.dto.UpdateResponse;
import com.reactive.user.dto.UpdateUserRequest;
import com.reactive.user.entity.User;
import com.reactive.user.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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

    public Flux<User> fetchAllUsers(){
        return userRepository.findAll();
    }
    public Mono<UpdateResponse> updatePassword(UpdateUserRequest updateUserRequest){
        return userRepository
                .findById(updateUserRequest.getId())
                .doOnSubscribe(s->log.info("Find By Id call started for id {} ",updateUserRequest.getId()))
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .flatMap(user -> {
                    log.info("DB call started!");
                    user.setPassword(updateUserRequest.getPassword());
                    log.info("Password set completed !");
                    return userRepository.save(user);
                })
                .map(user -> UpdateResponse.builder()
                        .id(user.getId())
                        .message("Update Successful !")
                        .build());
    }

}
