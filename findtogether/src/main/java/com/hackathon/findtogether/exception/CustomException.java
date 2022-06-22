package com.hackathon.findtogether.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 전역 Exception
 */

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
}
