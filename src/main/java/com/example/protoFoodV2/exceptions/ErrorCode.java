package com.example.protoFoodV2.exceptions;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    CannotParseRequest(HttpStatus.BAD_REQUEST, "Incorrectly formatted request. Please refer to the documentation for help."),
    InvalidParameter(HttpStatus.BAD_REQUEST, "Invalid parameter."),
    LimitExceeded(HttpStatus.BAD_REQUEST, "Limit Exceeded."),
    InternalServerError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error."),
    NotFound(HttpStatus.NOT_FOUND, "Resource not found."),
    NotAuthenticated(HttpStatus.UNAUTHORIZED, "Unauthorized."),
    NotAllowed(HttpStatus.FORBIDDEN, "Forbidden."),
    NotAuthorizedOrNotFound(HttpStatus.NOT_FOUND, "Authorization failed ot requested resource not found."),
    IncorrectState(HttpStatus.CONFLICT, "Incorrect state");

    @NonNull
    private final HttpStatus status;
    private final String message;

    ErrorCode(@NonNull HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
