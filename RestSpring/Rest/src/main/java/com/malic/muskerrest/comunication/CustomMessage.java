package com.malic.muskerrest.comunication;

import java.io.Serializable;
import java.util.Date;

public class CustomMessage implements Serializable {
    private String messageId;
    private String message;
    private Date messageDate;

    public CustomMessage(String messageId, String message, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public CustomMessage() {
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessage() {
        return message;
    }

    public Date getMessageDate() {
        return messageDate;
    }
}
