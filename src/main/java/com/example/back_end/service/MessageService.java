package com.example.back_end.service;

import com.example.back_end.model.Conversation;
import com.example.back_end.model.Message;
import com.example.back_end.model.Traveler;
import com.example.back_end.repository.ConversationRepository;
import com.example.back_end.repository.MessageRepository;
import com.example.back_end.repository.TravelerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private TravelerRepo userRepository;

    public Message sendMessage(Message message) {
        Conversation conversation = conversationRepository.findById(message.getConversation().getId()).orElse(null);
        if (conversation == null) {
            throw new IllegalArgumentException("Conversation not found");
        }

        Traveler sender = userRepository.findById(message.getSender().getId()).orElse(null);
        if (sender == null) {
            throw new IllegalArgumentException("Sender not found");
        }

        Traveler recipient = conversation.getUser1().equals(sender) ? conversation.getUser2() : conversation.getUser1();

        //Message message = new Message();
        //message.setSender(sender);
        message.setRecipient(recipient);
        //message.setConversation(conversation);
        //message.setContent(message.getContent());
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getChatMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}


