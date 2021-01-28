package com.flexisaf.challenge.challenge.apiresponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiResponse<T> {
    private String message;
    private String status;
    private T data;

    public ApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    private List<?> errors;

    public ApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();

}