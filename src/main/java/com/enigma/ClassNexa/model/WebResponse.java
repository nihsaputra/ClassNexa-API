package com.enigma.ClassNexa.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {
    public String status;
    private String message;
    private PagingResponse pagging;
    private T data;

}
