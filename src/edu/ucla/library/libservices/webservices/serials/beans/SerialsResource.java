package edu.ucla.library.libservices.webservices.serials.beans;

public class SerialsResource
{
  private String title;
  private String url;
  private String parentID;
  
  public SerialsResource()
  {
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getTitle()
  {
    return title;
  }

  public void setUrl( String url )
  {
    this.url = url;
  }

  public String getUrl()
  {
    return url;
  }

  public void setParentID( String parentID )
  {
    this.parentID = parentID;
  }

  public String getParentID()
  {
    return parentID;
  }
}
