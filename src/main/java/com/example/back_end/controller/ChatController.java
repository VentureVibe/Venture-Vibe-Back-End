package com.example.back_end.controller;

import com.example.back_end.model.Conversation;
import com.example.back_end.model.Message;
import com.example.back_end.service.ConversationService;
import com.example.back_end.service.MessageService;
import com.example.back_end.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private TravelerService userService;

    @PostMapping("/start")
    public Conversation startConversation(@RequestBody Conversation conversation) {
        return conversationService.startConversation(conversation);
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/messages/{conversationId}")
    public List<Message> getMessages(@PathVariable Long conversationId) {
        return messageService.getChatMessages(conversationId);
    }

    @GetMapping("/conversations/{user1Id}/{user2Id}")
    public Conversation getConversation(@PathVariable String user1Id, @PathVariable String user2Id) {
        return conversationService.getConversationByUser1IdAndUser2Id(user1Id, user2Id);
    }


    @GetMapping("/conversations/{userId}")
    public List<Conversation> getConversations(@PathVariable String userId) {
        return conversationService.getConversations(userId);
    }
}


