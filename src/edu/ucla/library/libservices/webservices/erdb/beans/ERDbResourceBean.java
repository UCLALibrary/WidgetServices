package edu.ucla.library.libservices.webservices.erdb.beans;

public class ERDbResourceBean
{
  private String ccle_dept;
  private int rank;
  private int title_id;
  private String title;
  private String url;
  private String type;
  private int total;

  public ERDbResourceBean()
  {
  }

  public void setTitle_id( int title_id )
  {
    this.title_id = title_id;
  }

  public int getTitle_id()
  {
    return title_id;
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

  public void setType( String type )
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }

  public void setTotal( int total )
  {
    this.total = total;
  }

  public int getTotal()
  {
    return total;
  }

  public void setCcle_dept( String ccle_dept )
  {
    this.ccle_dept = ccle_dept;
  }

  public String getCcle_dept()
  {
    return ccle_dept;
  }

  public void setRank( int rank )
  {
    this.rank = rank;
  }

  public int getRank()
  {
    return rank;
  }
}
