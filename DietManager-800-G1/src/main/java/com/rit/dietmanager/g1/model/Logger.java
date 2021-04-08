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
import java.util.Date;

public class Logger {

    //Attributes
    private final String file;
    private final Date date = new Date();
    private PrintWriter pw = null;
    private FileWriter fw = null;

    //Constructor
    public Logger() {
        file = "log.csv";
    }

    //logFood method that will log the food to the log.csv file
    public void logFood(String foodName, double servings) {
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw, true);
        } catch(IOException ioe) {
            System.out.println("Error, in creating writer objects: " + ioe.getMessage());
            ioe.printStackTrace();
            return;
        }
        //These need to change to date,name,type,typeVal
        String comma = ",";
        int currentYear = date.getYear() + 1900;
        int currentMonth = date.getMonth() + 1;

        pw.print("\n");
        pw.print(currentYear + comma);
        pw.print(currentMonth + comma);
        pw.print(date.getDate() + comma);
        pw.print('f' + comma);
        pw.print(foodName + comma);
        pw.print(servings);
        pw.flush();
        pw.close();
        System.out.println("model.Food logged");
    }

    //logWeight method that will log the weight to the log.csv file
    public boolean logWeight(float weight) {
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw, true);
        } catch(IOException ioe) {
            System.out.println("Error, in creating writer objects: " + ioe.getMessage());
            ioe.printStackTrace();
            return false;
        }
        //These need to change to date,name,type,typeVal
        String comma = ",";
        int currentYear = date.getYear() + 1900;
        int currentMonth = date.getMonth() + 1;
        pw.print("\n");
        pw.print(currentYear + comma);
        pw.print(currentMonth + comma);
        pw.print(date.getDate() + comma);
        pw.print('w' + comma);
        pw.print(weight);
        pw.flush();
        pw.close();
        System.out.println("Weight logged");
        return true;
    }

    //logCalorieLimit method that will log the calorie limit to the log.csv file
    public void logCalorieLimit(float calorieLimit) {
        if(calorieLimit < 0) throw new NumberFormatException("Calorie limit cannot be less than 0");
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw, true);
        } catch(IOException ioe) {
            System.out.println("Error, in creating writer objects: " + ioe.getMessage());
            ioe.printStackTrace();
            return;
        }
        //These need to change to date,name,type,typeVal
        String comma = ",";
        int currentYear = date.getYear() + 1900;
        int currentMonth = date.getMonth() + 1;
        pw.print("\n");
        pw.print(currentYear + comma);
        pw.print(currentMonth + comma);
        pw.print(date.getDate() + comma);
        pw.print('c' + comma);
        pw.print(calorieLimit);
        pw.flush();
        pw.close();
        System.out.println("Calorie Limit logged");
    }
}

