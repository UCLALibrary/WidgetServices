package edu.ucla.library.libservices.webservices.libguides.web;

import edu.ucla.library.libservices.webservices.libguides.generators.LibGuideGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;

@Path( "/guides/" )
public class LibGuideService
{
  @Context
  ServletConfig config;

  public LibGuideService()
  {
  }

  @GET
  @Produces( "application/json" )
  @Path( "json/{subject}" )
  public String getJson( @PathParam( "subject" )
    String subject )
    throws JSONException
  {
    LibGuideGenerator generator;

    generator = new LibGuideGenerator();
    generator.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    generator.setSubject( subject );

    return generator.getJsonGuides();
  }
}
