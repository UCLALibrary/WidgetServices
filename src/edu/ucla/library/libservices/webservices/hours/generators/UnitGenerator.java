package edu.ucla.library.libservices.webservices.hours.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.hours.beans.UnitBean;
import edu.ucla.library.libservices.webservices.hours.db.mappers.UnitMapper;

import java.util.List;

import javax.sql.DataSource;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@XmlRootElement( name = "unitList" )
public class UnitGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String dbName;
  @XmlElement( name = "unit" )
  private List<UnitBean> units;

  private static final String QUERY = 
    "SELECT DISTINCT	Unit_ID, Substring(Unit_Code, 1,3) AS Unit_Code," + 
    " Unit_Title FROM Library_Web.dbo.Widget_Hours ORDER BY" + 
    " Substring(Unit_Code, 1,3)";

  public UnitGenerator()
  {
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  public String getDbName()
  {
    return dbName;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource(getDbName());
    //ds = DataSourceFactory.createHoursSource();
  }


  public void generateUnits()
  {
    makeConnection();

    units = new JdbcTemplate( ds ).query( QUERY, new UnitMapper() );
  }

  public void setUnits( List<UnitBean> units )
  {
    this.units = units;
  }

  /*public List<UnitBean> getUnits()
  {
    return units;
  }*/
}
