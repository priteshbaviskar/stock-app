package com.develop.stock.ws.exception;

/**
 * Created by pritesh on 3/11/17.
 */
public class InvalidTickerException extends Exception {

    public InvalidTickerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
