package io.github.dunwu.spring.websocket.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Bean
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 用来注册WebSocket Server实现类，第二个参数是访问WebSocket的地址
        registry.addHandler(myHandler(), "/ws").addInterceptors(new HandShake());
        // 允许客户端使用SockJS
        registry.addHandler(myHandler(), "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
    }

}
