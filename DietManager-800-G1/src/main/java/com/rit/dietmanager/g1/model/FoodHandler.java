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

import java.io.*;
import java.util.*;

/*
    This class will be used to write to foods.csv file
    This class takes in FoodReader instance so we can
    refresh it's cache of foods once done logging
 */
public class FoodHandler {

    //Attributes
    private final String file;
    private PrintWriter pw = null;
    private FileWriter fw = null;
    private final Foods reader = null;

    private final ArrayList<Recipe> recipes = new ArrayList<>();
    private final ArrayList<BasicFood> basicFoods = new ArrayList<>();

    //Constructor
    public FoodHandler(String filename) {
        this.file = filename;
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }

    /* MOVE TO FILEHANDLER
   This function is used to refresh the file. This means that once invoked,
   it will read foods file and set foods as attributes to this class. If invoked again,
   it will clear previous foods and set new foods, meaning it could be used  as
   refresh file function.
*/
    public ArrayList<Food> readFile() {
        List<List<String>> records = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));

                if(values[0].equals("b")) {
                    BasicFood food = constructFood(values);
                    basicFoods.add(food);
                    foods.add(food);
                }
                else {
                    Recipe recipe = constructRecipe(values);
                    recipes.add(constructRecipe(values));
                    foods.add(recipe);
                }
            }
            System.out.println(".csv data refreshed");
        } catch (IOException e) {
            System.out.println("There was a problem reading foods.csv, please check if it is in the root directory.");
            e.printStackTrace();
        }
        return foods;
    }

    public BasicFood constructFood(String[] values) {
        BasicFood newBasicFood = new BasicFood();

        try{
            newBasicFood.setName(values[1]);
            newBasicFood.setCalories(Float.parseFloat(values[2]));
            newBasicFood.setFat(Float.parseFloat(values[3]));
            newBasicFood.setCarb(Float.parseFloat(values[4]));
            newBasicFood.setProtein(Float.parseFloat(values[5]));
//            System.out.println(newFood.toString());
            return newBasicFood;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("FoodReader -> addFood() received incomplete food, ignoring row...");
            return null;
        }
        catch (NumberFormatException nfe) {
            System.out.println("FoodReader -> addFood() NumberFormatException, food wasn't logged correctly");
            System.out.println(nfe.getLocalizedMessage());
            System.out.println(nfe.getMessage());
            return null;
        }
    }

    /*
        Parse recepie line and add it as object
     */
    public Recipe constructRecipe(String[] values) {
        String recipeName = values[1];

        //init recipe
        Recipe recipe = new Recipe(recipeName, values);


        loopThroughRecipePosition(values, recipe);
//        System.out.println(recipe.toString());
        return recipe;
    }

    /* 0 position tells us it's recipe, 1 position gives us the name
    //of recipe, and then positions 2/3 give us foodName/quantity
    //due to unknown amount of foods in recipe, we have to loop through
    //this until we reach end*/
    private void loopThroughRecipePosition(String[] values, Recipe recipe) {
        for (int i = 2; i < values.length-1; i=i+2) {
            System.out.println("Checking if food " + values[i] + " exists...");
            BasicFood basicFood = findFoodByName(values[i]);

            if(basicFood == null) { //If our food is null, check if recipe with that name exists
                System.out.println("Checking if recipe " + values[i] + " exists...");
                Recipe subRecipe = findRecipeByName(values[i]);
                //for every food within our recipe, add it and it's quantitiy to the new recipe
                for (BasicFood subBasicFood : subRecipe.getFoods().keySet()) {
                    recipe.addFood(subBasicFood, subRecipe.getFoods().get(subBasicFood));
                }

                System.out.println("Exists!");
            }
            float quantity = Float.parseFloat(values[i+1]);
            recipe.addFood(basicFood, quantity);
        }
    }

    /*
        Find food by name (recipe or basic)
     */
    public Food findByName(String name) {
        Food food = findFoodByName(name);
        if(food == null) food = findRecipeByName(name);
        System.out.println("model.Food not found: " + name);
        return food;
    }

    /*
        Find food model by name of the food
     */
    public BasicFood findFoodByName(String name) {
        return basicFoods.stream().filter(basicFood -> basicFood.getName().equals(name)).findFirst().orElse(null);
        //If null returned, no food with that name exists
    }

    /*
        Find recepie model by name of the food
     */
    public Recipe findRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if(recipe.getName().equals(name)) return recipe;
        }
        return null; //If null returned, no food with that name exists
    }

    /*
        Log method can take either FoodModel or model.Recipe model and write
        it to foods.csv file.
     */
    public void log(BasicFood basicFood) {
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw, true);
        } catch(IOException ioe) {
            System.out.println("Error, in creating writer objects: " + ioe.getMessage());
            ioe.printStackTrace();
            return;
        }

        String comma = ",";
        String FOOD_CHAR = "b";
        pw.print("\n" + FOOD_CHAR + comma);
        pw.print(basicFood.getName() + comma);
        pw.print(basicFood.getCalories() + comma);
        pw.print(basicFood.getFat() + comma);
        pw.print(basicFood.getCarb() + comma);
        pw.print(basicFood.getProtein());
        pw.flush();
        pw.close();
        System.out.println("model.Food recorded to " + this.file);
    }

    //r,Bad Dinner,Pizza Slice,2.0,HotDog+Bun,2.0,Potato Salad (cup),1.8,Cola (can),1.0,Berry Pie Slice,1.5
    /*
        Log method can take either FoodModel or model.Recipe model and write
        it to foods.csv file. This class takes in FoodReader instance so
        we can refresh it's cache of foods once done logging
     */
    public void log(Recipe recipe) {
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw, true);
        } catch(IOException ioe) {
            System.out.println("Error, in creating writer objects: " + ioe.getMessage());
            ioe.printStackTrace();
            return;
        }

        String comma = ",";
        String RECEPIE_CHAR = "r";
        pw.print("\n" + RECEPIE_CHAR + comma);
        pw.print(recipe.getName() + comma);

        for (int i = 2; i < recipe.getCompositionLength()-1; i=i+2) {
            String ingredientName = recipe.getCompositionFromFile(i);
            float ingredientQuantitiy = Float.parseFloat(recipe.getCompositionFromFile(i+1));

            pw.print(ingredientName + comma);
            //if we are on last ingredient, don't write comma after it
            if(i+1 != recipe.getCompositionLength()) pw.print(ingredientQuantitiy + comma);
            else pw.print(ingredientQuantitiy);
        }

        pw.flush();
        pw.close();
        System.out.println("model.Recipe recorded to " + this.file);
    }

    public HashMap<String, Double> getTodayFood(String currentDay, String currentMonth, String currentYear) {
        HashMap<String, Double> todayFoods = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("log.csv"))) {
            String line;

            boolean add = true;

            while ((line = br.readLine()) != null) {
            try{
                String[] values = line.split(",");
                add = values[0].equals(currentYear);
                if (!values[1].equals(currentMonth)) add = false;
                if (!values[2].equals(currentDay)) add = false;

                String anyFood = values[4];
                if (add) {
                    //if key exists, add to it
                    if (todayFoods.containsKey(anyFood)) {
                        Double quantity = todayFoods.get(anyFood) + Double.parseDouble(values[5]);
                        todayFoods.put(anyFood, quantity);
                    } //else create key
                    else todayFoods.put(anyFood, Double.parseDouble(values[5]));
                }
            } catch (ArrayIndexOutOfBoundsException aiob) {
                    System.out.println("Non food item scanned probably.");
                }
            }
        } catch (IOException e) {
            System.out.println("There was a problem reading foods.csv, please check if it is in the root directory.");
            e.printStackTrace();
        }

        return todayFoods;
    }

    public HashMap<String, Double> getFoodHistory(String currentDay, String currentMonth, String currentYear) {
        return getTodayFood(currentDay, currentMonth, currentYear);
    }

    public HashMap<String, Double> getFoodHistory() {
        Date date = new Date();
        String currentDay = String.valueOf(date.getYear() + 1900);
        String currentMonth = String.valueOf(date.getMonth() + 1);
        String currentYear = String.valueOf(date.getDate());
        return getTodayFood(currentDay, currentMonth, currentYear);
    }

}
