package com.backend.ecommercebackend.exception;


import org.springframework.http.HttpStatus;

public class InvalidTokenException extends RuntimeException{
  private final HttpStatus httpStatus;

  public InvalidTokenException(HttpStatus httpStatus,String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
