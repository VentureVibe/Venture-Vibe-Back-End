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

    @GetMapping("/messages")
    public List<Message> getMessages(@RequestParam Long conversationId) {
        return messageService.getChatMessages(conversationId);
    }
}


