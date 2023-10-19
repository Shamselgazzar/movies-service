package com.sumerge.moviesservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieErrorResponse {

    private Integer status;
    private String message;
    private String error_class;
    private Long timeStamp;

}
