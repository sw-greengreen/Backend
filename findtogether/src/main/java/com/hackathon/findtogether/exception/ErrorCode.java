package com.hackathon.findtogether.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, false,"권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, false,"현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND,false, "해당 유저 정보를 찾을 수 없습니다"),
    POST_NOT_FOUND(NOT_FOUND,false,"해당 게시물을 찾을 수 없습니다"),
    MESSAGE_NOT_FOUND(NOT_FOUND,false, "해당 메시지 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND,false, "로그아웃 된 사용자입니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, false,"데이터가 이미 존재합니다"),
    ;
    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String detail;
}
