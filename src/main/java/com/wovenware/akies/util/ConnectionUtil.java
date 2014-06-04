package com.wovenware.akies.util;

import java.sql.Connection;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class ConnectionUtil {
	private static Logger _logger = Logger.getLogger(ConnectionUtil.class.getName());
	
	private static final int MAX_ATTEMPTS = 50;
	
	private ConnectionUtil() {}
	
	public static Connection createConnection(String jndiName, boolean autoCommit) throws Exception {
		Connection connection = null;
		
		try {
			DataSource dataSource = DataSourceLocator.getInstance().getDataSource(jndiName);
			
			for(int i = 1; i <= MAX_ATTEMPTS; i++)
			{
				connection = dataSource.getConnection();
				connection.setAutoCommit(autoCommit);
				
				try {
					if(connection != null && !connection.isClosed()) {
						connection.rollback();
						
						break;
					}
				} catch(Exception e) {
					if(connection != null && !connection.isClosed()) {
						connection.close();
					}
					
					_logger.warning("Connection attempt #" + String.valueOf(i) + " failed!");
					
					if(i == MAX_ATTEMPTS) {
						_logger.severe("Connection max attempts (" + MAX_ATTEMPTS + ") reached!");
						
						throw e;
					}
				}
			}
			
		} catch (Exception e) {
			_logger.severe("Error creating connection!");
			_logger.severe(e.getMessage());
			
			throw new Exception(e.getMessage(), e);
		}
		
		return connection;
	}
}
