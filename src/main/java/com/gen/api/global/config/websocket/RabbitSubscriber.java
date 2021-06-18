package com.gen.api.global.config.websocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-03
 * Time: 오전 11:03
 */
@Slf4j
@RequiredArgsConstructor
public class RabbitSubscriber {
/**
    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;




    @RabbitListener(queues = "realTime")
    public void sendMessage(Object publishMessage) {
        try {
            System.out.println("rabbit : "+publishMessage.toString());
            // ChatMessage 객채로 맵핑
            //WebSocketMessage message = objectMapper.readValue(publishMessage, WebSocketMessage.class);
            WebSocketMessage message = (WebSocketMessage) publishMessage;
             //채팅방을 구독한 클라이언트에게 메시지 발송
            messagingTemplate.convertAndSend("/realTime/" + message.getPcId(), message);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }*/
}
