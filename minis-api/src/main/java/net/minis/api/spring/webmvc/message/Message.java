package net.minis.api.spring.webmvc.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Message {

    @Setter(AccessLevel.NONE)
    private long timestamp;

    private String path;

    private int status;

    private String statusText;

    private String message;

    public Message() {
        this.timestamp = System.currentTimeMillis();
    }

}
