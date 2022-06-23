package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.ChatMessage;

public interface ChatMessageConditionRepository {
    ChatMessage findBySenderIdAndReceiverId(String senderId, String receiverId);
}
