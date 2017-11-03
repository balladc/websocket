package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");//这句表示在topic和user这两个域上可以向客户端发消息；
        //config.setUserDestinationPrefix("/user");                  //这句表示给指定用户发送（一对一）的主题前缀是“/user/”;
        config.setApplicationDestinationPrefixes("/app");      //这句表示客户端向服务端发送时的主题上面需要加"/app"作为前缀；
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS(); //这个和客户端创建连接时的url有关，后面在客户端的代码中可以看到。
    }

}