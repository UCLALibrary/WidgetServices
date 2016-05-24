package edu.ucla.library.libservices.webservices.invoices.invoicenumbers.generator;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class InvoiceNumberGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String branchCode;
  private String dbName;
  private static final String SEQUENCE_SQL = 
    "SELECT lpad(invoice_owner.invoice_seq.nextval, 8, '0') FROM dual";

  public InvoiceNumberGenerator()
  {
  }

  public void setBranchCode( String branchCode )
  {
    this.branchCode = branchCode;
  }

  private String getBranchCode()
  {
    return branchCode;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }
  
  public String getInvoiceNumber()
  {
    makeConnection();
    
    return getBranchCode().concat( 
      new JdbcTemplate( ds ).queryForObject(
        SEQUENCE_SQL, String.class).toString() );
  }
}
