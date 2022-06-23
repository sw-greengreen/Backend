package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.ChatMessage;
import com.hackathon.findtogether.domain.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    long countBySenderIdAndReceiverIdAndStatus(
            String senderId, String receiverId, MessageStatus status);

    List<ChatMessage> findByChatId(String id);

}
