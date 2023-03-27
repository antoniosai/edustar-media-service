package com.edustar.media.core.dto;

import lombok.Data;

@Data
public class HttpResponseDTO {
    private String message;
    private Object data;


    public HttpResponseDTO(String message, Object response) {

        this.message = message;
        this.data = response;
    }

    public HttpResponseDTO(String message) {
        this.message = message;
    }
}
