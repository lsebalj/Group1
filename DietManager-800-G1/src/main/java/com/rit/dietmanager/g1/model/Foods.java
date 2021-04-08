/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rit.dietmanager.g1.model;

/**
 *
 * @author lukas
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/*
    This class is responsible for reading and interpreting
    foods.csv file. This will be used to access the file and
    return food model or recepie model which will be further used
    within the controler
 */
public class Foods {

    private final FoodHandler foodHandler;

    private ArrayList<Food> allFood;

    //Constructor reads the file initially, but can be refreshed later by calling readFile again
    public Foods(FoodHandler handler) {
        this.foodHandler = handler;
        this.allFood = foodHandler.readFile();
    }

    public ArrayList<Food> getFoods(){
        return this.allFood;
    }

    public String[] getFoodNames() {
        this.allFood = foodHandler.readFile();
        ArrayList<String> foodNames = this.allFood.stream().map(Food::getName).collect(Collectors.toCollection(ArrayList::new));

        String[] str = new String[foodNames.size()];

        for (int j = 0; j < foodNames.size(); j++) str[j] = foodNames.get(j);
        return str;
    }

    public Food findFood(String foodName) {
        for (Food food :allFood) {
            if(food.getName().equals(foodName)) return food;
        }
        System.out.println("Can't find food by name: " + foodName);
        return null;
    }

    public HashMap<Food, Double> getTodayFood() {
        HashMap<String, Double> name_quantity = foodHandler.getFoodHistory();
        HashMap<Food, Double> food_quantity = new HashMap<>();
        name_quantity.keySet().forEach(food -> {
            System.out.println(food);
            food_quantity.put(findFood(food), name_quantity.get(food));
        });

        return food_quantity;
    }

    public HashMap<Food, Double> getTodayFood(String currentDay, String currentMonth, String currentYear) {
        HashMap<String, Double> name_quantity = foodHandler.getFoodHistory(currentDay, currentMonth, currentYear);
        HashMap<Food, Double> food_quantity = new HashMap<>();
        name_quantity.keySet().forEach(food -> {
            System.out.println(food);
            food_quantity.put(findFood(food), name_quantity.get(food));
        });

        return food_quantity;
    }
}

