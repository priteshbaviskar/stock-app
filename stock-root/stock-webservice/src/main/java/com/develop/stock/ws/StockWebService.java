package com.develop.stock.ws;

import javax.ws.rs.core.Response;

/**
 * Created by pritesh on 3/11/17.
 */
public interface StockWebService {

    Response getBuySellDates(String ticker);
}
