package com.example.jpashop.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)  //  대기 중 특정 이벤트 발생 시 catch 해서 실행.(세션 정보들 가져와서 넣어줌)
@MappedSuperclass
@Getter
public class BaseDataEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

}
