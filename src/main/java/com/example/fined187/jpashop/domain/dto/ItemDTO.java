package com.example.fined187.jpashop.domain.dto;

import lombok.Data;

@Data
public class ItemDTO {

    private String itemName;
    private int itemPrice;
    private int itemCount;
    private String artist;
    private String bAuthor;
    private String isbn;
    private String code;
    private String type;

}
