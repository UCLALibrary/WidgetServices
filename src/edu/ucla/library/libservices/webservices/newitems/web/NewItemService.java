package edu.ucla.library.libservices.webservices.newitems.web;

import edu.ucla.library.libservices.webservices.newitems.generators.NewItemGenerator;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;

@Path( "/newitems/" )
public class NewItemService
{
  @Context
  ServletConfig config;

  public NewItemService()
  {
  }

  @GET
  @Produces( "text/xml" )
  @Path( "xml/subject/{subject}/items/{items}" )
  public String getXml( @PathParam( "subject" )
    String subject, @PathParam( "items" )
    String items )
    throws UnsupportedEncodingException
  {
    NewItemGenerator docMaker;

    docMaker = new NewItemGenerator();

    docMaker.setCount( items );
    docMaker.setDbName( config.getServletContext().getInitParameter("datasource.oracle") );
    docMaker.setSubject( URLDecoder.decode( subject, "UTF-8" ) );

    return docMaker.getXmlResources();
  }

  @GET
  @Produces( "application/json" )
  @Path( "json/subject/{subject}/items/{items}" )
  public String getJson( @PathParam( "subject" )
    String subject, @PathParam( "items" )
    String items )
    throws JSONException, UnsupportedEncodingException
  {
    NewItemGenerator docMaker;

    docMaker = new NewItemGenerator();

    docMaker.setCount( items );
    docMaker.setDbName( config.getServletContext().getInitParameter("datasource.oracle") );
    docMaker.setSubject( URLDecoder.decode( subject, "UTF-8" ) );

    return docMaker.getJsonResources();
  }
}
