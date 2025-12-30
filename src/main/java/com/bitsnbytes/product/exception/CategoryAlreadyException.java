package com.bitsnbytes.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CategoryAlreadyException extends RuntimeException{

    public CategoryAlreadyException(String message) {
        super(message);
    }

}
