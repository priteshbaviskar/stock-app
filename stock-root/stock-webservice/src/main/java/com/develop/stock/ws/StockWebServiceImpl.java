package com.develop.stock.ws;

import com.develop.stock.ws.exception.InvalidTickerException;
import com.develop.stock.ws.service.StockService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by pritesh on 3/11/17.
 */

@Component
@Path("/stock-service")
public class StockWebServiceImpl implements StockWebService {


    @Autowired
    private StockService stockService;

    private static Log log = LogFactory.getLog(StockWebServiceImpl.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("stock/buySellDates/{ticker}")
    public Response getBuySellDates(@PathParam("ticker") String ticker) throws InvalidTickerException {

        log.info("Fetching buy/sell dates for: "+ticker);


        return null;
    }


    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
}
