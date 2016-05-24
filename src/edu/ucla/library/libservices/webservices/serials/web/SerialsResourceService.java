package edu.ucla.library.libservices.webservices.serials.web;

import edu.ucla.library.libservices.webservices.serials.generators.SerialsResourceGenerator;

import java.io.IOException;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.JSONException;

import org.xml.sax.SAXException;

@Path( "/serials/" )
public class SerialsResourceService
{
  @Context
  ServletConfig config;

  public SerialsResourceService()
  {
  }

  @GET
  @Produces( "application/json" )
  @Path( "subject/{subject}" )
  public String getJson( @PathParam( "subject" )
    String subject )
    throws ParserConfigurationException, SAXException, IOException, 
           XPathExpressionException, JSONException
  {
    SerialsResourceGenerator generator;

    generator = new SerialsResourceGenerator();
    generator.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    generator.setSubject( subject );
    
    return generator.getResources();
  }
}
