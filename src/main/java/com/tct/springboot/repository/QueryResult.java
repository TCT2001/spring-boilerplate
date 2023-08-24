package com.tct.springboot.repository;

import com.tct.springboot.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QueryResult<T> {
    private StatusCode statusCode;
    private T data;

    public QueryResult(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
