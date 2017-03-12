package com.develop.stock.ws;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

public class App extends ResourceConfig {
	
	public App() {
		//application resources
		register(StockWebServiceImpl.class);
		
		//bridge between JAX-rx and spring
		register(RequestContextFilter.class);
	}

}
