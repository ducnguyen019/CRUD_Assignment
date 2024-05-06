package com.vti.demo_crud.service;

import com.vti.demo_crud.entity.GroupEntity;
import com.vti.demo_crud.exception.AppException;
import com.vti.demo_crud.exception.ErrorResponse;
import com.vti.demo_crud.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class GroupService implements IGroupService{
    @Autowired //Để lấy giá trị
    private GroupRepository groupRepository;

    @Override
    public Page<GroupEntity> getAllGroup(Pageable pageable) {

        return groupRepository.findAll(pageable);
    }

    @Override
    public Page<GroupEntity> getGroupById(Integer id, Pageable pageable) {
        if (!groupRepository.findById(id).isPresent()) {
            throw new AppException((ErrorResponse.NOT_FOUND));
        }
        return groupRepository.findById(id, pageable);
    }

    @Override
    public Page<GroupEntity> getGroupByGroupName(String groupName, Pageable pageable) {
        if (groupRepository.findByGroupName(groupName, pageable).isEmpty()) {
            throw new AppException((ErrorResponse.NOT_FOUND));
        }
        return groupRepository.findByGroupName(groupName,pageable);
    }

    @Override
    public GroupEntity createGroup(GroupEntity groupEntity) {
        groupEntity.setId(null);
        if(groupRepository.existsByGroupName(groupEntity.getGroupName())){
            throw new AppException(ErrorResponse.EXISTED);
        }
        return groupRepository.save(groupEntity);
    }

    @Override
    public GroupEntity updateGroup(Integer id, GroupEntity groupEntity) {
        GroupEntity data = groupRepository.findById(id).get();
        data.setGroupName(groupEntity.getGroupName());
        data.setMember(groupEntity.getMember());
        data.setCreator(groupEntity.getCreator());
        data.setCreateDate(groupEntity.getCreateDate());
        return groupRepository.save(data);
    }

    @Override
    public void deleteGroupById(Integer id) {
        if (!groupRepository.findById(id).isPresent()) {
            throw new AppException((ErrorResponse.NOT_FOUND));
        }
        groupRepository.deleteById(id);
    }
}
