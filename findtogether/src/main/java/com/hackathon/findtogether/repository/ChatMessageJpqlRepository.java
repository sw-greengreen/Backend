package com.hackathon.findtogether.repository;

import com.hackathon.findtogether.domain.ChatMessage;
import com.hackathon.findtogether.domain.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class ChatMessageJpqlRepository implements ChatMessageConditionRepository {

    private final EntityManager em;

    @Override
    public ChatMessage findBySenderIdAndReceiverId(String senderId, String receiverId) {
        String jpql = "select cm from ChatMessage cm where cm.senderId = :senderId and cm.receiverId = :receiverId";

        return em.createQuery(jpql, ChatMessage.class)
                .setParameter("senderId", senderId)
                .setParameter("receiverId", receiverId)
                .getSingleResult();
    }

    //    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
//        Query query = new Query(
//                Criteria
//                        .where("senderId").is(senderId)
//                        .and("recipientId").is(recipientId));
//        Update update = Update.update("status", status);
//        mongoOperations.updateMulti(query, update, ChatMessage.class);
//    }
}
