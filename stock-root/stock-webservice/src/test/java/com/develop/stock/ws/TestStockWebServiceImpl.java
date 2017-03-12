package com.develop.stock.ws;

import com.develop.stock.ws.service.StockServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

/**
 * Created by pritesh on 3/12/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:TestApplication-context.xml"})
public class TestStockWebServiceImpl extends JerseyTest{

    @InjectMocks
    private StockServiceImpl stockService;

    private StockWebService stockWebService;

    @Before
    public void setup() {
        stockWebService = new StockWebServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHappyPath() throws JSONException {

        Response response = target("/stock-analysis/AAPL/maxyield").request().get();
        Assert.assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        JSONAssert.assertEquals("{\n" +
                "  \"responseMessage\" : \"These dates would yield maximum returns\",\n" +
                "  \"isSuccess\" : true,\n" +
                "  \"object\" : {\n" +
                "    \"responsepair\" : {\n" +
                "      \"buyDate\" : \"2016-09-12\",\n" +
                "      \"sellDate\" : \"2017-03-02\"\n" +
                "    }\n" +
                "  }\n" +
                "}", json,false);
    }

    @Test
    public void testInvalidTicker() throws JSONException{
        Response response = target("/stock-analysis/SKDJFH/maxyield").request().get();
        Assert.assertEquals(400, response.getStatus());
        String json = response.readEntity(String.class);
        JSONAssert.assertEquals("{\n" +
                "  \"responseMessage\" : \"Ticker name is not valid.\",\n" +
                "  \"isSuccess\" : false,\n" +
                "  \"object\" : { }\n" +
                "}",json,false);
    }

    @Override
    protected Application configure() {
        ResourceConfig rc = new ResourceConfig(StockWebServiceImpl.class);
        rc.property("contextConfigLocation", "classpath*:TestApplication-context.xml");
        return rc;

    }
}
