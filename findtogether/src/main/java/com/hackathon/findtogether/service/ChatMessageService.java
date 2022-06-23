package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.ChatMessage;
import com.hackathon.findtogether.domain.MessageStatus;
import com.hackathon.findtogether.exception.CustomException;
import com.hackathon.findtogether.exception.ErrorCode;
import com.hackathon.findtogether.repository.ChatMessageJpqlRepository;
import com.hackathon.findtogether.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;
    private final ChatMessageJpqlRepository chatMessageJpqlRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        return chatMessageRepository.countBySenderIdAndReceiverIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
        var chatId = chatRoomService.getChatId(senderId, receiverId, false);

        var messages = chatId.map(cId -> chatMessageRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if (messages.size() > 0) {
            updateStatuses(senderId, receiverId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public ChatMessage findById(String id) {
        return chatMessageRepository.findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return chatMessageRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new CustomException(ErrorCode.MESSAGE_NOT_FOUND));
//                        new IllegalArgumentException("can't find message (" + id + ")"));
    }

//    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
//        Query query = new Query(
//                Criteria
//                        .where("senderId").is(senderId)
//                        .and("recipientId").is(recipientId));
//        Update update = Update.update("status", status);
//        mongoOperations.updateMulti(query, update, ChatMessage.class);
//    }

    @Transactional
    public void updateStatuses(String senderId, String receiverId, MessageStatus status) {
        ChatMessage chatMessage = chatMessageJpqlRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        chatMessage.setStatus(status); //변경감지
    }
}
