package com.tct.springboot;

import com.tct.springboot.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CommonResponse<T> {
    private StatusCode code;
    private String description;
    private T data;
}
