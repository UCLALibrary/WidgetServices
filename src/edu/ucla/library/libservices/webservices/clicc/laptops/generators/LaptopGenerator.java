package edu.ucla.library.libservices.webservices.clicc.laptops.generators;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

import com.sun.syndication.io.XmlReader;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Iterator;


public class LaptopGenerator
{
  private String feedURL;
  
  public LaptopGenerator()
  {
  }

  public void setFeedURL( String feedURL )
  {
    this.feedURL = feedURL;
  }

  public String getFeedURL()
  {
    return feedURL;
  }
  
  public String convertToJson()
    throws MalformedURLException, IOException, FeedException
  {
    URL url;
    XmlReader reader;
    SyndFeed feed;
    StringBuffer output;

    url = new URL( getFeedURL() );
    reader = null;
    output = new StringBuffer("{ [");

    try
    {
      reader = new XmlReader( url );
      feed = new SyndFeedInput().build( reader );
      
      for ( Iterator i = feed.getEntries().iterator(); i.hasNext(); )
      {
        SyndEntry entry;
        entry = ( SyndEntry ) i.next();
        
        output.append("\n{\"feed\":\"").append(feed.getTitle()).append("\",");
        output.append("\"entry\":\"").append(entry.getTitle()).append("\",");
        output.append("\"descript\":\"").append(entry.getDescription().getValue()).append("\",");
        output.append("\"link\":\"").append( entry.getLink() ).append("\"").append("}");;
        if ( i.hasNext() )
          output.append(",");
      }
    }
    finally
    {
      if ( reader != null )
        reader.close();
    }
    output.append( "\n] }" );

    return output.toString();
  }
}
