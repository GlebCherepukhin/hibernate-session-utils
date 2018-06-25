package com.connecture.config;

public enum HibernateProperties {

  HIBERNATE_DIALECT("hibernate.dialect"),
  HIBERNATE_SHOW_SQL("hibernate.show_sql"),
  HIBERNATE_DDL_AUTO("hibernate.ddl-auto"),
  HIBERNATE_SESSION_CONTEXT("hibernate.current_session_context_class"),
  HIBERNATE_SQL_COMMENTS("hibernate.use_sql_comments"),
  HIBERNATE_FORMAT_SQL("hibernate.format_sql");

  private String value;

  HibernateProperties(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}