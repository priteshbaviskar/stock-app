package com.develop.stock.ws;

import com.develop.stock.utilities.json.ResponseJson;
import com.develop.stock.ws.exception.InvalidTickerException;
import com.develop.stock.ws.service.StockService;
import org.apache.commons.lang3.tuple.Pair;
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
    @Autowired
    private ResponseJson responseJson;

    private static Log log = LogFactory.getLog(StockWebServiceImpl.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("stock/buySellDates/{ticker}")
    public Response getBuySellDates(@PathParam("ticker") String ticker)  {

        String response;
        log.info("Fetching buy/sell dates for: "+ticker);
        try {
            Pair pair = stockService.getDates(ticker);
            if(pair != null) {
                response = responseJson.addStatus(true).addResponseMessage("success").build(pair);
            }
        } catch (InvalidTickerException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setResponseJson(ResponseJson responseJson) {
        this.responseJson = responseJson;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
}
