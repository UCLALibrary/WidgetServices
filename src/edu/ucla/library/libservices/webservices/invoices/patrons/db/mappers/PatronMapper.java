package edu.ucla.library.libservices.webservices.invoices.patrons.db.mappers;

import edu.ucla.library.libservices.webservices.invoices.patrons.beans.PatronBean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PatronMapper
  implements RowMapper
{
  public PatronMapper()
  {
  }

    public Object mapRow( ResultSet rs, int i )
      throws SQLException
    {
      PatronBean bean;

      bean = new PatronBean();

      bean.setBarcode(rs.getString("patron_barcode"));
      bean.setFirstName(rs.getString("normal_first_name"));
      bean.setLastName(rs.getString("normal_last_name"));
      bean.setPatronID(rs.getInt("patron_id"));
      
      return bean;
    }
}
