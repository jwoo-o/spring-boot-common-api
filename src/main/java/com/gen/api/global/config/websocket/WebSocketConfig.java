package com.gen.api.global.config.websocket;

import com.gen.api.global.config.handler.StompHandler;
import com.gen.api.global.interceptor.HttpHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-17
 * Time: 오전 11:22
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final StompHandler stompHandler;

    @Value("${endpoint}")
    private String endpoint;
    @Value("${destination.prefix}")
    private String destinationPrefix;
    @Value("${stomp.broker.relay}")
    private String stompBrokerRelay;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.afterPropertiesSet();
        long[] heartbeat = {0, 60000};

        config.enableSimpleBroker(stompBrokerRelay)
                .setTaskScheduler(taskScheduler)
                .setHeartbeatValue(heartbeat);
       /** config.enableStompBrokerRelay(stompBrokerRelay)
                                                        .setRelayHost("192.168.100.61")
                                                        .setRelayPort(61613)
                                                        .setSystemLogin("guest")
                                                        .setSystemPasscode("guest");*/

        config.setApplicationDestinationPrefixes(destinationPrefix);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint(endpoint).setAllowedOrigins("*").addInterceptors(new HttpHandshakeInterceptor());
        registry.addEndpoint(endpoint).setAllowedOrigins("*").addInterceptors(new HttpHandshakeInterceptor()).withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(160 * 64 * 1024);    // Max incoming message size, default : 64 * 1024
        registration.setSendTimeLimit(20 * 10000);            // default : 10 * 10000
        registration.setSendBufferSizeLimit(10 * 512 * 1024); // Max outgoing buffer size, default : 512 * 1024
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompHandler);

    }

}
