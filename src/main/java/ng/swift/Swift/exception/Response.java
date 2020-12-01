package ng.swift.Swift.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private String status;
    private String message;
    private Integer code;
    private T data;

}
