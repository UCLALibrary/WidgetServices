package edu.ucla.library.libservices.utility.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
  }

  public static DriverManagerDataSource createVgerSource()
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName( "oracle.jdbc.OracleDriver" );
    ds.setUrl( "jdbc:oracle:thin:@ils-db-prod.library.ucla.edu:1521:VGER" );
    ds.setUsername( "vger_support" );
    ds.setPassword( "vger_support_pwd" );

    return ds;
  }

  public static DriverManagerDataSource createERDbSource()
  {
    DriverManagerDataSource ds;
    
    ds  = new DriverManagerDataSource();
    ds.setDriverClassName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
    ds.setUrl( "jdbc:sqlserver://db-erdb.library.ucla.edu:1433" );
    ds.setUsername( "ERdb_Public" );
    ds.setPassword( "ERdb_Public_pwd" );
    
    return ds;
  }
  
  public static DriverManagerDataSource createHoursSource()
  {
    DriverManagerDataSource ds;
    
    ds  = new DriverManagerDataSource();
    ds.setDriverClassName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
    ds.setUrl( "jdbc:sqlserver://db-libraryweb.library.ucla.edu:1433" );
    ds.setUsername( "Hours_Update" );
    ds.setPassword( "Hours_Update_pwd" );
    
    return ds;
  }
  
  public static DataSource createDataSource( String name )
  {
    Context envContext;
    InitialContext context;
    DataSource connection;

    try
    {
      context = new InitialContext();
      envContext = (Context)context.lookup("java:/comp/env");
      connection = ( DataSource ) envContext.lookup( name );
    }
    catch ( NamingException e )
    {
      e.printStackTrace();
      connection = null;
    }

    return connection;
  }
}
