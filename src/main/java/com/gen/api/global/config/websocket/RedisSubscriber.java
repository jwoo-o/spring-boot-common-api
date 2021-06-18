package com.gen.api.global.config.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gen.bluexray.client.websocket.dto.WebSocketMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    /**
     * Redis에서 메시지가 발행(publish)되면 대기하고 있던 Redis Subscriber가 해당 메시지를 받아 처리한다.
     */
    public void sendMessage(String publishMessage) {
        try {
            // ChatMessage 객채로 맵핑
            WebSocketMessage message = objectMapper.readValue(publishMessage, WebSocketMessage.class);
            // 채팅방을 구독한 클라이언트에게 메시지 발송
            messagingTemplate.convertAndSend("/sub/" + message.getPcId(), message);

            log.debug("__________________sendMessage");
            log.debug(message.toString());
            log.debug("__________________sendMessage");

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }
}
