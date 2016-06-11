/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.visualisatie.opdracht;


/**
 *
 * @author Lars
 */
public class Main {
    
    public static void main(String[] args){
        Window.main("com.mycompany.visualisatie.opdracht.Window");
        InputManager inputManager = new InputManager();
        inputManager.manageInput();
    }
}
