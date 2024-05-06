package com.vti.demo_crud.repository;

import com.vti.demo_crud.entity.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
    boolean existsByGroupName (String groupName);
    Page<GroupEntity> findById(Integer id, Pageable pageable);

    Page<GroupEntity>findByGroupName(String groupName, Pageable pageable);
}
