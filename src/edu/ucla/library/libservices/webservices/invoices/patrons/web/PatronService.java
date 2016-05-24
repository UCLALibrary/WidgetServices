package edu.ucla.library.libservices.webservices.invoices.patrons.web;

import edu.ucla.library.libservices.webservices.invoices.patrons.generator.PatronGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path( "/patrons/" )
public class PatronService
{
  @Context
  ServletConfig config;

  public PatronService()
  {
  }


  @GET
  @Produces( "application/json" )
  @Path( "patron_record/{bc}" )
  public PatronGenerator jsonByBarcode( @PathParam( "bc" )
    String barcode )
  {
    PatronGenerator docMaker;

    docMaker = new PatronGenerator();

    docMaker.setBarcode( barcode );
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    docMaker.getPatronsByBarcode();

    return docMaker;
  }

  @GET
  @Produces( "text/xml" )
  @Path( "patron_record/{bc}" )
  public PatronGenerator xmlByBarcode( @PathParam( "bc" )
    String barcode )
  {
    PatronGenerator docMaker;

    docMaker = new PatronGenerator();

    docMaker.setBarcode( barcode );
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    docMaker.getPatronsByBarcode();

    return docMaker;
  }

  @GET
  @Produces( "application/json" )
  @Path( "patron_list/{name}" )
  public PatronGenerator jsonByName( @PathParam( "name" )
    String lastName )
  {
    PatronGenerator docMaker;

    docMaker = new PatronGenerator();

    docMaker.setLastName( lastName );
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    docMaker.getPatronsByName();

    return docMaker;
  }

  @GET
  @Produces( "text/xml" )
  @Path( "patron_list/{name}" )
  public PatronGenerator xmlByName( @PathParam( "name" )
    String lastName )
  {
    PatronGenerator docMaker;

    docMaker = new PatronGenerator();

    docMaker.setLastName( lastName );
    docMaker.setDbName( config.getServletContext().getInitParameter( "datasource.oracle" ) );
    docMaker.getPatronsByName();

    return docMaker;
  }
}
