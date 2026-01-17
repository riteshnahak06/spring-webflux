package com.reactive.user.controller;

import com.reactive.user.dto.CreateUserRequest;
import com.reactive.user.dto.UserDto;
import com.reactive.user.entity.User;
import com.reactive.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public Mono<ResponseEntity<UserDto>>createUser(@RequestBody @Valid Mono<CreateUserRequest> createUserRequest){
        return createUserRequest
                .flatMap(userService::createUser)
                .map(user -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(toDto(user)));
    }
    @PostMapping("/create-users")
    public Mono<ResponseEntity<List<UserDto>>> createAllUser(
            @RequestBody Flux<CreateUserRequest> requestFlux) {
        return requestFlux
                .flatMap(userService::createUser)
                .map(this::toDto)
                .collectList()
                .map(ResponseEntity::ok);
    }


    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<UserDto>> findById(@PathVariable Integer id){
        return userService.findUserById(id)
                .map(user -> ResponseEntity.ok(toDto(user)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/fetchAll")
    public Mono<ResponseEntity<List<UserDto>>> getAllUser(){
        return userService.fetchAllUsers()
                .map(this::toDto)
                .collectList()
                .map(ResponseEntity::ok);
    }




    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
