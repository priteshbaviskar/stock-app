package com.develop.stock.utilities.json;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Json {
	
	private Log log = LogFactory.getLog(Json.class);


	protected String writeAsJson(Map<String, Object> responseMap) {

        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		try {
			return ow.writeValueAsString(responseMap);
		} catch (Exception ex) {
			log.info("Json threw an exception: " + ex.getMessage());
		}
		return null;
	}


    public List<?> toList(String jsonArray) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonArray, List.class);
        } catch (IOException ex) {
            log.info("Json threw an exception: " + ex.getMessage());
        }
        return null;
    }

}
