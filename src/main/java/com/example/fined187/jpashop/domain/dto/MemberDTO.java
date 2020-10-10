package com.example.fined187.jpashop.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {
    private String name;
    private String email;
    private String street;
    private String city;
    private String zipcode;

}
