package com.example.protoFoodV2.exceptions;

public class RenderableExceptionGenerator {
    public static RenderableException generateInvalidParameterException(String name) {
        return new RenderableException(ErrorCode.InvalidParameter, String.format("Invalid parameter %s", name));
    }

    public static RenderableException generateInvalidParameterException(String name, String message) {
        return new RenderableException(ErrorCode.InvalidParameter, String.format("Invalid parameter %s. %s", name, message));
    }

    public static RenderableException generateInternalServerErrorException() {
        return new RenderableException(ErrorCode.InternalServerError, ErrorCode.InternalServerError.getMessage());
    }

    public static RenderableException generateNotAuthorizedException(String message) {
        return new RenderableException(ErrorCode.NotAuthorizedOrNotFound, message);
    }

    public static RenderableException generateLimitExceededException(String message) {
        return new RenderableException(ErrorCode.LimitExceeded,
                ErrorCode.LimitExceeded.getMessage() + " " + message);
    }

    public static RenderableException generateIncorrectStateException() {
        return new RenderableException(ErrorCode.IncorrectState, ErrorCode.IncorrectState.getMessage());
    }
}
