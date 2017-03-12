package com.develop.stock.utilities.json;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

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
			//special case. Maybe a better way to handle?
            // message codes 001 are user login specific.
			if(responseMessage.contains("001")) {
				entityMap.put("auth_token", entity);
			}else {
				entityMap.put(entityName, entity);
			}
		}
		
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("responseMessage", responseMessage);
		responseMap.put("object", entityMap);
		
		return writeAsJson(responseMap);
	}
	
	public <T> String buildCustomObject(Class<?> clazz, Map<String, Object> map) {
		
		Map<String, Object> responseMap = Maps.newHashMap();
		Map<String, Object> entityMap = Maps.newHashMap();
		String entityName = clazz.getSimpleName().toLowerCase();
		entityMap.put(entityName, map);
		
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("responseMessage", responseMessage);
		responseMap.put("object", entityMap);
		
		return writeAsJson(responseMap);
	}
	
	
	public <T> String buildList(List<T> entities) {

		Map<String, Object> responseMap = Maps.newHashMap();
		Multimap<String, Object> entityMap = ArrayListMultimap.create();
		
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("responseMessage", responseMessage);

		if(entities != null && !entities.isEmpty()) {
			String entityName = entities.get(0).getClass().getSimpleName().toLowerCase();
			for (T entity : entities) {
				entityMap.put(entityName, entity);
			}
		}
		responseMap.put("object", entityMap.asMap());
		return writeAsJson(responseMap);
	}

    public <T> String buildSearchResultList(List<T> entities, BigInteger totalCount) {


        Map<String, Object> responseMap = Maps.newHashMap();
        Multimap<String, Object> entityMap = ArrayListMultimap.create();

        responseMap.put("isSuccess", isSuccess);
        responseMap.put("responseMessage", responseMessage);

        if(entities != null && !entities.isEmpty()) {
            String entityName = entities.get(0).getClass().getSimpleName().toLowerCase();
            entityMap.put("totalCount", totalCount);
            for (T entity : entities) {
                entityMap.put(entityName, entity);
            }

        }
        responseMap.put("object", entityMap.asMap());
        return writeAsJson(responseMap);
    }

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}	
