package com.twognation.ecommerce.exception;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 665497887647101359L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(final String message) {
        super(message);
    }

    public BadRequestException(final Throwable cause) {
        super(cause);
    }
}
