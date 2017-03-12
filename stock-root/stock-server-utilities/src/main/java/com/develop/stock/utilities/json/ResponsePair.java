package com.develop.stock.utilities.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pritesh on 3/12/17.
 */
public class ResponsePair implements Serializable{

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buyDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sellDate;

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    @Override
    public String toString() {
        return "ResponsePair{" +
                "buyDate=" + buyDate +
                ", sellDate=" + sellDate +
                '}';
    }
}
