package edu.ucla.library.libservices.webservices.invoices.patrons.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"patronID", "barcode" , "lastName", "firstName" })
public class PatronBean
{
  private int patronID;
  private String barcode;
  private String lastName;
  private String firstName;

  public PatronBean()
  {
  }

  public void setPatronID( int patronID )
  {
    this.patronID = patronID;
  }

  public int getPatronID()
  {
    return patronID;
  }

  public void setBarcode( String barcode )
  {
    this.barcode = barcode;
  }

  public String getBarcode()
  {
    return barcode;
  }

  public void setLastName( String lastName )
  {
    this.lastName = lastName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setFirstName( String firstName )
  {
    this.firstName = firstName;
  }

  public String getFirstName()
  {
    return firstName;
  }
}
