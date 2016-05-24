package edu.ucla.library.libservices.webservices.hours.web;

import edu.ucla.library.libservices.webservices.hours.generators.UnitGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path( "/units" )
public class UnitService
{
  @Context
  ServletConfig config;

  public UnitService()
  {
  }

  @GET
  @Produces( "application/json, text/xml" )
  public Response getUnits()
  {
    UnitGenerator docMaker;

    docMaker = new UnitGenerator();

    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.hours" ) );
    docMaker.generateUnits();

    return Response.ok( docMaker ).build();
  }
}
