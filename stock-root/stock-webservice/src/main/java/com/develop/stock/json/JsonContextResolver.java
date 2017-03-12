package com.develop.stock.json;

import com.google.common.collect.Maps;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

import javax.ws.rs.ext.ContextResolver;
import java.util.Map;

public class JsonContextResolver implements ContextResolver<MoxyJsonConfig>{

	private final MoxyJsonConfig config;
	
	public JsonContextResolver() {
		final Map<String, String> namespacePrefixMapper = Maps.newHashMap();
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        
        
        config = new MoxyJsonConfig()
            .setNamespacePrefixMapper(namespacePrefixMapper)
            .setNamespaceSeparator(':')
            .setFormattedOutput(true)
            .setIncludeRoot(true)
            .setMarshalEmptyCollections(true);
	}
	
	public MoxyJsonConfig getContext(Class<?> arg0) {

		return config;
	}

}
