package edu.ucla.library.libservices.webservices.invoices.patrons.generator;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.invoices.patrons.beans.PatronBean;
import edu.ucla.library.libservices.webservices.invoices.patrons.db.mappers.PatronMapper;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@XmlRootElement(name="patronList")
public class PatronGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String barcode;
  private String lastName;
  private String dbName;
  @XmlElement(name="patron")
  private List<PatronBean> patrons;
  private static final String PATRON_BY_BARCODE = 
    "SELECT * FROM vger_support.invoice_patrons WHERE patron_barcode = ?" 
    + " ORDER BY normal_last_name";
  private static final String PATRON_BY_NAME = 
    "SELECT * FROM vger_support.invoice_patrons WHERE upper(normal_last_name)" 
    + " like upper(?) ORDER BY normal_last_name, normal_first_name";

  public PatronGenerator()
  {
  }

  public void setBarcode( String barcode )
  {
    this.barcode = barcode;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  //public List<PatronBean> getPatronsByBarcode()
  public void getPatronsByBarcode()
  {
    makeConnection();

    patrons = new JdbcTemplate( ds ).query( PATRON_BY_BARCODE, new Object[]
          { getBarcode() }, new PatronMapper() );

    //return patrons;
  }

  //public List<PatronBean> getPatronsByName()
  public void getPatronsByName()
  {
    makeConnection();

    patrons = new JdbcTemplate( ds ).query( PATRON_BY_NAME, new Object[]
          { "%".concat(getLastName()).concat("%") }, new PatronMapper() );

    //return patrons;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  private String getDbName()
  {
    return dbName;
  }

  private String getBarcode()
  {
    return barcode;
  }

  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  private String getLastName()
  {
    return lastName;
  }
}
