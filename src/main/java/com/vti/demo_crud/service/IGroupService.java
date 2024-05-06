package com.vti.demo_crud.service;

import com.vti.demo_crud.entity.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGroupService {
    Page<GroupEntity> getAllGroup(Pageable pageable);
    Page<GroupEntity> getGroupById(Integer id, Pageable pageable);
    Page<GroupEntity> getGroupByGroupName(String groupName, Pageable pageable);
    GroupEntity createGroup(GroupEntity groupEntity);
    GroupEntity updateGroup(Integer id, GroupEntity groupEntity);
    void deleteGroupById(Integer id);

}
