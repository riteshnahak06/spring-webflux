package com.reactive.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateResponse {
    private Integer id;
    private String message;
}
