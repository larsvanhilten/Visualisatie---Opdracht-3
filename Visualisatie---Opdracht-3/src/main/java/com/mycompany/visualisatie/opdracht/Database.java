/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.visualisatie.opdracht;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lars
 */
public class Database {

    Connection connection;

    public Database() {
        createDatabase();
        createTable();
    }

    private void createDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:coordinates.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createTable(String tablename) {

        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE ROTTERAM_WEST"
                    + "(ID INT PRIMARY KEY     NOT NULL,"
                    + " X           DOUBLE    NOT NULL, "
                    + " Y            DOUBLE     NOT NULL, "
                    + " Z        DOUBLE     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
