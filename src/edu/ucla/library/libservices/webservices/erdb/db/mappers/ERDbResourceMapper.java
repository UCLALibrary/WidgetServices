package edu.ucla.library.libservices.webservices.erdb.db.mappers;

import edu.ucla.library.libservices.webservices.erdb.beans.ERDbResourceBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ERDbResourceMapper
  implements RowMapper
{
  public ERDbResourceMapper()
  {
  }

  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    ERDbResourceBean bean;

    bean = new ERDbResourceBean();

    bean.setCcle_dept(rs.getString("ccle_dept"));
    bean.setRank(rs.getInt("rank"));
    bean.setTitle( rs.getString( "title" ) );
    bean.setTitle_id( rs.getInt( "title_id" ) );
    bean.setTotal( rs.getInt( "total" ) );
    bean.setType( rs.getString( "type" ) );
    bean.setUrl( rs.getString( "url" ) );

    return bean;
  }
}
