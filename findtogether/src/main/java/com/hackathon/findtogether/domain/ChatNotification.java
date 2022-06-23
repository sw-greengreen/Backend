package com.hackathon.findtogether.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {

    @Id @GeneratedValue
    private String id;
    private String senderId;
    private String senderName;
}
