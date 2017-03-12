package com.develop.stock.ws.service;

import com.develop.stock.utilities.json.ResponsePair;
import com.develop.stock.ws.exception.InvalidTickerException;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pritesh on 3/11/17.
 */
@Service
public class StockServiceImpl implements StockService {

    private static Log log = LogFactory.getLog(StockServiceImpl.class);

    @Override
    public ResponsePair getDates(String ticker) throws InvalidTickerException {

        List<HistoricalQuote> historicalQuotes = getHistoricalData(ticker);
        if(historicalQuotes != null && !historicalQuotes.isEmpty()) {
            return findDatesForMaximumProfit(historicalQuotes);
        }
        return null;
    }


    public List<HistoricalQuote> getHistoricalData(String ticker) throws InvalidTickerException {

        if(ticker != null && !ticker.isEmpty()){
            try {
                Calendar fromCal = Calendar.getInstance();
                Calendar toCal = Calendar.getInstance();
                fromCal.add(Calendar.MONTH, -6); // go back six months by default.
                Stock stockData = YahooFinance.get(ticker,fromCal,toCal, Interval.DAILY);
                return stockData.getHistory();

            } catch (IOException e) {
                log.debug("Something went wrong while fetching historical data for "+ ticker+" " + e.getMessage());
                throw new InvalidTickerException("Ticker is invalid. Please check your input");
            }
        }
        return null;
    }

    private ResponsePair findDatesForMaximumProfit(List<HistoricalQuote> historicalQuotes) {

        historicalQuotes=  Lists.reverse(historicalQuotes);
        HistoricalQuote minQuote = historicalQuotes.get(0);
        int count = 0;
        for(int i = 1; i < historicalQuotes.size(); i++) {
            if(historicalQuotes.get(i).getLow().compareTo(minQuote.getLow()) < 0) {
                minQuote = historicalQuotes.get(i);
                count = i;
            }
        }

        HistoricalQuote maxQuote = new HistoricalQuote();
        maxQuote.setHigh(BigDecimal.valueOf(Integer.MIN_VALUE));
        for(int j= count; j < historicalQuotes.size(); j++) {
            if(historicalQuotes.get(j).getHigh().compareTo(maxQuote.getHigh()) > 0) {
                maxQuote = historicalQuotes.get(j);
            }
        }

        // there will always be a pair since we have daily highs and lows for a stock.
        // if the stock goes south for 6 months straight, we still have the first low-high pair
        // yielding the maximum profit.
        ResponsePair pair = new ResponsePair();
        pair.setBuyDate(minQuote.getDate().getTime());
        pair.setSellDate(maxQuote.getDate().getTime());
        return pair;

    }


}
