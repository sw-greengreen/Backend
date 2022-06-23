package com.hackathon.findtogether.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindUsernameDto {
    private String name;
    private String phone;
}
