package com.example.jpashop;

import com.example.fined187.jpashop.exception.NotFoundException;
import com.example.jpashop.domain.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Controller
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    ResponseEntity<?> NotFoundException(NotFoundException e) {

        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail("NOT_FOUND", e.getMessage()));

    }
}
