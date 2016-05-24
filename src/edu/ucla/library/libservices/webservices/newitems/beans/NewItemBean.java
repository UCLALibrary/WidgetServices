package edu.ucla.library.libservices.webservices.newitems.beans;

public class NewItemBean
{
  private String bib_id;
  private String shortTitle;
  private String fullTitle;
  private String pubDate;

  public NewItemBean()
  {
  }

  public void setBib_id( String bib_id )
  {
    this.bib_id = bib_id;
  }

  public String getBib_id()
  {
    return bib_id;
  }

  public void setShortTitle( String shortTitle )
  {
    this.shortTitle = shortTitle;
  }

  public String getShortTitle()
  {
    return shortTitle;
  }

  public void setFullTitle( String fullTitle )
  {
    this.fullTitle = fullTitle;
  }

  public String getFullTitle()
  {
    return fullTitle;
  }

  public void setPubDate( String pubDate )
  {
    this.pubDate = pubDate;
  }

  public String getPubDate()
  {
    return pubDate;
  }
}
