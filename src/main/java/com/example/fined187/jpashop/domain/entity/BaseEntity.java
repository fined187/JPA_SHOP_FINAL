package com.example.fined187.jpashop.domain.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity extends BaseDataEntity{
    @CreatedBy
    @Column(updatable = false)
    private String createUser;

    @LastModifiedDate
    private String updateUser;
}
