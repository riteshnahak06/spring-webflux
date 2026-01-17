package com.reactive.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("users")
@Getter@Setter
public class User {

    @Id
    private UUID id;

    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    private String email;
    private String password;
}
