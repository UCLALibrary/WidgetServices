package edu.ucla.library.libservices.webservices.libguides.db.mappers;

import edu.ucla.library.libservices.webservices.libguides.beans.LibGuide;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LibGuideMapper
  implements RowMapper
{
  public LibGuideMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    LibGuide bean;
    bean = new LibGuide();
    bean.setName(rs.getString("libguide_name"));
    bean.setUrl(rs.getString("libguide_url"));
    
    return bean;
  }
}
