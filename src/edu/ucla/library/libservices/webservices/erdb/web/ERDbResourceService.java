package edu.ucla.library.libservices.webservices.erdb.web;

import edu.ucla.library.libservices.webservices.erdb.generators.ERDbResourceGenerator;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;

@Path( "/resources/" )
public class ERDbResourceService
{
  @Context
  ServletConfig config;

  public ERDbResourceService()
  {
  }

  @GET
  @Produces( "text/xml" )
  @Path( "xml/subject/{subject}/type/{type}/items/{items}" )
  public String getXml( @PathParam( "subject" ) String subject, 
    @PathParam( "type" ) String type, @PathParam( "items" ) String items )
    throws UnsupportedEncodingException
  {
    ERDbResourceGenerator docMaker;

    docMaker = new ERDbResourceGenerator();

    docMaker.setCount( items );
    docMaker.setDbName(config.getServletContext().getInitParameter("datasource.mssql"));
    docMaker.setSubject( URLDecoder.decode( subject, "UTF-8" ) );
    docMaker.setType( URLDecoder.decode( type, "UTF-8" ) );

    return docMaker.getXmlResources();
  }

  @GET
  @Produces( "application/json" )
  @Path( "json/subject/{subject}/type/{type}/items/{items}" )
  public String getJson( @PathParam( "subject" ) String subject, 
    @PathParam( "type" ) String type, @PathParam( "items" ) String items )
    throws JSONException, UnsupportedEncodingException
  {
    ERDbResourceGenerator docMaker;

    docMaker = new ERDbResourceGenerator();

    docMaker.setCount( items );
    docMaker.setDbName(config.getServletContext().getInitParameter("datasource.mssql"));
    docMaker.setSubject( URLDecoder.decode( subject, "UTF-8" ) );
    docMaker.setType( URLDecoder.decode( type, "UTF-8" ) );

    return docMaker.getJsonResources();
  }
}
