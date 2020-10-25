package com.example.fined187.jpashop.controller;

import com.example.fined187.jpashop.service.ItemServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemServiceImpl itemServiceImpl;

//    TODO Item 상품 등록, 조회, 수정 Test.

    @Test
    @DisplayName("상품 등록")
    void registerTest() throws Exception {
        //given
        given(itemServiceImpl.registerItem(any())).willReturn((long) 1);

        mvc.perform(post("/api/v1/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemName\" : \"BOOK2\", \"itemPrice\" : \"30000\"," +
                        "\"itemCount\" : \"10\",\"author\" : \"Lee\",\"item_isbn\" : \"1234-99\"," +
                        " \"type\" : \"book\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/api/v1/items/1"));

        //then
        then(itemServiceImpl).should(times(1)).registerItem(any());
    }
}
