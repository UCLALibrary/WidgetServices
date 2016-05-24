package edu.ucla.library.libservices.webservices.hours.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType//(name = "adjustmentTypes")
@XmlAccessorType( XmlAccessType.FIELD )
public class UnitBean
{
  @XmlElement( name = "id" )
  int id;
  @XmlElement( name = "code" )
  private String code;
  @XmlElement( name = "name" )
  private String name;

  public UnitBean()
  {
  }

  public void setId( int id )
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public void setCode( String code )
  {
    this.code = code;
  }

  public String getCode()
  {
    return code;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
