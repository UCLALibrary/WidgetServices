package edu.ucla.library.libservices.webservices.hours.db.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.ucla.library.libservices.webservices.hours.beans.UnitBean;

public class UnitMapper
  implements RowMapper
{
  public UnitMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    UnitBean bean;

    bean = new UnitBean();
    bean.setCode(rs.getString( "Unit_Code" ));
    bean.setId(rs.getInt("Unit_ID"));
    bean.setName(rs.getString( "Unit_Title" ));

    return bean;
  }
}
