package com.vti.demo_crud.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "`group`")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "GROUP_NAME", unique = true, nullable = false, length = 50)
    private String groupName;

    @Column(name = "MEMBER", nullable = false)
    private Integer member;

    @Column(name = "CREATOR", nullable = false)
    private String creator;

    @Column(name = "CREATE_DATE", nullable = false, updatable = true)
    @CreationTimestamp //tự tạo thời gian
    private Date createDate;
}
