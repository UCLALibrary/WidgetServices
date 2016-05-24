package edu.ucla.library.libservices.webservices.newitems.db.mappers;

import edu.ucla.library.libservices.webservices.newitems.beans.NewItemBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NewItemMapper
  implements RowMapper
{
  public NewItemMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    NewItemBean bean;
    
    bean = new NewItemBean();
    
    bean.setBib_id(rs.getString("bib_id"));
    bean.setFullTitle(rs.getString("full_title"));
    bean.setPubDate(rs.getString("publisher_date"));
    bean.setShortTitle(rs.getString("short_title"));
    
    return bean;
  }
}
