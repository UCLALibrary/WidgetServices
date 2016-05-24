package edu.ucla.library.libservices.webservices.libguides.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.libguides.beans.LibGuide;

import edu.ucla.library.libservices.webservices.libguides.db.mappers.LibGuideMapper;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class LibGuideGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private List<LibGuide> guides;
  private String dbName;
  private String subject;
  private StringBuffer results;

  private static final String GUIDES_SQL = 
    "SELECT * FROM vger_support.ccle_libguides_map WHERE ccle_dept = ? ORDER BY libguide_name";

  public LibGuideGenerator()
  {
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void setSubject( String subject )
  {
    this.subject = subject;
  }

  private String getSubject()
  {
    return subject;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  public String getJsonGuides()
    throws JSONException
  {
    makeConnection();
    getGuides();

    results = new StringBuffer( "{ [" );

    for ( int index = 0; index < guides.size(); index++ )
    {
      addGuide( guides.get( index ) );
      if ( index < ( guides.size() - 1 ) )
        results.append( "," );
    }

    results.append( "] }" );

    return results.toString();
  }


  private void addGuide( LibGuide guide )
    throws JSONException
  {
    JSONObject obj;

    obj = new JSONObject( guide, false );
    results.append( obj.toString( 1 ) );
  }

  private void getGuides()
  {
    guides = new JdbcTemplate( ds ).query( GUIDES_SQL, new Object[]
          { getSubject() }, new LibGuideMapper() );
  }
}
