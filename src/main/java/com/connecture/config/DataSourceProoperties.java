package com.connecture.config;

public enum DataSourceProoperties {
  DS_URL("ds.url"),
  DS_USERNAME("ds.username"),
  DS_PASSWORD("ds.password"),
  DS_DRIVER_CLASS("ds.driverClassName");

  private String value;

  DataSourceProoperties(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
