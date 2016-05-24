package edu.ucla.library.libservices.utility.testing;

import edu.ucla.library.libservices.webservices.hours.beans.HoursBean;
import edu.ucla.library.libservices.webservices.hours.generators.HoursGenerator;

import java.util.List;

public class Class1
{
  public Class1()
  {
  }

  public static void main( String[] args )
  {
    HoursGenerator generator;
    List<HoursBean> hours;

    generator = new HoursGenerator();
    generator.setUnitID( 54 );
    hours = generator.getHoursByUnit();
    
    for ( HoursBean theHour : hours )
    {
      System.out.println( theHour.getUnitTitle() + "\t" 
                          + theHour.getMonThursOpens() + "\t" 
                          + theHour.getMonThursCloses() );
    }
  }
}
