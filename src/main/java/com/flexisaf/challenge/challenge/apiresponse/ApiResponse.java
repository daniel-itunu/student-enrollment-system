package com.flexisaf.challenge.challenge.apiresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private String message;
    private String status;
    private T data;

    public ApiResponse(String message, String status, T errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public ApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    private T errors;

    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();

}