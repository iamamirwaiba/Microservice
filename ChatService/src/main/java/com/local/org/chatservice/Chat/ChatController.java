package com.local.org.ChatService.Chat;


import com.local.org.ChatService.ChatRoom.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

    private SimpMessagingTemplate messagingTemplate;

    private ChatService chatService;

    private ChatRoomService chatRoomService;

    @Autowired
    ChatController(SimpMessagingTemplate messagingTemplate,ChatService chatService ,ChatRoomService chatRoomService){
        this.messagingTemplate=messagingTemplate;
        this.chatService=chatService;
        this.chatRoomService=chatRoomService;
    }



    @MessageMapping("/chat")
    public void processMessage(@Payload ChatDTO dto){


    }


    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatEntity sendMessage(@Payload ChatEntity chatMessage) {
        return chatMessage;
    }
}
