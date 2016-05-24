package edu.ucla.library.libservices.webservices.newitems.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.newitems.beans.NewItemBean;
import edu.ucla.library.libservices.webservices.newitems.db.mappers.NewItemMapper;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class NewItemGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String subject;
  private String count;
  private String dbName;
  private List<NewItemBean> items;
  private StringBuffer output;

  private static final String SHORT_TITLE = "shortTitle";
  private static final String FULL_TITLE = "fullTitle";
  private static final String PUB_DATE = "pubDate";
  private static final String URL = "url";
  private static final String BASE_URL = 
    "http://catalog.library.ucla.edu/cgi-bin/Pwebrecon.cgi?DB=local&BBID=";
  private static final String QUERY_WITHOUT_SUBJECT = 
    "SELECT DISTINCT bib_id,short_title, full_title, publisher_date FROM (" 
    + "SELECT * FROM vger_support.MOODLE_NEW_ITEMS ORDER BY added_date DESC) " 
    + "WHERE ROWNUM <= ?";
  private static final String QUERY_WITH_SUBJECT = 
    "SELECT DISTINCT bib_id,short_title, full_title, publisher_date FROM (" 
    + "SELECT mni.* FROM vger_support.MOODLE_NEW_ITEMS mni join " 
    + "vger_support.SUBJECT_CCLE_MAP scm ON mni.subject = scm.subject WHERE " 
    + "scm.ccle_dept = ? ORDER BY mni.added_date DESC) WHERE ROWNUM <= ?";
  private static final String QUERY_FOR_MATCH = 
    "SELECT COUNT(*) FROM vger_support.SUBJECT_CCLE_MAP WHERE ccle_dept = ?";

  public NewItemGenerator()
  {
  }

  public String getXmlResources()
  {
    makeConnection();
    getResources();

    output = 
        new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<items>\n" );

    for ( NewItemBean theItem: items )
    {
      output.append( "<item>\n" );

      output.append( buildElement( SHORT_TITLE, 
                                   cleanUp( theItem.getShortTitle() ) ) );
      output.append( buildElement( FULL_TITLE, 
                                   cleanUp( theItem.getFullTitle() ) ) );
      output.append( buildElement( PUB_DATE, 
                                   cleanUp( theItem.getPubDate() ) ) );
      output.append( buildElement( URL, 
                                   cleanUp( BASE_URL.concat( theItem.getBib_id() ) ) ) );

      output.append( "</item>\n" );
    }

    output.append( "</items>\n" );
    return output.toString();
  }

  public String getJsonResources()
    throws JSONException
  {
    makeConnection();
    getResources();

    //output = new StringBuffer( "{\"items\":[" );
    output = new StringBuffer( "{ [" );

    for ( int index = 0; index < items.size(); index++ )
    {
      addItem( items.get( index ) );
      if ( index < ( items.size() - 1 ) )
        output.append( "," );
    }

    output.append( "]}" );

    return output.toString();
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource(getDbName());
    //ds = DataSourceFactory.createVgerSource();
  }

  private void getResources()
  {
    if ( foundCCLEMatch() )
      items = 
          new JdbcTemplate( ds ).query( 
            QUERY_WITH_SUBJECT, 
            new Object[]{getSubject(),getCount()}, 
            new NewItemMapper() );
    else
      items = 
          new JdbcTemplate( ds ).query( 
            QUERY_WITHOUT_SUBJECT, 
            new Object[]{getCount()}, 
            new NewItemMapper() );
  }

  private StringBuffer buildElement( String field, String value )
  {
    StringBuffer element;
    element = new StringBuffer();

    element
      .append( "<" ).append( field ).append( ">" ).append( value )
      .append( "</" ).append( field ).append( ">\n" );

    return element;
  }

  private String cleanUp( String value )
  {
    if ( value != null && value.length() != 0 )
      return value.replaceAll( "&", "&amp;" );
    else
      return value;
  }

  private void addItem( NewItemBean item )
    throws JSONException
  {
    JSONObject obj;

    obj = new JSONObject( item, false );
    output.append( obj.toString( 1 ) );
  }

  public void setSubject( String subject )
  {
    this.subject = subject;
  }

  public String getSubject()
  {
    return subject;
  }

  public void setCount( String count )
  {
    this.count = count;
  }

  public String getCount()
  {
    return count;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  public String getDbName()
  {
    return dbName;
  }

  private boolean foundCCLEMatch()
  {
    return (new JdbcTemplate( ds ).queryForInt(
      QUERY_FOR_MATCH, new Object[] {getSubject()}) != 0);
  }
}

/*
 * check if Moodle dept is in SUBJECT_CCLE_MAP
 * if so, use join sql
 * if not, use generic sql
 */
 
 /*
  private String buildQuery()
  {
    StringBuffer query;

    query = new StringBuffer( "SELECT DISTINCT BIB_ID,SHORT_TITLE, FULL_TITLE, PUBLISHER_DATE FROM (" );

    query.append( "SELECT * FROM VGER_SUPPORT.MOODLE_NEW_ITEMS" ); // WHERE SUBJECT = '" );
    //query.append( getSubject() ).append( "' ORDER BY ADDED_DATE DESC)" ); 
    query.append( " ORDER BY ADDED_DATE DESC)" ); 
    query.append( " WHERE ROWNUM <= " ).append( getCount() );

    return query.toString();
  }
  */