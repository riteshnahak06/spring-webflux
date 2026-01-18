package com.reactive.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotNull(message = "Id Cannot be null in update request !")
    private Integer id;
    @NotBlank(message = "password cannot be blank")
    private String password;}
