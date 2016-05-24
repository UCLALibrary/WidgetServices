package edu.ucla.library.libservices.webservices.libguides.beans;

public class LibGuide
{
  private String name;
  private String url;

  public LibGuide()
  {
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void setUrl( String url )
  {
    this.url = url;
  }

  public String getUrl()
  {
    return url;
  }
}
