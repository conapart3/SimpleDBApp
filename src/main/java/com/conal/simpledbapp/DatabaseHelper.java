package com.conal.simpledbapp;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for interactions with database.
 */
public class DatabaseHelper {

    // configuration which reads from system properties and environment variables
    private final AppConfig appConfig;
    // We will use the postgres driver
    private static final String DRIVER = "org.postgresql.Driver";

    public DatabaseHelper() {
        this.appConfig = new AppConfig();
    }

    /**
     * Get connection to the PostgresQL server.
     *
     * @return Connection
     */
    public Connection getConnection() {
        if(appConfig.getDbConnectionUrl()==null){
            System.err.println("System property \"db-connection-url\" is null. This must be set to point to your PostgresQL server");
            System.exit(1);
        }
        if(appConfig.getUser()==null){
            System.err.println("System property \"user\" is null. This must be set to allow access to your PostgresQL server");
            System.exit(1);
        }
        if(appConfig.getPassword()==null){
            System.err.println("System property \"password\" is null. This must be set to allow access to your PostgresQL server");
            System.exit(1);
        }
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(appConfig.getDbConnectionUrl(),
                    appConfig.getUser(), appConfig.getPassword());
        } catch (ClassNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        } catch (SQLException e) {
            System.err.println(e);
            System.exit(1);
        }
        return connection;
    }

    /**
     * Add a server. Call the add_server stored procedure.
     *
     * @param id          server id
     * @param name        server name
     * @param description server description
     */
    public void addServer(String id, String name, String description) {
        String sql = "{call add_server(?,?,?)}";

        try (
                Connection conn = getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, description);

            ResultSet rs = stmt.executeQuery();
            stmt.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Edit an existing server.
     *
     * @param id          the server id to edit
     * @param name        the altered name of the server
     * @param description the altered description of the server
     */
    public void editServer(String id, String name, String description) {
        String sql = "{call edit_server(?,?,?)}";

        try (
                Connection conn = getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, description);

            ResultSet rs = stmt.executeQuery();
            stmt.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Count the servers number of servers
     *
     * @return the number of servers
     */
    public long countServers() {
        String sql = "{call count_servers()}";
        long serverCount = 0;
        try (
                Connection conn = getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                serverCount = Long.parseLong(rs.getString("serverCount"));
            }
            stmt.close();
            conn.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e);
            System.exit(1);
        }
        return serverCount;
    }

    /**
     * List all servers
     *
     * @return the list of servers
     */
    public List<Server> listServers() {
        String sql = "{call list_servers()}";
        List<Server> serverList = new ArrayList<>();

        try (
                Connection conn = getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Server s = new Server();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("description"));
                serverList.add(s);
            }

            stmt.close();
            conn.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e);
            System.exit(1);
        }
        return serverList;
    }

    /**
     * Delete the server specified by the ID
     *
     * @param id server to delete
     */
    public void deleteServer(String id) {
        String sql = "{call delete_server(?)}";

        try (
                Connection conn = getConnection();
                CallableStatement stmt = conn.prepareCall(sql);
        ) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            conn.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    /**
     * Validate that the servers table exists.
     *
     * @return if table exists
     */
    private boolean checkIfTableExists() {
        // If the table does not exist already, create it.
        Statement stmt = null;
        String sql =
                "SELECT EXISTS(\n" +
                        "    SELECT * \n" +
                        "    FROM information_schema.tables \n" +
                        "    WHERE \n" +
                        "      table_schema = 'public' AND \n" +
                        "      table_name = 'servers'\n" +
                        ");";
        return true;
    }

    /**
     * This method is called by the create-schema subcommand to create servers table and stored procedures
     */
    public void createSchema() {
        Connection conn = getConnection();
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new liquibase.Liquibase(
                    "changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (DatabaseException e) {
            System.err.println(e);
            System.exit(1);
        } catch (LiquibaseException e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}
