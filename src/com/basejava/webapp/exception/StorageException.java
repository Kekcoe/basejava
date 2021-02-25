package com.basejava.webapp.exception;

public class StorageException extends RuntimeException{
    private final String uuid;

    public StorageException(String warningMessage,String uuid) {
        super(warningMessage);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
