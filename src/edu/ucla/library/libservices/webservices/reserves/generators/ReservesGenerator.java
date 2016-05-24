package edu.ucla.library.libservices.webservices.reserves.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.reserves.beans.ReservesBean;
import edu.ucla.library.libservices.webservices.reserves.db.mappers.ReservesMapper;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ReservesGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String srs_number;
  private String dbName;
  private List<ReservesBean> bookList;
  private StringBuffer results;

  private static final String SRS_NUMBER = "srs_number";
  private static final String COURSE_NAME = "course_name";
  private static final String TITLE = "title";
  private static final String LOCATION = "location";
  private static final String CALL_NO = "call_no";
  private static final String ITEM_STATUS = "item_status";
  private static final String ITEM_ID = "item_id";
  private static final String ITEM_ENUM = "item_enum";
  private static final String COPY_NUMBER = "copy_number";
  private static final String LIBRARY_NAME = "library_name";
  private static final String MAP_LINK = "map_link";
  private static final String RESERVE_SQL = 
    "SELECT * FROM vger_support.reserve_items_by_course WHERE srs_number = ? ORDER BY title";

  public ReservesGenerator()
  {
  }

  public void setSrs_number( String srs_number )
  {
    this.srs_number = srs_number;
  }

  public String getSrs_number()
  {
    return srs_number;
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
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  public String getXmlReserves()
  {
    makeConnection();
    getBookList();

    results = 
        new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><books>\n" );

    for ( ReservesBean theBook: bookList )
    {
      results.append( "<book>\n" );
      results.append( buildElement( SRS_NUMBER, 
                                    cleanUp( theBook.getSrs_number() ) ) );
      results.append( buildElement( COURSE_NAME, 
                                    cleanUp( theBook.getCourse_name() ) ) );
      results.append( buildElement( TITLE, 
                                    cleanUp( theBook.getTitle() ) ) );
      results.append( buildElement( LOCATION, 
                                    cleanUp( theBook.getLocation() ) ) );
      results.append( buildElement( CALL_NO, 
                                    cleanUp( theBook.getCall_no() ) ) );
      results.append( buildElement( ITEM_STATUS, 
                                    cleanUp( theBook.getItem_status() ) ) );
      results.append( buildElement( ITEM_ID, 
                                    cleanUp( theBook.getItem_id() ) ) );
      results.append( buildElement( ITEM_ENUM, 
                                    cleanUp( theBook.getItem_enum() ) ) );
      results.append( buildElement( COPY_NUMBER, 
                                    cleanUp( theBook.getCopy_number() ) ) );
      results.append( buildElement( LIBRARY_NAME, 
                                    cleanUp( theBook.getLibrary_name() ) ) );
      results.append( buildElement( MAP_LINK, 
                                    cleanUp( theBook.getMap_link() ) ) );
      results.append( "</book>\n" );
    }

    results.append( "</books>\n" );
    return results.toString();
  }

  private StringBuffer buildElement( String field, String value )
  {
    StringBuffer element;
    element = new StringBuffer();

    element.append( "<" ).append( field ).append( ">" ).append( value ).append( "</" ).append( field ).append( ">\n" );

    return element;
  }

  public String getJsonReserves()
    throws JSONException
  {
    makeConnection();
    getBookList();

    //results = new StringBuffer( "{\"books\":[" );
    results = new StringBuffer( "{ [" );

    for ( int index = 0; index < bookList.size(); index++ )
    {
      addBook( bookList.get( index ) );
      if ( index < ( bookList.size() - 1 ) )
        results.append( "," );
    }

    results.append( "]}" );

    return results.toString();
  }

  private String cleanUp( String value )
  {
    if ( value != null && value.length() != 0 )
      return value.replaceAll( "&", "&amp;" );
    else
      return value;
  }

  private void addBook( ReservesBean book ) 


    throws JSONException
  {
    JSONObject obj;

    obj = new JSONObject( book, false );
    results.append( obj.toString( 1 ) );
  }

  private void getBookList()
  {
    bookList = new JdbcTemplate( ds ).query( RESERVE_SQL, new Object[]
          { getSrs_number() }, new ReservesMapper() );
  }
}
