package com.vti.demo_crud.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @NotNull
    private String fullName;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 12)
    private String password;

    @NotNull
    private LocalDate birthDay;

    private LocalDateTime createdDate;

}