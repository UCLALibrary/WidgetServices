package edu.ucla.library.libservices.webservices.reserves.beans;

public class ReservesBean
{
  private String srs_number;
  private String course_name;
  private String location;
  private String item_id;
  private String title;
  private String item_enum;
  private String copy_number;
  private String call_no;
  private String item_status;
  private String library_name;
  private String map_link;

  public ReservesBean()
  {
  }

  public void setCourse_name( String course_name )
  {
    this.course_name = course_name;
  }

  public String getCourse_name()
  {
    return course_name;
  }

  public void setSrs_number( String srs_number )
  {
    this.srs_number = srs_number;
  }

  public String getSrs_number()
  {
    return srs_number;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public String getTitle()
  {
    return title;
  }

  public void setLocation( String location )
  {
    this.location = location;
  }

  public String getLocation()
  {
    return location;
  }

  public void setCall_no( String call_no )
  {
    this.call_no = call_no;
  }

  public String getCall_no()
  {
    return call_no;
  }

  public void setItem_id( String item_id )
  {
    this.item_id = item_id;
  }

  public String getItem_id()
  {
    return item_id;
  }

  public void setItem_enum( String item_enum )
  {
    this.item_enum = item_enum;
  }

  public String getItem_enum()
  {
    return item_enum;
  }

  public void setCopy_number( String copy_number )
  {
    this.copy_number = copy_number;
  }

  public String getCopy_number()
  {
    return copy_number;
  }

  public void setItem_status( String item_status )
  {
    this.item_status = item_status;
  }

  public String getItem_status()
  {
    return item_status;
  }

  public void setLibrary_name( String library_name )
  {
    this.library_name = library_name;
  }

  public String getLibrary_name()
  {
    return library_name;
  }

  public void setMap_link( String map_link )
  {
    this.map_link = map_link;
  }

  public String getMap_link()
  {
    return map_link;
  }
}
