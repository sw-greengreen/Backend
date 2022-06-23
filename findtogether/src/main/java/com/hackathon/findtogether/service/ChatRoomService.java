package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.ChatRoom;
import com.hackathon.findtogether.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String receiverId, boolean createIfNotExist) {

        return chatRoomRepository.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExist) {
                        return Optional.empty();
                    }
                    var chatId = String.format("%s_%s", senderId, receiverId);

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .receiverId(receiverId)
                            .build();

                    ChatRoom receiverSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(receiverId)
                            .receiverId(senderId)
                            .build();

                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(receiverSender);

                    return Optional.of(chatId);
                });
    }
}
