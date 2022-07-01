package com.charlie.addressbook.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFound extends RuntimeException {

    public ContactNotFound(String message) {

        super(message);
    }
}
