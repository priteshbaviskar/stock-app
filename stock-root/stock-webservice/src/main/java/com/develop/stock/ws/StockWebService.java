package com.develop.stock.ws;

import javax.ws.rs.core.Response;

/**
 * API to analyze a stock from its historical data.
 * Created by pritesh on 3/11/17.
 */
public interface StockWebService {

    /**
     * Webcall which returns buy/sell dates which would have returned maximum yield in past.
     * @param ticker
     * @return Standard HTTP web response.
     */
    Response getBuySellDates(String ticker);
}
