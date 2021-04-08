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

import com.rit.dietmanager.g1.model.BasicFood;
import com.rit.dietmanager.g1.model.Food;

import java.util.HashMap;

public class Recipe implements Food {

    private HashMap<BasicFood, Float> foods = new HashMap<BasicFood, Float>();
    private String name;

    //This is actual data read from file, array of strings. This is used so we
    //can Identify if this recipe is composed of other recipes
    private String[] compositionFromFile;

    public Recipe(String name) { this.name = name; }

    public Recipe(String name, String[] composition) {
        this.name = name;
        this.compositionFromFile = composition;
    }
    
    public Recipe(String name, HashMap<BasicFood, Float> foods ) {
        this.name = name;
        this.foods = foods;
    }

    public String getCompositionFromFile(int index) {
        return compositionFromFile[index];
    }
    public int getCompositionLength() {
        return compositionFromFile.length;
    }


    public void setCompositionFromFile(String[] compositionFromFile) {
        this.compositionFromFile = compositionFromFile;
    }

    @Override
    public String toString() {
        return "RecipeModel{" +
                "name='" + name+
                ", foods=" + foods  + '\'' +
                '}';
    }

    public HashMap<BasicFood, Float> getFoods() {
        return foods;
    }

    public void setFoods(HashMap<BasicFood, Float> foods) {
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFood(BasicFood basicFood, float quantity) {
        if(basicFood != null) this.foods.put(basicFood, quantity);
    }
    
    public void removeFood(BasicFood basicFood) {
        this.foods.remove(basicFood);
    }

    /*
        Gets total calories for this recepie
     */
    public float getCalories() {
        float calories = 0;
        for (BasicFood basicFood : foods.keySet()) { //So it's food calories * quantitiy of food in recepie
            calories += basicFood.getCalories() * foods.get(basicFood);
        }
        return calories;
    }
    /*
        Gets total fat for this recepie
     */
    public float getFat() {
        float fat = 0;
        for (BasicFood basicFood : foods.keySet()) {//So it's food fat * quantitiy of food in recepie
            fat += basicFood.getFat() * foods.get(basicFood);
        }
        return fat;
    }
    /*
        Gets total carb for this recepie
     */
    public float getCarb() {
        float carb = 0;
        for (BasicFood basicFood : foods.keySet()) {//So it's food carbs * quantitiy of food in recepie
            carb += basicFood.getCarb() * foods.get(basicFood);
        }
        return carb;
    }
    /*
        Gets total protein for this recepie
     */
    public float getProtein() {
        float protein = 0;
        for (BasicFood basicFood : foods.keySet()) {//So it's food protein * quantitiy of food in recepie
            protein += basicFood.getProtein() * foods.get(basicFood);
        }
        return protein;


    }
}

