package com.conal.simpledbapp;

/**
 * App configuration, reads in system properties and environment variables.
 */
public class AppConfig {
    private String dbConnectionUrl;
    private String user;
    private String password;

    public AppConfig() {
        dbConnectionUrl = System.getProperty("db-connection-url", System.getenv("db-connection-url"));
        user = System.getProperty("user", System.getenv("user"));
        password = System.getProperty("password", System.getenv("password"));
    }

    public String getDbConnectionUrl() {
        return dbConnectionUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
