package com.develop.stock.ws.service;

import com.develop.stock.utilities.json.ResponsePair;
import com.develop.stock.ws.exception.InvalidTickerException;

/**
 * Service level interface carrying out basic service operations for StockWebService class.
 * Created by pritesh on 3/11/17.
 */
public interface StockService {

    /**
     * Returns buy/sell Date pair which returns maximum yield for a given stock ticker.
     * This method will look out for dates up to 6 months in past.
     * @param ticker
     */
    ResponsePair getDates(String ticker) throws InvalidTickerException;

}
