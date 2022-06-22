package com.hackathon.findtogether.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class PostSearchDto {
    private String keyword; // # 붙으면 해시태그, 안붙으면 제목
    private String sortingType; //LATEST, OLDEST
}
