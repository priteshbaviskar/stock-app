package com.develop.stock.utilities.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by pritesh on 3/11/17.
 */
public class Stock implements Serializable {

    private String stockName;
    private String stockTicker;
    private BigDecimal currentValue;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockTicker() {
        return stockTicker;
    }

    public void setStockTicker(String stockTicker) {
        this.stockTicker = stockTicker;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockName='" + stockName + '\'' +
                ", stockTicker='" + stockTicker + '\'' +
                ", currentValue=" + currentValue +
                '}';
    }
}
