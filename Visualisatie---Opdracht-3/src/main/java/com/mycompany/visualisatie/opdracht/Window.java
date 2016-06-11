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
    private static int drawJob = 0;
    private int oldJob = 0;
    private Database database;

    public static void setDrawJob(int drawJob) {
        Window.drawJob = drawJob;

    }

    @Override
    public void setup() {
        size(1200, 800);
        database = new Database();
        dataDrawer = new DataDrawer(this);

        data = database.readRotterdam(6);
        noSmooth();

    }

 
    @Override
    public void draw() {
        if (oldJob != drawJob) {
            background(200);
            switch (drawJob) {
                case 1:
                    data = database.readRotterdam(6);
                    break;
                case 2:
                    data = database.readWijnhaven(1);
                    break;
            }
        }
        switch (drawJob) {
            case 1:
                dataDrawer.drawRotterdam(data);
                oldJob = 1;
                break;
            case 2:
                dataDrawer.drawWijnhaven(data);
                oldJob = 2;
                break;

        }
        dataDrawer.drawWaterLevel();
        dataDrawer.drawButtons();
    }

    @Override
    public void mousePressed() {

        if (mouseX > 200 && mouseX < 230) {
            if (mouseY > 10 && mouseY < 40) {
                dataDrawer.startWaterLevel();
            }
        }

        if (mouseX > 230 && mouseX < 260) {
            if (mouseY > 10 && mouseY < 40) {
                dataDrawer.pauseWaterLevel();
            }
        }

        if (mouseX > 260 && mouseX < 290) {
            if (mouseY > 10 && mouseY < 40) {
                dataDrawer.stopWaterLevel();
            }
        }

        if (mouseX > 290 && mouseX < 320) {
            if (mouseY > 10 && mouseY < 40) {
                dataDrawer.resetWaterLevel();
            }
        }
    }
}
