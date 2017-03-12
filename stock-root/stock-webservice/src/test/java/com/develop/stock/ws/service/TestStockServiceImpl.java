package com.develop.stock.ws.service;

import com.develop.stock.ws.exception.InvalidTickerException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by pritesh on 3/11/17.
 */
public class TestStockServiceImpl {

    private StockService service;
    @Before
    public void setup() {
        service = new StockServiceImpl();

    }

    @Test
    public void testValidateTicker() throws InvalidTickerException {
        service.getDates("AAPL");
    }
}
