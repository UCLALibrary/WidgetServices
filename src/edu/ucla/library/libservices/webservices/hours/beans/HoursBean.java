package edu.ucla.library.libservices.webservices.hours.beans;

import java.util.Date;

public class HoursBean
{
  private String unitTitle;
  private String periodTitle;
  private Date monThursOpens;
  private Date monThursCloses;
  private boolean monThursClosed;
  private String monThursNote;
  private Date friOpens;
  private Date friCloses;
  private boolean friClosed;
  private String friNote;
  private Date satOpens;
  private Date satCloses;
  private boolean satClosed;
  private String satNote;
  private Date sunOpens;
  private Date sunCloses;
  private boolean sunClosed;
  private String sunNote;

  public HoursBean()
  {
  }

  public void setUnitTitle( String unitTitle )
  {
    this.unitTitle = unitTitle;
  }

  public String getUnitTitle()
  {
    return unitTitle;
  }

  public void setPeriodTitle( String periodTitle )
  {
    this.periodTitle = periodTitle;
  }

  public String getPeriodTitle()
  {
    return periodTitle;
  }

  public void setMonThursOpens( Date monThursOpens )
  {
    this.monThursOpens = monThursOpens;
  }

  public Date getMonThursOpens()
  {
    return monThursOpens;
  }

  public void setMonThursCloses( Date monThursCloses )
  {
    this.monThursCloses = monThursCloses;
  }

  public Date getMonThursCloses()
  {
    return monThursCloses;
  }

  public void setMonThursClosed( boolean monThursClosed )
  {
    this.monThursClosed = monThursClosed;
  }

  public boolean isMonThursClosed()
  {
    return monThursClosed;
  }

  public void setMonThursNote( String monThursNote )
  {
    this.monThursNote = monThursNote;
  }

  public String getMonThursNote()
  {
    return monThursNote;
  }

  public void setFriOpens( Date friOpens )
  {
    this.friOpens = friOpens;
  }

  public Date getFriOpens()
  {
    return friOpens;
  }

  public void setFriCloses( Date friCloses )
  {
    this.friCloses = friCloses;
  }

  public Date getFriCloses()
  {
    return friCloses;
  }

  public void setFriClosed( boolean friClosed )
  {
    this.friClosed = friClosed;
  }

  public boolean isFriClosed()
  {
    return friClosed;
  }

  public void setFriNote( String friNote )
  {
    this.friNote = friNote;
  }

  public String getFriNote()
  {
    return friNote;
  }

  public void setSatOpens( Date satOpens )
  {
    this.satOpens = satOpens;
  }

  public Date getSatOpens()
  {
    return satOpens;
  }

  public void setSatCloses( Date satCloses )
  {
    this.satCloses = satCloses;
  }

  public Date getSatCloses()
  {
    return satCloses;
  }

  public void setSatClosed( boolean satClosed )
  {
    this.satClosed = satClosed;
  }

  public boolean isSatClosed()
  {
    return satClosed;
  }

  public void setSatNote( String satNote )
  {
    this.satNote = satNote;
  }

  public String getSatNote()
  {
    return satNote;
  }

  public void setSunOpens( Date sunOpens )
  {
    this.sunOpens = sunOpens;
  }

  public Date getSunOpens()
  {
    return sunOpens;
  }

  public void setSunCloses( Date sunCloses )
  {
    this.sunCloses = sunCloses;
  }

  public Date getSunCloses()
  {
    return sunCloses;
  }

  public void setSunClosed( boolean sunClosed )
  {
    this.sunClosed = sunClosed;
  }

  public boolean isSunClosed()
  {
    return sunClosed;
  }

  public void setSunNote( String sunNote )
  {
    this.sunNote = sunNote;
  }

  public String getSunNote()
  {
    return sunNote;
  }
}
