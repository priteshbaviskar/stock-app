package com.develop.stock.ws;

import com.develop.stock.ws.exception.InvalidTickerException;

import javax.ws.rs.core.Response;

/**
 * Created by pritesh on 3/11/17.
 */
public interface StockWebService {

    Response getBuySellDates(String ticker) throws InvalidTickerException;
}
