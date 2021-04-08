/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rit.dietmanager.g1.controller;

/**
 *
 * @author lukas
 */

import com.rit.dietmanager.g1.model.BasicFood;
import com.rit.dietmanager.g1.model.FoodHandler;
import com.rit.dietmanager.g1.model.Recipe;
import com.rit.dietmanager.g1.view.NewJFrame;
import com.rit.dietmanager.g1.model.Logger;
import com.rit.dietmanager.g1.model.Foods;
import com.rit.dietmanager.g1.model.Food;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class FoodController implements ActionListener {

    private final NewJFrame view;
    private final Logger logger;
    private final Foods foods;
    private final FoodHandler foodHandler;
    private float calIntake = 0;
    private float fatIntake = 0;
    private float carbIntake = 0;
    private float proteinIntake = 0;


    private static final String ADD_FOOD = "addFood";
    private static final String ADD_RECIPE = "addRecipe";
    private static final String SET_CALORIES = "setCalIntake";
    private static final String SET_WEIGHT = "setWeight";
    private static final String EAT_FOOD = "addEaten";
    private static final String SET_DATE = "setDate";
    private static final String INSTANTIATE_RECIPE = "instantiateRecipe";
    private static final String ADD_TO_RECIPE = "addToRecipe";

    public FoodController(Foods foods, NewJFrame view, Logger logger) {
        //setup models and view
        this.view = view;
        this.logger = logger;
        this.foods = foods;
        this.foodHandler = new FoodHandler("foods.csv");

        //setup action listeners
        this.view.getFoodBtn().addActionListener(this);
        this.view.getFoodBtn().setActionCommand(ADD_FOOD);

        this.view.getCreateRecipeButton().addActionListener(this);
        this.view.getCreateRecipeButton().setActionCommand(ADD_RECIPE);

        this.view.getCalorieBtn().addActionListener(this);
        this.view.getCalorieBtn().setActionCommand(SET_CALORIES);

        this.view.getCurrentWeightBtn().addActionListener(this);
        this.view.getCurrentWeightBtn().setActionCommand(SET_WEIGHT);

        this.view.getSelectEatenB().addActionListener(this);
        this.view.getSelectEatenB().setActionCommand(EAT_FOOD);

        this.view.getDateBtn().addActionListener(this);
        this.view.getDateBtn().setActionCommand(SET_DATE);

        this.view.getInstantiateRecipeButton().addActionListener(this);
        this.view.getInstantiateRecipeButton().setActionCommand(INSTANTIATE_RECIPE);


        this.refreshFood();
        this.setOverallIntake();
    }


    public void refreshFood() {
        String[] foodToAdd = foods.getFoodNames();
        this.view.updateEatFood(foodToAdd);
    }

    public void setOverallIntake(){
        calIntake = 0;
        fatIntake = 0;
        carbIntake = 0;
        proteinIntake = 0;
        HashMap<Food, Double> food = null;
        overallIntakeCondition();
        this.view.getCalIntakeTF().setText(String.valueOf(calIntake));
        this.view.getFatIntakeTF().setText(String.valueOf(fatIntake));
        this.view.getCarbIntakeTF().setText(String.valueOf(carbIntake));
        this.view.getProteinIntakeTF().setText(String.valueOf(proteinIntake));
    }

    private void overallIntakeCondition() {
        HashMap<Food, Double> food;
        if (!this.view.getDay().equals("") || !this.view.getMonth().equals("") || !this.view.getYear().equals("")) {
            FoodHandler fh = new FoodHandler("log.csv");
            fh.getFoodHistory(this.view.getDay(), this.view.getMonth(), this.view.getYear());
            food = this.foods.getTodayFood(this.view.getDay(), this.view.getMonth(), this.view.getYear());
            for (Food fm : food.keySet()) {
                double quantity = food.get(fm);
                calIntake += (fm.getCalories() * quantity);
                fatIntake += (fm.getFat() * quantity);
                carbIntake += (fm.getCarb() * quantity);
                proteinIntake += (fm.getProtein() * quantity);
                System.out.println(calIntake);
            }
        } else {
            food = this.foods.getTodayFood();
            for (Food fm : food.keySet()) {
                double quantity = food.get(fm);
                calIntake += (fm.getCalories() * quantity);
                fatIntake += (fm.getFat() * quantity);
                carbIntake += (fm.getCarb() * quantity);
                proteinIntake += (fm.getProtein() * quantity);
                System.out.println(calIntake);
            }
        }
    }

    /*
        View action recognition and handling
     */
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        System.out.println("command: " + ae.getActionCommand());
        viewHandling(action);
        setOverallIntake();
    }

    private void viewHandling(String action) {
        if (!action.equals(ADD_FOOD)) {
            switch (action) {
                case ADD_RECIPE:
                    String recipeName = this.view.getRecipeNameTF();
                    BasicFood food = this.foodHandler.findFoodByName(this.view.getFoodCountTF());
                    Recipe recipe = foodHandler.findRecipeByName(recipeName);

                    if (recipe == null) {
                        Recipe newRecipe = new Recipe(this.view.getRecipeNameTF());
                        newRecipe.addFood(food, Float.parseFloat(this.view.getServings()));
                        foodHandler.log(newRecipe);
                    } else {
                        //                this.view.dialogError("This recipe is already instantiated!\n");
                        recipe.addFood(food, Float.parseFloat(this.view.getServings()));
                        foodHandler.log(recipe);
                    }
                    this.view.clearRecipeInputs();
                    break;
                case SET_CALORIES:
                    try {
                        this.logger.logCalorieLimit(Float.parseFloat(this.view.getCalorieIntake()));
                        this.view.dialogError("Calorie limit logged successfully!");
                    } catch (NumberFormatException nfe) {
                        System.out.println("EXCEPTION! Incorrect input when adding caliories.");
                        this.view.dialogError("Calorie limit log error!\n" + nfe.getMessage());
                    }
                    break;
                case SET_WEIGHT:
                    System.out.println("set weight... Work in progress..");
                    break;
                case EAT_FOOD:
                    String selected = this.view.getSelectedFoodToBeEaten();
                    this.logger.logFood(selected, Double.parseDouble(this.view.getServings()));
                    this.view.dialogError("model.Food successfully logged!");
                    setOverallIntake();
                    break;
                case SET_DATE:
                    setOverallIntake();
                    break;
            }
        } else {
            BasicFood basicFood = new BasicFood();
            try {
                basicFood.setName(this.view.getFoodName());
                basicFood.setCalories(Float.parseFloat(this.view.getFoodCal()));
                basicFood.setFat(Float.parseFloat(this.view.getFoodFat()));
                basicFood.setCarb(Float.parseFloat(this.view.getFoodCarb()));
                basicFood.setProtein(Float.parseFloat(this.view.getFoodProtein()));
            } catch (NumberFormatException | StringIndexOutOfBoundsException nfe) {
                this.view.dialogError("Error encountered while adding food!\n" + nfe.getLocalizedMessage());
            }

            if (basicFood.isComplete()) {
                this.foodHandler.log(basicFood);
                this.view.clearFoodInputs();
                this.view.dialogError("model.Food added successfully!");
            }
        }
    }
}

