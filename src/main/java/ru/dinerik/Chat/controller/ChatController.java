package ru.dinerik.Chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dinerik.Chat.entity.ChatMessage;

@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @MessageMapping("/send_message")        // метод контроллера должен принимать сообщения, которые были отправлены на префикс /chat_in/send_message
    public void messageReceiver(@Payload ChatMessage chatMessage) {
        logger.info("New message: " + chatMessage);
        template.convertAndSendToUser(chatMessage.getReceiver(), "/chat_out/receive_message", chatMessage);
    }
}