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

    public ResultSet readRotterdam(int nRow) {
        ResultSet results = null;
        try {
            String sql = "SELECT * FROM ROTTERDAM WHERE rowid % ? = 0";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, nRow);

            results = stat.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;

    }

    public ResultSet readWijnhaven(int nRow) {
        ResultSet results = null;
        
        int RDX = 92858;
        int RDY = 436940;
        int range = 250;
        
        try {
            String sql = "SELECT * FROM ROTTERDAM WHERE rowid % ? = 0 AND x BETWEEN ? AND ? AND y BETWEEN ? AND ?";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, nRow);
            
            stat.setInt(2, RDX - range);
            stat.setInt(3, RDX + range);
            
            stat.setInt(4, RDY - range);
            stat.setInt(5, RDY + range);

            results = stat.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return results;
    }

}
