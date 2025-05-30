package com.example.demo.core;

import java.sql.Timestamp;
import java.util.UUID;

public class Event {
    private UUID id;
    private Timestamp timestamp;
    private String type;
    private Object data;
    public Event(UUID id, Timestamp timestamp, String type, Object data) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.data = data;
    }
    public UUID getId() {
        return id;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public String getType() {
        return type;
    }
    public Object getData() {
        return data;
    }
}
