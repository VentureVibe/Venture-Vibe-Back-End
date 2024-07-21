package com.example.back_end.service;

import com.example.back_end.model.Conversation;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.ConversationRepository;
import com.example.back_end.repository.TravelerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private TravelerRepo userRepository;

    public Conversation startConversation(Conversation conversation) {
        Traveler user1 = userRepository.findById(conversation.getUser1().getId()).orElse(null);
        Traveler user2 = userRepository.findById(conversation.getUser2().getId()).orElse(null);

        if (user1 == null || user2 == null) {
            throw new IllegalArgumentException("User not found");
        }

        //Conversation conversation = new Conversation();
        conversation.setUser1(user1);
        conversation.setUser2(user2);
        conversation.setStartTime(LocalDateTime.now());

        return conversationRepository.save(conversation);
    }

    public Conversation getConversation(Long conversationId) {
        return conversationRepository.findById(conversationId).orElse(null);
    }
}


