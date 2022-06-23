package com.hackathon.findtogether.dto.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class PostSearchDto {
    private String postType; // LOST, DISCOVERY
    private String keyword; // 해시태그 or 제목
    private String sortingType; // LATEST, OLDEST, WAITING, COMPLETION
}
