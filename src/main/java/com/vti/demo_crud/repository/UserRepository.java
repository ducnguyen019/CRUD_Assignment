package com.vti.demo_crud.repository;

import com.vti.demo_crud.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmailOrPhoneNumber(String email, String phoneNumber);

}
