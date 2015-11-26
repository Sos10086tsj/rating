/**
 * 
 */
package com.chinesedreamer.rating.common.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author dev-laiting
 *
 */
public final class PropertiesUtils {

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtils.class);

	private final Properties properties;

	/**
	 * 以";"分隔
	 * 
	 * @param resourcesPaths
	 */
	public PropertiesUtils(String resourcesPaths) {
		properties = loadProperties(resourcesPaths.split(";"));
	}

	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();

		for (String location : resourcesPaths) {
			logger.debug("Loading properties file from path:{}", location);
			InputStream is = null;
			try {
				is = PropertiesUtils.class.getClassLoader()
						.getResourceAsStream(location);
				props.load(is);
			} catch (IOException ex) {
				logger.info("Could not load properties from path:{}, {} ",
						location, ex.getMessage());
			} finally {
				if (is != null) {
	                try {
						is.close();
					} catch (IOException e) {
						//ignore
					}
	            }
			}
		}
		return props;
	}

	/**
	 * 取出Property。
	 */
	private String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		return properties.getProperty(key);
	}

	/**
	 * 取出String类型的Property,如果都為Null则抛出异常.
	 */
	public String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

	/**
	 * 取出String类型的Property.如果都為Null則返回Default值.
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * 取出所有key-value
	 * 
	 * @return
	 */
	public Map<String, String> getAllKeyValues() {
		Map<String, String> map = new HashMap<String, String>();

		if (properties == null) {
			return map;
		}

		Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			map.put(key, value);
		}

		return map;
	}
}
