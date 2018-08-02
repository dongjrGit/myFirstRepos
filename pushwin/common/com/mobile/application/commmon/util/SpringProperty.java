package com.mobile.application.commmon.util;
    
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SpringProperty extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap;
    public static Properties props;

    @Override
    protected void loadProperties(Properties property) throws IOException {
        super.loadProperties(property);
        props = property;
    }
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		
		super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}
}
