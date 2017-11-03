package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        template.convertAndSendToUser("12345","/message",new Greeting("服务器主动回复！12345"));
        return new Greeting("Hello, " + message.getName() + "!");
    }

    //调用domax将会服务器主动发消息
    public void domax(){
        //template.convertAndSendToUser();
        template.convertAndSend("/topic/greetings",new Greeting("服务器主动回复！"));
    }

    @MessageMapping("/message")
    @SendToUser("/topic/greetings")
    public Greeting greetingone(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("-------------------->"+message.getName()+"-----------in");
        //template.convertAndSendToUser("user","/topic/greetings",new Greeting("服务器主动回复！1234"));

        return new Greeting("I just server for  " + message.getName() + "!");
    }

    @MessageMapping("/sendOne1")
    @SendTo("/abc/sendOne")
    public Greeting greetingone12(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("-------------------->"+message.getName()+"in");
        template.convertAndSend("/abc/sendOne",new Greeting("服务器主动回复！"));
        return new Greeting("I just server for  " + message.getName() + "!");
    }
}
