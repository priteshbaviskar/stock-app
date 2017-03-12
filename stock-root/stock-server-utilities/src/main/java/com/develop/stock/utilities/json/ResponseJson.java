package com.develop.stock.utilities.json;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * A generic json response builder which can be used by different modules across the system.
 * A standard messaging format follows the following convention-
 * {
 *      "responseMessage": "A custom response message which can be directly rendered to UI",
 *      "isSuccess": true/false,
 *      "object": {
 *          // a custom response object returned. In this case, its a pair of buy/sell dates
 *          "responsepair": {
 *              "buyDate": "2016-10-26",
 *              "sellDate": "2017-01-17"
 *          }
 *      }
 * }
 *
 */

public class ResponseJson extends Json{
	
	private boolean isSuccess;
	private String responseMessage;
	
	public ResponseJson addStatus(boolean success) {
		this.isSuccess = success;
		return this;
	}
	
	public ResponseJson addResponseMessage(String message) {
		this.responseMessage = message;
		return this;
	}
	
	public <T> String build(T entity) {

		Map<String, Object> entityMap = Maps.newHashMap();
		Map<String, Object> responseMap = Maps.newHashMap();
	
		if(entity != null) {
			String entityName = entity.getClass().getSimpleName().toLowerCase();
			entityMap.put(entityName, entity);
		}
		
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("responseMessage", responseMessage);
		responseMap.put("object", entityMap);
		
		return writeAsJson(responseMap);
	}
	public boolean isSuccess() {
		return isSuccess;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}	

