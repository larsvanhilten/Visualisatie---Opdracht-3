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

    private Connection connection;

    public Database() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:coordinates.db");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet readData(String tablename, int nRow) {
        ResultSet results = null;
        try {
            String sql = "SELECT X, Y,Z FROM " + tablename + " WHERE ID%? = 0 ORDER BY ID";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, nRow);

            results = stat.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;

    }

}
