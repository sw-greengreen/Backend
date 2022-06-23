package com.hackathon.findtogether.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private String id;
    private String chatId;
    private String senderId; //송신자
    private String receiverId; //수신자
}
