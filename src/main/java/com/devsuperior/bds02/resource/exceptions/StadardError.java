package com.devsuperior.bds02.resource.exceptions;

import java.time.Instant;

public class StadardError {

    private Instant timesTamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StadardError(Instant timesTamp, Integer status, String error, String message, String path) {
        this.timesTamp = timesTamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimesTamp() {
        return timesTamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
