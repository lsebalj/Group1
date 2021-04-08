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
public class BasicFood implements Food {

    String name;
    float calories;
    float fat;
    float carb;
    float protein;

    public BasicFood() {
        this.name = null;
        this.calories = -1;
        this.fat = -1;
        this.carb = -1;
        this.protein = -1;
    }

    public BasicFood(String name, float calories, float fat, float carb, float protein) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
    }

    /*
        Check if food item is complete,
        if one of attributes is default, return false
     */
    public boolean isComplete() {
        return this.name != null && this.calories != -1 && this.fat != -1 && this.carb != -1 && this.protein != -1;
    }

    @Override
    public String toString() {
        return "FoodModel{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", fat=" + fat +
                ", carb=" + carb +
                ", protein=" + protein +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) throw new StringIndexOutOfBoundsException("Name can't be a blank string.");
        this.name = name;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        if(calories < 0) throw new NumberFormatException("Calories number incorrectly formed.");
        this.calories = calories;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        if(fat < 0) throw new NumberFormatException("Fat number incorrectly formed.");
        this.fat = fat;
    }

    public float getCarb() {
        return carb;
    }

    public void setCarb(float carb) {
        if(carb < 0) throw new NumberFormatException("Carb number incorrectly formed.");
        this.carb = carb;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        if(protein < 0) throw new NumberFormatException("Protein number incorrectly formed.");
        this.protein = protein;
    }
}

