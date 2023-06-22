package com.example.protoFoodV2.exceptions;

public class DalException extends RuntimeException {

    public DalException(Exception ex) {
        super(ex);
    }

    public DalException(String msg) {
        super(msg);
    }

    public DalException(String msg, Exception ex) {
        super(msg, ex);
    }
}
