package com.rm2pt.rapidood.transform;

public class TransformException extends RuntimeException {

    public TransformException(String message) {
        super(message);
    }

    public TransformException(String message, Throwable cause) {
        super(message, cause);
    }
}
