package com.malic.musker.comunication;

import com.malic.musker.entities.Avistamiento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@Service
public class MessagePublisher {

    @Autowired
    private RabbitTemplate template;

    public void publishMessage(@RequestBody(required = false) Avistamiento avistamiento){
        if (avistamiento == null) avistamiento = new Avistamiento();
        template.convertAndSend(MQConfig.EXCHANGE_DATOS,
                "", avistamiento);
    }

}
