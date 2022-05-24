package com.malic.muskerrest.api;

import com.malic.muskerrest.comunication.CustomMessage;
import com.malic.muskerrest.comunication.MQConfig;
import com.malic.muskerrest.entities.Constantes;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(path = "/rabbit")
public class MessagePublisher{

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/publish")
    public String publishMassage(@RequestBody(required = false) CustomMessage constantes){
        if (constantes == null) constantes = new CustomMessage();
        constantes.setMessageId(UUID.randomUUID().toString());
        constantes.setMessageDate(new Date());
        template.convertAndSend(MQConfig.EXCHANGE_DATOS,
                "", constantes);
        return "Message Published";
    }

}
