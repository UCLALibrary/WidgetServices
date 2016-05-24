package edu.ucla.library.libservices.webservices.hours.web;

import edu.ucla.library.libservices.webservices.hours.generators.HoursGenerator;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.Response;

import org.json.JSONException;

@Path( "/hours/" )
public class HoursService
{
  @Context
  ServletConfig config;

  public HoursService()
  {
  }

  @GET
  @Produces( "text/xml" )
  @Path( "xml" )
  public String getXml()
    throws UnsupportedEncodingException
  {
    HoursGenerator docMaker;

    docMaker = new HoursGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.hours" ) );

    return docMaker.getXmlHours();
  }

  @GET
  @Produces( "application/json" )
  @Path( "json" )
  public String getJson()
    throws JSONException, UnsupportedEncodingException
  {
    HoursGenerator docMaker;

    docMaker = new HoursGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.hours" ) );

    return docMaker.getJsonHours();
  }

  @GET
  @Produces( "application/json" )
  @Path( "formaljson" )
  public String getFormalJson()
    throws JSONException, UnsupportedEncodingException
  {
    HoursGenerator docMaker;

    docMaker = new HoursGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.hours" ) );

    return docMaker.getFormalJsonHours();
  }

  @GET
  @Produces( "application/json" )
  @Path( "simplejson" )
  public String getSimpleJson()
    throws JSONException, UnsupportedEncodingException
  {
    HoursGenerator docMaker;

    docMaker = new HoursGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.hours" ) );

    return docMaker.getSimpleJsonHours();
  }

  @GET
  @Produces( "application/json, text/xml" )
  @Path( "unit/{unitID}" )
  public Response hoursByUnit(@PathParam( "unitID" )
    int unitID)
  {
    HoursGenerator docMaker;

    docMaker = new HoursGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.hours" ) );
    docMaker.setUnitID( unitID );
    docMaker.getHoursByUnit();

    return Response.ok( docMaker ).build();
  }
}
