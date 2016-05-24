package edu.ucla.library.libservices.webservices.reserves.db.mappers;

import edu.ucla.library.libservices.webservices.reserves.beans.ReservesBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReservesMapper
  implements RowMapper
{
  public ReservesMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    ReservesBean bean;

    bean = new ReservesBean();

    bean.setCall_no(rs.getString("call_no"));
    bean.setCopy_number(rs.getString("copy_number"));
    bean.setCourse_name( rs.getString( "course_name" ) );
    bean.setItem_enum(rs.getString("item_enum"));
    bean.setItem_id(rs.getString("item_id"));
    bean.setItem_status(rs.getString("item_status"));
    bean.setLibrary_name(rs.getString("library_name"));
    bean.setLocation(rs.getString("location"));
    bean.setMap_link(rs.getString("map_link"));
    bean.setSrs_number( rs.getString( "srs_number" ) );
    bean.setTitle(rs.getString("title"));

    return bean;
  }
}
