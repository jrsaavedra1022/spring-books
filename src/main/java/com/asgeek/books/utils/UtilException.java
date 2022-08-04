package com.asgeek.books.utils;

/**
 * Class personalizada para manejo de errores
 * @author Jrsaavedra
 *
 */
public class UtilException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String code;

    public UtilException(String code) {
        super();
        this.code = code;
    }

    public UtilException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public UtilException(String message, String code) {
        super(message);
        this.code = code;
    }

    public UtilException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}