package edu.ucla.library.libservices.webservices.erdb.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.erdb.beans.ERDbResourceBean;
import edu.ucla.library.libservices.webservices.erdb.db.mappers.ERDbResourceMapper;

//import java.net.URLDecoder;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ERDbResourceGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String subject;
  private String type;
  private String count;
  private String dbName;
  private List<ERDbResourceBean> resources;
  private StringBuffer output;

  private static final String CCLE_DEPT = "ccle_dept";
  private static final String RANK = "rank";
  private static final String TITLE_ID = "title_id";
  private static final String TITLE = "title";
  private static final String URL = "url";
  private static final String TYPE = "type";
  private static final String TOTAL = "total";

  public ERDbResourceGenerator()
  {
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource(getDbName());
    //ds = DataSourceFactory.createERDbSource();
  }

  public String getXmlResources()
  {
    makeConnection();
    getResources();
    
    output = 
      new StringBuffer( "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<resources>\n" );
    
    for ( ERDbResourceBean theBean : resources  )
    {
      output.append( "<resource>\n" );

      output.append( buildElement( CCLE_DEPT, cleanUp( theBean.getCcle_dept() ) ) );
      output.append( buildElement( RANK, String.valueOf( theBean.getRank() ) ) );
      output.append( buildElement( TITLE_ID, String.valueOf( theBean.getTitle_id() ) ) );
      output.append( buildElement( TITLE, cleanUp( theBean.getTitle() ) ) );
      output.append( buildElement( URL, cleanUp( theBean.getUrl() ) ) );
      output.append( buildElement( TYPE, cleanUp( theBean.getType() ) ) );
      output.append( buildElement( TOTAL, String.valueOf( theBean.getTotal() ) ) );

      output.append( "</resource>\n" );
    }

    output.append( "</resources>\n" );
    return output.toString();
  }

  public String getJsonResources()
    throws JSONException
  {
    makeConnection();
    getResources();

    output = new StringBuffer( "{ [" );

    for ( int index = 0; index < resources.size(); index++ )
    {
      addResource( resources.get( index ) );
      if ( index < ( resources.size() - 1 ) )
        output.append( "," );
    }

    output.append( "]}" );

    return output.toString();
  }

  private void getResources()
  {
    resources = new JdbcTemplate( ds )
      .query( buildQuery(), new ERDbResourceMapper() );
  }

  private String cleanUp( String value )
  {
    if ( value != null && value.length() != 0 )
      return value.replaceAll( "&", "&amp;" );
    else
      return value;
  }

  private StringBuffer buildElement( String field, String value )
  {
    StringBuffer element;
    element = new StringBuffer();

    element.append( "<" ).append( field ).append( ">" ).append( value )
      .append( "</" ).append( field ).append( ">\n" );

    return element;
  }

  private String buildQuery()
  {
    StringBuffer query;

    query = new StringBuffer(
      "select * from ERdb.dbo.rpt_top_n_for_ccle where ccle_dept = '");
    query.append(getSubject()).append("' and type = '")
      .append( getType() ).append( "'");
    if ( !getCount().equalsIgnoreCase( "all" ) )
      query.append( " and rank <= " ).append( getCount() );
    query.append(" order by rank");
    
    return query.toString();
  }

  private void addResource( ERDbResourceBean resource )
    throws JSONException
  {
    JSONObject obj;

    obj = new JSONObject( resource, false );
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

  public void setType( String type )
  {
    this.type = type; 
  }

  public String getType()
  {
    return type;
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

}
