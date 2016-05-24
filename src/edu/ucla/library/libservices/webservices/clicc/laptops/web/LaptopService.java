package edu.ucla.library.libservices.webservices.clicc.laptops.web;

import com.sun.syndication.io.FeedException;

import edu.ucla.library.libservices.webservices.clicc.laptops.generators.LaptopGenerator;

import java.io.IOException;

import java.net.MalformedURLException;

import javax.servlet.ServletConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path( "/laptops/" )
public class LaptopService
{
  @Context
  ServletConfig config;

  public LaptopService()
  {
  }

  @GET
  @Produces( "application/json" )
  @Path( "json" )
  public String getJson()
    throws MalformedURLException, IOException, FeedException
  {
    LaptopGenerator docMaker;

    docMaker = new LaptopGenerator();

    docMaker.setFeedURL(config.getServletContext().getInitParameter("url.laptops"));

    return docMaker.convertToJson();
  }
}
