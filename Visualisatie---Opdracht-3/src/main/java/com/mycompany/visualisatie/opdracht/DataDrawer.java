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
import processing.core.PImage;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Lars
 */
public class DataDrawer {

    private final PApplet applet;
    private static float waterLevel = 0;

    private PImage play, pause, stop, reset;
    private Timer timer;
    private boolean timerActive = false;

    public DataDrawer(PApplet applet) {
        this.applet = applet;
        timer = new Timer();
        play = applet.loadImage("play.png");
        pause = applet.loadImage("pause.png");
        stop = applet.loadImage("stop.png");
        reset = applet.loadImage("reset.png");
    }

    public static void setWaterLevel(int hoursPassed) {
        waterLevel = 0.5f * hoursPassed;
    }

    public void drawRotterdam(ResultSet data) {

        try {
            for (int i = 0; i < 15000; i++) {
                if (data.next()) {
                    int[] mappedData = mapData(data.getDouble("x"), data.getDouble("y"), new float[]{56751.7894897f, 101132.306702f}, new float[]{428547.057678f, 447033.057678f});
                    int mapColor = mapHeight(data.getDouble("z") - waterLevel);
                    int altColor = 255 - mapColor;

                    applet.stroke(0, mapColor, altColor);
                    applet.point(mappedData[0], mappedData[1]);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawWijnhaven(ResultSet data) {
        try {
            for (int i = 0; i < 15000; i++) {
                if (data.next()) {
                    int[] mappedData = mapData(data.getDouble("x"), data.getDouble("y"), new float[]{92858 - 250, 92858 + 250}, new float[]{436940 - 250, 436940 + 250});
                    int mapColor = mapHeight(data.getDouble("z") - waterLevel);
                    int altColor = 255 - mapColor;

                    applet.stroke(0, mapColor, altColor);
                    applet.fill(0, mapColor, altColor);
                    applet.ellipse(mappedData[0], mappedData[1], 4, 4);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int[] mapData(double x, double y, float[] minMaxX, float[] minMaxY) {

        int[] mappedData = {0, 0};

        mappedData[0] = (int) map((float) x, minMaxX[0], minMaxX[1], 0, applet.width);
        mappedData[1] = (int) map((float) y, minMaxY[0], minMaxY[1], applet.height, 0);

        return mappedData;

    }

    private int mapHeight(double height) {
        int heightColor = (int) map((float) height, -5f, 10f, 0, 255);

        return heightColor;
    }

    public void drawWaterLevel() {
        applet.fill(0);
        applet.text("Waterlevel: " + waterLevel + " Meter", 25, 25);
    }

    public void drawButtons() {
        applet.image(play, 200, 10);
        applet.image(pause, 230, 10);
        applet.image(stop, 260, 10);
        applet.image(reset, 290, 10);

    }

    public void startWaterLevel() {
        if(!timerActive){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                waterLevel += 0.5f;
                timerActive = true;
                System.out.println(waterLevel);
            }
        }, 1000, 1000);
        }
    }

    public void pauseWaterLevel() {
        timer.cancel();
    }

    public void stopWaterLevel() {
        timer.cancel();
        waterLevel = 0;
    }

    public void resetWaterLevel() {
        timer.cancel();
        waterLevel = 0;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                waterLevel = +0.5f;
            }
        }, 1000, 1000);
    }
}
