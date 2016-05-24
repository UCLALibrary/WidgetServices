package edu.ucla.library.libservices.webservices.invoices.invoicenumbers.web;

import edu.ucla.library.libservices.webservices.invoices.invoicenumbers.generator.InvoiceNumberGenerator;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path( "/invoices/" )
public class InvoiceNumberService
{
  @Context
  ServletConfig config;

  public InvoiceNumberService()
  {
  }

  @GET
  @Produces( "text/plain" )
  @Path( "next_invoice_number/{bc}" )
  public String getInvoiceNumber( @PathParam( "bc" )
    String code )
  {
    InvoiceNumberGenerator maker;

    maker = new InvoiceNumberGenerator();

    maker.setBranchCode( code );
    maker.setDbName( config.getServletContext().getInitParameter( "datasource.invoice" ) );
    return maker.getInvoiceNumber();
  }

}
