package com.develop.stock.ws;

import com.develop.stock.utilities.json.ResponseJson;
import com.develop.stock.utilities.json.ResponsePair;
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
@Path("/stock-analysis")
public class StockWebServiceImpl implements StockWebService {


    @Autowired
    private StockService stockService;
    @Autowired
    private ResponseJson responseJson;

    private static Log log = LogFactory.getLog(StockWebServiceImpl.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{ticker}/maxyield")
    public Response getBuySellDates(@PathParam("ticker") String ticker)  {

        String response;
        try {
            ResponsePair pair = stockService.getDates(ticker);
            if(pair != null) {
                log.info("Max yielding buy/sell dates for: "+ticker+ " are" + pair.toString());
                response = responseJson.addStatus(true).addResponseMessage("These dates would yield maximum returns").build(pair);
                return Response.ok().entity(response).build();
            }else {
                log.info(" No historical data available for: "+ticker);
                response = responseJson.addStatus(false).addResponseMessage("No historical data available for stock").build(pair);
                return Response.status(Response.Status.NOT_FOUND).entity(response).build();
            }
        } catch (InvalidTickerException e) {
            log.info(" Invalid ticker name: "+ticker);
            response = responseJson.addStatus(false).addResponseMessage("Ticker name is not valid.").build(null);
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }
    }

    public void setResponseJson(ResponseJson responseJson) {
        this.responseJson = responseJson;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
}
