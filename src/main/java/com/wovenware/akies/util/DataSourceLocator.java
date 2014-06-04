package com.wovenware.akies.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceLocator {
	private Logger _logger = Logger.getLogger(this.getClass().getName());
	
	private static DataSourceLocator _dsl = null;
	
	private InitialContext _ic = null;
	
	private Map<String, DataSource> _cache = null;
	
	private final String JNDI_PREFIX = "java:comp/env/";
	
	private DataSourceLocator() throws Exception {
		_cache = Collections.synchronizedMap(new HashMap<String, DataSource>());
		
		try {
			_ic = new InitialContext();
		} catch(NamingException ne) {
			_logger.severe("Error setting Initial Context!");
			_logger.severe(ne.getMessage());
			
			throw new Exception(ne.getMessage(), ne);
		}
	}
	
	public static DataSourceLocator getInstance() throws Exception {
		if(_dsl == null)
		{
			_dsl = new DataSourceLocator();
		}
		
		return _dsl;
	}
	
	public DataSource getDataSource(String dataSourceName) throws Exception {
		DataSource dataSource = null;
		
		if(dataSourceName != null && !dataSourceName.isEmpty()) {
			String jndiName = JNDI_PREFIX + dataSourceName;
			
			dataSource = _cache.get(jndiName);
			
			try {
				if(dataSource == null && !_cache.containsKey(jndiName)) {
					dataSource = (DataSource) _ic.lookup(jndiName);
					
					_cache.put(jndiName, dataSource);
				}
			} catch(NamingException ne) {
				_logger.severe("Error looking for Data Source: [" + dataSourceName + "]");
				_logger.severe(ne.getMessage());
				
				throw new Exception(ne.getMessage(), ne);
			}
		}
		else {
			_logger.warning("The Data Source name provided is null or empty!");
		}
		
		return dataSource;
	}
}
