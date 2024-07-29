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
    private ConversationService conversationService;

    @Autowired
    private TravelerRepo userRepository;

    public Message sendMessage(Message message) {
        // Fetch the conversation between the sender and recipient, if it exists
        Conversation conversation = conversationService.getConversationByUser1IdAndUser2Id(
                message.getSender().getId(), message.getRecipient().getId()
        );

        // If no conversation exists, check for the reverse order
        if (conversation == null) {
            conversation = conversationService.getConversationByUser1IdAndUser2Id(
                    message.getRecipient().getId(), message.getSender().getId()
            );

            // If still no conversation exists, create a new one
            if (conversation == null) {
                Traveler sender = userRepository.findById(message.getSender().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

                Traveler recipient = userRepository.findById(message.getRecipient().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

                // Create new conversation
                Conversation newConversation = new Conversation();
                newConversation.setUser1(sender);
                newConversation.setUser2(recipient);
                newConversation.setLastMessage(message.getContent());
                newConversation.setStartTime(LocalDateTime.now());
                newConversation.setReadByUser2(false);
                conversation = conversationService.startConversation(newConversation);
            }
        }

        // Fetch sender from repository (this check is redundant if you have already checked before)
        Traveler sender = userRepository.findById(message.getSender().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        // Determine the recipient from the conversation
        Traveler recipient = conversation.getUser1().equals(sender) ? conversation.getUser2() : conversation.getUser1();

        // Set message details
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setConversation(conversation);
        message.setTimestamp(LocalDateTime.now());

        // Update the last message in the conversation
        conversation.setLastMessage(message.getContent());

        // Save the message
        return messageRepository.save(message);
    }



    public List<Message> getChatMessages(Long conversationId) {
        return messageRepository.findByConversationId(conversationId);
    }
}
