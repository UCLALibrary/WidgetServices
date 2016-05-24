package edu.ucla.library.libservices.webservices.hours.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.hours.beans.HoursBean;
import edu.ucla.library.libservices.webservices.hours.db.mappers.HoursMapper;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.json.JSONException;
import org.json.JSONObject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "scheduleList" )
public class HoursGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String dbName;
  @XmlElement( name = "unitSchedule" )
  private List<HoursBean> hours;
  private StringBuffer output;
  private int unitID;

  private static final String QUERY =
    "SELECT * FROM Library_Web.dbo.Widget_Hours ORDER BY Substring(Unit_Title, 1,3)";
  private static final String UNIT_QUERY =
    "SELECT * FROM Library_Web.dbo.Widget_Hours WHERE Unit_ID = ?";
  private static final String UNITTITLE = "library";
  private static final String PERIODTITLE = "period";
  private static final String MONTHURS = "monThu";
  private static final String FRI = "fri";
  private static final String SAT = "sat";
  private static final String SUN = "sun";
  private static final SimpleDateFormat FORMAT =
    new SimpleDateFormat( "h:mm a" );

  public HoursGenerator()
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
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createHoursSource();
  }

  private void getHours()
  {
    hours = new JdbcTemplate( ds ).query( QUERY, new HoursMapper() );
  }

  public List<HoursBean> getHoursByUnit()
  {
    makeConnection();
    hours = new JdbcTemplate( ds ).query( UNIT_QUERY, new Object[]{getUnitID()}, new HoursMapper() );
    return hours;
  }

  public String getXmlHours()
  {
    makeConnection();
    getHours();

    output =
        new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<hours>\n" );

    for ( HoursBean theBean: hours )
    {
      output.append( "<schedule>\n" );

      output.append( buildElement( PERIODTITLE,
                                   theBean.getPeriodTitle() ) );
      output.append( buildElement( UNITTITLE, theBean.getUnitTitle() ) );
      output.append( buildElement( MONTHURS,
                                   determineHours( theBean.getMonThursOpens(),
                                                   theBean.getMonThursCloses(),
                                                   theBean.isMonThursClosed(),
                                                   theBean.getMonThursNote() ) ) );
      output.append( buildElement( FRI,
                                   determineHours( theBean.getFriOpens(),
                                                   theBean.getFriCloses(),
                                                   theBean.isFriClosed(),
                                                   theBean.getFriNote() ) ) );
      output.append( buildElement( SAT,
                                   determineHours( theBean.getSatOpens(),
                                                   theBean.getSatCloses(),
                                                   theBean.isSatClosed(),
                                                   theBean.getFriNote() ) ) );
      output.append( buildElement( SUN,
                                   determineHours( theBean.getSunOpens(),
                                                   theBean.getSunCloses(),
                                                   theBean.isSunClosed(),
                                                   theBean.getFriNote() ) ) );

      output.append( "</schedule>\n" );
    }

    output.append( "</hours>\n" );
    return output.toString();
  }

  private StringBuffer buildElement( String field, String value )
  {
    StringBuffer element;
    element = new StringBuffer();

    element.append( "<" ).append( field ).append( ">" ).append( value ).append( "</" ).append( field ).append( ">\n" );

    return element;
  }

  public String getJsonHours()
  {
    makeConnection();
    getHours();

    output = new StringBuffer( "{ [" );

    for ( int index = 0; index < hours.size(); index++ )
    {
      addHour( hours.get( index ) );
      if ( index < ( hours.size() - 1 ) )
        output.append( "," );
    }

    output.append( "\n] }" );

    return output.toString();
  }

  public String getFormalJsonHours()
    throws JSONException
  {
    makeConnection();
    getHours();

    output = new StringBuffer( "{ hours : [" );

    for ( int index = 0; index < hours.size(); index++ )
    {
      JSONObject obj;

      obj = new JSONObject( hours.get( index ), false );
      output.append( "schedule : " ).append( obj.toString( 1 ) );

      if ( index < ( hours.size() - 1 ) )
        output.append( "," );
    }

    output.append( "\n] }" );

    return output.toString();
  }

  public String getSimpleJsonHours()
  {
    makeConnection();
    getHours();

    output = new StringBuffer( "[" );

    for ( int index = 0; index < hours.size(); index++ )
    {
      addHour( hours.get( index ) );
      if ( index < ( hours.size() - 1 ) )
        output.append( "," );
    }

    output.append( "\n]" );

    return output.toString();
  }

  private void addHour( HoursBean theHour )
  {
    output.append( "\n{" ).append( "\"period\":\"" +
                                   theHour.getPeriodTitle() + "\"," );
    output.append( "\"library\":\"" + theHour.getUnitTitle() + "\"," );
    output.append( "\"monThu\":\"" +
                   determineHours( theHour.getMonThursOpens(),
                                   theHour.getMonThursCloses(),
                                   theHour.isMonThursClosed(),
                                   theHour.getMonThursNote() ) + "\"," );
    output.append( "\"fri\":\"" +
                   determineHours( theHour.getFriOpens(), theHour.getFriCloses(),
                                   theHour.isFriClosed(),
                                   theHour.getFriNote() ) + "\"," );
    output.append( "\"sat\":\"" +
                   determineHours( theHour.getSatOpens(), theHour.getSatCloses(),
                                   theHour.isSatClosed(),
                                   theHour.getSatNote() ) + "\"," );
    output.append( "\"sun\":\"" +
                   determineHours( theHour.getSunOpens(), theHour.getSunCloses(),
                                   theHour.isSunClosed(),
                                   theHour.getSunNote() ) +
                   "\"" ).append( "}" );
  }

  private String determineHours( Date opens, Date closes, boolean isClosed,
                                 String note )
  {
    StringBuffer dailyHours;

    dailyHours = new StringBuffer();

    if ( !isEmpty( opens ) && !isEmpty( closes ) )
    {
      dailyHours.append( formatTime( opens ) ).append( " - " ).append( formatTime( closes ) );
    }
    else if ( isClosed )
    {
      dailyHours.append( "closed" );
    }
    if ( !isEmpty( note.trim() ) )
    {
      dailyHours.append( " " ).append( note );
    }

    return dailyHours.toString().trim();
  }

  private boolean isEmpty( Object term )
  {
    return ( term == null || isEmpty( term.toString() ) );
  }

  private boolean isEmpty( String term )
  {
    return ( term == null || term.length() == 0 || term.equals( "" ) );
  }

  private String formatTime( Date time )
  {
    return FORMAT.format( time );
  }

  public void setUnitID( int unitID )
  {
    this.unitID = unitID;
  }

  private int getUnitID()
  {
    return unitID;
  }
}
/*
 private String cleanUp( String value )
 {
   if ( value != null && value.length() != 0 )
     return value.replaceAll( "&", "&amp;" );
   else
     return value;
 }
 */
