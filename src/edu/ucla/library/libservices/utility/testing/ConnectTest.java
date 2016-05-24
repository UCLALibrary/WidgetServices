package edu.ucla.library.libservices.utility.testing;

import java.net.*;

import java.io.*;

public class ConnectTest
{
  public ConnectTest()
  {
  }

  public static void main( String[] args )
    throws MalformedURLException, IOException
  {
    BufferedReader in;
    String inputLine;
    URL service;
    URLConnection connect;

    service = 
        new URL( "http://164.67.152.27:8988/libservices/resources/subject/Economics/type/Electronic%20Journals/items/5" );
    connect = service.openConnection();
    connect.setRequestProperty( "Accept", "application/json" );
    in = 
        new BufferedReader( new InputStreamReader( connect.getInputStream() ) );

    while ( ( inputLine = in.readLine() ) != null )
      System.out.println( inputLine );
    in.close();
  }
}
