package com.hackathon.findtogether.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    @Id @GeneratedValue
    @Column(name = "chat_message_id")
    private String id;

    private String chatId;
    private String senderId; //송신자
    private String receiverId; //수신자
    private String senderName;
    private String receiverName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}
