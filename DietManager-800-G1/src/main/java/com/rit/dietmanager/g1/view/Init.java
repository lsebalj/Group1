/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rit.dietmanager.g1.view;

/**
 *
 * @author lukas
 */

import com.rit.dietmanager.g1.controller.FoodController;
import com.rit.dietmanager.g1.model.FoodHandler;
import com.rit.dietmanager.g1.model.Foods;
import com.rit.dietmanager.g1.model.Logger;

public class Init {
    public static void main(String[] args) {
        FoodHandler handler = new FoodHandler("foods.csv");
        Foods foods = new Foods(handler);
        NewJFrame view = new NewJFrame();
        view.setVisible(true);
        Logger logger = new Logger();
        FoodController controller = new FoodController(foods,view, logger);
    }
}

