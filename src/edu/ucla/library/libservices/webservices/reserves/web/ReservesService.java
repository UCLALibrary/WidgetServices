package edu.ucla.library.libservices.webservices.reserves.web;

import edu.ucla.library.libservices.webservices.reserves.generators.ReservesGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.json.JSONException;

@Path( "/reserves/" )
public class ReservesService
{
  @Context
  ServletConfig config;
  
  public ReservesService()
  {
  }

  @GET
  @Produces( "text/xml" )
  @Path( "xml/srs_number/{srs}" ) ///term/{term}" )
  public String getReserves( @PathParam( "srs" )
    String srs )
  {
    ReservesGenerator docMaker;
    
    docMaker = new ReservesGenerator();
    
    docMaker.setSrs_number( srs );
    docMaker.setDbName( config.getServletContext().getInitParameter("datasource.oracle") );
    return docMaker.getXmlReserves();
  }


  @GET
  @Produces( "application/json" )
  @Path( "json/srs_number/{srs}" ) ///term/{term}" )
  public String getJsonReserves( @PathParam( "srs" )
    String srs )
    throws JSONException
  {
    ReservesGenerator docMaker;

    docMaker = new ReservesGenerator();

    docMaker.setSrs_number( srs );
    docMaker.setDbName( config.getServletContext().getInitParameter("datasource.oracle") );
    return docMaker.getJsonReserves();
  }

}
