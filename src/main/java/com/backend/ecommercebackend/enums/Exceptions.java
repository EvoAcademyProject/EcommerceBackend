package com.backend.ecommercebackend.enums;

import com.backend.ecommercebackend.model.consts.ExceptionMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Exceptions {
  INVALID_TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST, ExceptionMessage.TOKEN_IS_INVALID_MSG);

  private final HttpStatus httpStatus;
  private final String message;
}
