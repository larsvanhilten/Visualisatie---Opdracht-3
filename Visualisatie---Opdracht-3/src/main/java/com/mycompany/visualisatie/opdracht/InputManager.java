/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.visualisatie.opdracht;

import java.util.Scanner;

/**
 *
 * @author Lars
 */
public class InputManager {

    private Scanner scanner;

    public InputManager() {
        scanner = new Scanner(System.in);

        showInfo();
    }

    private void showInfo() {
        System.out.println("The following commands are available:");
        System.out.println("'1': Show entirety of Rotterdam");
        System.out.println("'2': Show Wijnhaven");
    }

    public void manageInput() {
        while (true) {
            try {
                System.out.println("What to do?");
                int input = Integer.parseInt(scanner.next());

                switch (input) {
                    case 1:
                        askWaterLevel();
                        Window.setDrawJob(1);
                        break;
                    case 2:
                        askWaterLevel();
                        Window.setDrawJob(2);
                        break;
                    default:
                        System.out.println("Wrong input");
                        break;

                }

            } catch (Exception e) {
                System.out.println("Wrong inpu2t");
                System.out.println(e.toString());
            }
        }

    }

    private void askWaterLevel() {
        System.out.println("waterlevel = hours_passed * 0.5 meter");
        System.out.println("Set the waterlevel (in hours passed):");
        int hoursPassed = Integer.parseInt(scanner.next());
        DataDrawer.setWaterLevel(hoursPassed);

    }

}
