package edu.ucla.library.libservices.webservices.serials.generators;

import edu.ucla.library.libservices.utility.db.DataSourceFactory;
import edu.ucla.library.libservices.webservices.serials.beans.SerialsResource;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

public class SerialsResourceGenerator
{
  //private DriverManagerDataSource ds;
  private DataSource ds;
  private String subject;
  private String dbName;
  private List<SerialsResource> resources;
  private Document doc;

  public SerialsResourceGenerator()
  {
  }

  public void setSubject( String subject )
  {
    this.subject = subject;
  }

  private String getSubject()
  {
    return subject;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public String getResources()
    throws ParserConfigurationException, SAXException, IOException, 
           XPathExpressionException, JSONException
  {
    StringBuffer output;

    makeConnection();
    populateDocument();
    populateResources();

    output = new StringBuffer( "{ [" );
    for ( int index = 0; index < resources.size(); index++ )
    {
      JSONObject obj;

      obj = new JSONObject( resources.get( index ), false );
      output.append( obj.toString( 1 ) );
      if ( index < ( resources.size() - 1 ) )
        output.append( "," );
    }

    output.append( "] }" );
    return output.toString();
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }

  private void populateResources()
    throws XPathExpressionException
  {
    NodeList names;
    NodeList urls;
    String query = "select vger_support.get_serials_groupid(?) from dual";
    String groupID, parentID, combined;

    combined = new JdbcTemplate( ds ).queryForObject( query, new Object[]
          { getSubject() }, String.class ).toString();

    groupID = combined.split( ":" )[ 0 ];
    parentID = combined.split( ":" )[ 1 ];

    names = getNames( groupID );
    urls = getURLs( groupID );

    resources = new ArrayList<SerialsResource>();
    for ( int i = 0; i < names.getLength(); i++ )
    {
      SerialsResource bean;
      bean = new SerialsResource();
      bean.setTitle( names.item( i ).getNodeValue() );
      bean.setParentID( parentID );
      resources.add( bean );
    }

    for ( int i = 0; i < resources.size(); i++ )
    {
      resources.get( i ).setUrl( urls.item( i ).getNodeValue() );
    }
  }

  private NodeList getNames( String group )
    throws XPathExpressionException
  {
    NodeList names;
    XPath xpath;
    XPathExpression expr;
    XPathFactory factory;

    factory = XPathFactory.newInstance();
    xpath = factory.newXPath();
    expr = 
        xpath.compile( "//subjectList/subject/resourceGroups/group[@id=" + 
                       group + "]/resources/database/name/text()" );
    names = ( NodeList ) expr.evaluate( doc, XPathConstants.NODESET );

    return names;
  }

  private NodeList getURLs( String group )
    throws XPathExpressionException
  {
    NodeList urls;
    XPath xpath;
    XPathExpression expr;
    XPathFactory factory;

    factory = XPathFactory.newInstance();
    xpath = factory.newXPath();
    expr = 
        xpath.compile( "//subjectList/subject/resourceGroups/group[@id=" + 
                       group + "]/resources/database/URL/text()" );
    urls = ( NodeList ) expr.evaluate( doc, XPathConstants.NODESET );

    return urls;
  }

  private void populateDocument()
    throws ParserConfigurationException, SAXException, IOException
  {
    DocumentBuilderFactory domFactory = 
      DocumentBuilderFactory.newInstance();
    domFactory.setNamespaceAware( true ); // never forget this!
    DocumentBuilder builder = domFactory.newDocumentBuilder();
    doc = 
        builder.parse( new URL( "http://webservices.library.ucla.edu/serials/serials2.xml" ).openStream() );
  }
}
