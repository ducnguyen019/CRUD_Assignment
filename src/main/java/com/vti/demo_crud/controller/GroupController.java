package com.vti.demo_crud.controller;

import com.vti.demo_crud.entity.GroupEntity;
import com.vti.demo_crud.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    private  final IGroupService groupService;
    @Autowired
    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/get-all")
    public Page<GroupEntity> getAllGroup(Pageable pageable){
        return groupService.getAllGroup(pageable);
    }

    @GetMapping("/{id}")
    public Page<GroupEntity> getGroupById(@PathVariable Integer id, Pageable pageable){
        return groupService.getGroupById(id, pageable);
    }

    @GetMapping("/byGroupName/{groupName}")
    public Page<GroupEntity> getGroupByGroupName(@PathVariable String groupName, Pageable pageable){
        return groupService.getGroupByGroupName(groupName, pageable);
    }

    @PostMapping
    public GroupEntity createGroup(@RequestBody GroupEntity groupEntity){
        return groupService.createGroup(groupEntity);
    }

    @PutMapping("/{id}")
    public GroupEntity updateGroup(@PathVariable Integer id, @RequestBody GroupEntity groupEntity){
        return groupService.updateGroup(id, groupEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Integer id){
        groupService.deleteGroupById(id);
    }
}
