package org.codewarrior.rpg.api.exception;

public class BeanNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8148639873798427287L;

    public BeanNotFoundException() {
        super();
    }

    public BeanNotFoundException(String message) {
        super(message);
    }

    public BeanNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
