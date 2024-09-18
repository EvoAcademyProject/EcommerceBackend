package com.backend.ecommercebackend.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTokenException extends RuntimeException{
  private final HttpStatus httpStatus;

  public InvalidTokenException(HttpStatus httpStatus,String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
