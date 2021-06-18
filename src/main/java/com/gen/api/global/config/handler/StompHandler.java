package com.gen.api.global.config.handler;

import com.gen.bluexray.client.websocket.service.WebSocketChatService;
import com.gen.bluexray.client.websocket.service.WebSocketConnectService;
import com.gen.api.global.exception.AlreadyAccessException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-26
 * Time: 오전 10:10
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {


    private final WebSocketConnectService webSocketConnectService;

    private final WebSocketChatService webSocketChatService;

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        log.debug("___________preSend");
        log.debug(message.toString());
        log.debug(accessor.toString());
        log.debug("___________preSend");


        try {
            if (StompCommand.CONNECT == accessor.getCommand()) { // websocket 연결요청
                String pcId = accessor.getFirstNativeHeader("login");
                String sessionId = (String) message.getHeaders().get("simpSessionId");

                if (pcId != null) {
                    log.info("CONNECT AGENT - {}, {}", pcId, sessionId);
                } else {
                    String adminId = accessor.getFirstNativeHeader("adminId");
                    log.info("CONNECT ADMIN - {}, {}", adminId, sessionId);
                }


            } else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {

                String sessionId = (String) message.getHeaders().get("simpSessionId");
                log.info("SUBSCRIBE  - {}",sessionId);
                /** header정보에서 구독 destination정보를 얻고, 구독중인 /sub/pcId를 추출한다.*/
                String pcId = Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("pcId");
                /**  접속한 클라이언트 sessionId를 pcId와 맵핑해 놓는다.
                //String sessionId = (String) message.getHeaders().get("simpSessionId");

                //webSocketService.enter(sessionId,pcId);
                //log.info("SUBSCRIBE {}, {}", sessionId, pcId);
                 */

                webSocketConnectService.sendSubscribe(pcId.replace("/sub/", ""));


            } else if (StompCommand.UNSUBSCRIBE == accessor.getCommand()) {

                String sessionId = (String) message.getHeaders().get("simpSessionId");
                log.info("UNSUBSCRIBE  - {}",sessionId);

            } else if (StompCommand.DISCONNECT == accessor.getCommand()) { // Websocket 연결 종료
                String sessionId = (String) message.getHeaders().get("simpSessionId");
                String pcId = webSocketConnectService.updDisConnect(sessionId);
                if (pcId != null) {
                    log.info("DISCONNECT {}, {}", sessionId, pcId);
                }else {
                    log.info("DISCONNECT {}", sessionId);

                    webSocketChatService.chatExit(sessionId);
                }
            }
        } catch (AlreadyAccessException e) {
            e.printStackTrace();
        }

        return message;
    }

}
