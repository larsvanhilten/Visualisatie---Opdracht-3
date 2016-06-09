/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.visualisatie.opdracht;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.map;

/**
 *
 * @author Lars
 */
public class DataDrawer {

    private PApplet applet;

    public DataDrawer(PApplet applet) {

        this.applet = applet;
    }

    public void drawData(ResultSet data) {
        try {
            while (true) {
                for (int i = 0; i < 50; i++) {

                    int[] mappedData = mapData(data.getDouble("x"), data.getDouble("y"), "ROTTERDAM_OOST");
                    applet.fill(255, 255, 255);
                    applet.point(mappedData[0], mappedData[1]);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int[] mapData(double x, double y, String tablename) {

        //volgens mij kloppen min en max niet?
        int[] mappedData = {0, 0};
        mappedData[0] = (int) map((float) x, 85630, 101132, 0, applet.width);
        mappedData[1] = (int) map((float) y, 440921, 445495, 0, applet.height - 50);

        System.out.println(mappedData[0]);
        System.out.println(mappedData[1]);
        return mappedData;

    }
}
