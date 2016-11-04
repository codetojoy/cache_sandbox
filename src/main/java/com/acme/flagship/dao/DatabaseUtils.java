    
package com.acme.flagship.dao;

import java.sql.*;

public class DatabaseUtils {

    public void createDatabase() {
        Connection conn = null;
        Statement statement = null;

        try {
            Class.forName(Constants.JDBC_DRIVER);

            System.out.println("Connecting ...");
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASSWORD);
            System.out.println("Connected database successfully...");

            System.out.println("Creating table ...");
            statement = conn.createStatement();

            String sql = "CREATE TABLE ACCOUNT( account_id bigint NOT NULL, " + 
                                     " username VARCHAR(256) NOT NULL, " +
                                     " status VARCHAR(256) );";

            statement.executeUpdate(sql);
            System.out.println("Created table.");

            // This example uses HSQLDB as an in-memory database, 
            // and so we populate some rows here as a convenience.
            // (Normally, this would be absurd.)

            final String sqlFormat = "INSERT INTO account (account_id, username, status) VALUES (%d,'%s','active');";

            final int NUM_ROWS = 1000;
            for (int i = 1; i <= NUM_ROWS; i++) {
                String insertSql = String.format(sqlFormat, i, "Name_" + i);
                statement.execute(insertSql);
            }

            System.out.println("Populated seed rows.");

        } catch (Exception ex) {
            System.err.println("caught exception : " + ex);
        } finally {
            try { if (statement != null) statement.close(); } catch(Exception ex) {} 
            try { if (conn != null) conn.close(); } catch(Exception ex) {} 
        }
    } 
} 
