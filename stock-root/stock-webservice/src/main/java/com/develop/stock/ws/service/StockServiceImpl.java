package com.develop.stock.ws.service;

import com.develop.stock.ws.exception.InvalidTickerException;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class StockServiceImpl implements StockService {

    private static Log log = LogFactory.getLog(StockServiceImpl.class);

    @Override
    public Pair getDates(String ticker) throws InvalidTickerException {

        List<HistoricalQuote> historicalQuotes = getHistoricalData(ticker);
        if(historicalQuotes != null && !historicalQuotes.isEmpty()) {
            return findDatesForMaximumProfit(historicalQuotes);
        }

        return null;
    }


    private List<HistoricalQuote> getHistoricalData(String ticker) throws InvalidTickerException {

        if(ticker != null && !ticker.isEmpty()){
            try {
                Calendar fromCal = Calendar.getInstance();
                Calendar toCal = Calendar.getInstance();
                fromCal.add(Calendar.MONTH, -6); // go back six months by default.

                Stock stockData = YahooFinance.get(ticker,fromCal,toCal, Interval.MONTHLY);

                return stockData.getHistory();

            } catch (IOException e) {
                log.debug("Something went wrong while fetching historical data for "+ ticker+" " + e.getMessage());
                throw new InvalidTickerException("Ticker is invalid. Please check your input");
            }
        }
        return null;
    }

    private Pair findDatesForMaximumProfit(List<HistoricalQuote> historicalQuotes) {

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


        return Pair.of(minQuote.getDate(), maxQuote.getDate());




//        BigDecimal maxProfit = new BigDecimal(0);
//        BigDecimal maxOverAllProfit = new BigDecimal(0);
//        //BigDecimal minSoFar = historicalQuotes.get(0).getLow();
//        HistoricalQuote minHistoricalQuoteSoFar = historicalQuotes.get(0);
//
//        BigDecimal dailyMax = new BigDecimal(0);
//        Calendar buyDate,sellDate;
//
//        BigDecimal minLowValue = BigDecimal.valueOf(Integer.MAX_VALUE);
//        for(HistoricalQuote quote : historicalQuotes) {
//            if(quote.getLow().compareTo(minLowValue) < 0) {
//                minLowValue = quote.getLow();
//            }
//        }






//        for(int i = 0 ; i < historicalQuotes.size() ; i) {

//            if (prices.length == 0) {
//                return 0 ;
//            }
//            int max = 0 ;
//            int sofarMin = prices[0] ;
//            for (int i = 0 ; i < prices.length ; ++i) {
//                if (prices[i] > sofarMin) {
//                    max = Math.max(max, prices[i] - sofarMin) ;
//                } else{
//                    sofarMin = prices[i];
//                }
//            }
//            return  max ;
//
//            if(historicalQuotes.get(i).getHigh().compareTo(minHistoricalQuoteSoFar.getLow()) > 0) {
//
//                BigDecimal currentProfit = historicalQuotes.get(i).getHigh().subtract(minHistoricalQuoteSoFar.getLow());
//
//                if(currentProfit.compareTo(maxProfit) > 0) {
//                     maxProfit = currentProfit;
//                     buyDate =  minHistoricalQuoteSoFar.getDate();
//                     sellDate = historicalQuotes.get(i).getDate();
//                 } else {
//                     maxProfit =
//                 }
//            }














//            //check out for today's profit.
//            dailyMax = historicalQuotes.get(i).getHigh().subtract(historicalQuotes.get(i).getClose());
//            //check out for profit between yest and today- add it with currentMaxProfit so far.
//            maxCurrentProfit = maxCurrentProfit.add(historicalQuotes.get(i+1).getHigh().subtract(historicalQuotes.get(i).getLow()));
//
//            // whoever is the max between 2 is current max profit so far.
//            //maxCurrentProfit = maxCurrentProfit.compareTo(dailyMax) > 0 ? maxCurrentProfit : dailyMax;
//            if(maxCurrentProfit.compareTo(dailyMax) > 0) {
//                //currentMax is still local max
//                buyDate = historicalQuotes.get(i).getDate();
//                sellDate = historicalQuotes.get(i+1).getDate();
//
//            }else {
//                //dailyMax wins
//                buyDate = historicalQuotes.get(i).getDate();
//                sellDate = historicalQuotes.get(i).getDate();
//            }
//
//           // maxOverAllProfit = maxOverAllProfit.compareTo(maxCurrentProfit) > 0 ? maxOverAllProfit : maxCurrentProfit;
//            if(maxOverAllProfit.compareTo(maxCurrentProfit) > 0){
//
//            }


    }


}
