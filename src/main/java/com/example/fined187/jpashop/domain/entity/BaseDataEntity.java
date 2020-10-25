package com.example.fined187.jpashop.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseDataEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

}
