/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.visualisatie.opdracht;

import java.sql.ResultSet;
import processing.core.PApplet;

/**
 *
 * @author Lars
 */
public class Window extends PApplet {

    private DataDrawer dataDrawer;
    private ResultSet data;

    @Override
    public void setup() {
        size(800, 500);

        Database database = new Database();
        dataDrawer = new DataDrawer(this);
        
        data = database.readData("ROTTERDAM_OOST", 6);
        

    }

    @Override
    public void draw() {
        dataDrawer.drawData(data);
    }
}
