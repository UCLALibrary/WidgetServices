package edu.ucla.library.libservices.webservices.hours.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucla.library.libservices.webservices.hours.beans.HoursBean;

public class HoursMapper
  implements RowMapper
{
  public HoursMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    HoursBean bean;

    bean = new HoursBean();
    bean.setFriClosed( rs.getBoolean( "FR_Closed" ) );
    bean.setFriCloses( rs.getTimestamp( "FR_C" ) );
    bean.setFriNote( rs.getString( "FR_Note" ) );
    bean.setFriOpens( rs.getTimestamp( "FR_O" ) );
    bean.setMonThursClosed( rs.getBoolean( "MT_Closed" ) );
    bean.setMonThursCloses( rs.getTimestamp( "MT_C" ) );
    bean.setMonThursNote( rs.getString( "MT_Note" ) );
    bean.setMonThursOpens( rs.getTimestamp( "MT_O" ) );
    bean.setPeriodTitle( rs.getString( "Period_Title" ) );
    bean.setSatClosed( rs.getBoolean( "SA_Closed" ) );
    bean.setSatCloses( rs.getTimestamp( "SA_C" ) );
    bean.setSatNote( rs.getString( "SA_Note" ) );
    bean.setSatOpens( rs.getTimestamp( "SA_O" ) );
    bean.setSunClosed( rs.getBoolean( "SU_Closed" ) );
    bean.setSunCloses( rs.getTimestamp( "SU_C" ) );
    bean.setSunNote( rs.getString( "SU_Note" ) );
    bean.setSunOpens( rs.getTimestamp( "SU_O" ) );
    bean.setUnitTitle( rs.getString( "Unit_Title" ) );

    return bean;
  }
}
