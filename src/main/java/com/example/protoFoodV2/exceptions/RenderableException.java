package com.example.protoFoodV2.exceptions;

import lombok.NonNull;

public class RenderableException extends RuntimeException {
    private final @NonNull ErrorCode errorCode;

    public RenderableException(@NonNull ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
