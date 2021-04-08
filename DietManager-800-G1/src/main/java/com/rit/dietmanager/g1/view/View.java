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

import javax.swing.*;
import java.awt.*;

public class View {

    public static JButton addFoodBtn;
    public static JButton addRecipeBtn;
    public static JButton calBtn;
    public static JButton jbWeight;
    public static JButton selectEatenB;
    public static JTextField nametf;
    public static JTextField caltf;
    public static JTextField fattf;
    public static JTextField carbtf;
    public static JTextField proteintf;
    public static JTextField recipeNametf;
    public static JTextField firstFoodNametf;
    public static JTextField firstFoodCounttf;
    public static JTextField calGoaltf;
    public static JTextField tfCal;
    public static JTextField tfFat;
    public static JTextField tfCarb;
    public static JTextField tfProtein;
    public static JTextField tfNeeded;
    public static JTextField servingstf;
    private JFrame f;
    private JComboBox<String> jcbFood;
    public static JTextField daytf;
    public static JTextField monthtf;
    public static JTextField yeartf;
    public static JButton setDateBtn;

    public View() {
    //public void createDispVilay() {
        String[] foodArr = {"(model.)Foods", "Pancakes", "bla bla", "neki food"};
        String[] recipeArr = {"Recipes", "Pancakes", "bla bla", "neki food"};


        createFrame(foodArr, recipeArr);
    }

    private void createFrame(String[] foodArr, String[] recipeArr) {
        JFrame frame = new JFrame("Diet Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 1000);


        JPanel foodPanel = new JPanel(); // the panel is not visible in output
        JLabel namejl = new JLabel("(model.)Food name");
        nametf = new JTextField(10);
        JLabel caljl = new JLabel("Calories");
        caltf = new JTextField(10);
        JLabel fatjl = new JLabel("Fat");
        fattf = new JTextField(10);
        JLabel carbjl = new JLabel("Carbs");
        carbtf = new JTextField(10);
        JLabel proteinjl = new JLabel("Proteins");
        proteintf = new JTextField(10);
        addFoodBtn = new JButton("Add (model.)Food");
        JTextArea recepie = new JTextArea(10,100);

        foodPanel.add(namejl);
        foodPanel.add(nametf);
        foodPanel.add(caljl);
        foodPanel.add(caltf);
        foodPanel.add(fatjl);
        foodPanel.add(fattf);
        foodPanel.add(carbjl);
        foodPanel.add(carbtf);
        foodPanel.add(proteinjl);
        foodPanel.add(proteintf);
        foodPanel.add(addFoodBtn);

        JPanel recipePanel = new JPanel(); // the panel is not visible in output
        JLabel recipeNamejl = new JLabel("(model.)Recipe name");
        recipeNametf = new JTextField(10);
        JLabel firstFoodNamejl = new JLabel("food name");
        firstFoodNametf = new JTextField(10);
        JLabel firstFoodCountjl = new JLabel("food count");
        firstFoodCounttf = new JTextField(10);

        addRecipeBtn = new JButton("Add (model.)Recipe");

        recipePanel.add(recipeNamejl);
        recipePanel.add(recipeNametf);
        recipePanel.add(firstFoodNamejl);
        recipePanel.add(firstFoodNametf);
        recipePanel.add(firstFoodCountjl);
        recipePanel.add(firstFoodCounttf);

        recipePanel.add(addRecipeBtn);

        JPanel calPanel = new JPanel();
        JLabel calGoaljl = new JLabel("Set desired caloric intake");
        calGoaltf = new JTextField(10);
        calBtn = new JButton("Set");
        calPanel.add(calGoaljl);
        calPanel.add(calGoaltf);
        calPanel.add(calBtn);

        JPanel datePanel = new JPanel();
        JLabel dayjl = new JLabel("Day:");
        daytf = new JTextField(7);
        JLabel monthjl = new JLabel("Month:");
        monthtf = new JTextField(7);
        JLabel yearjl = new JLabel("Year:");
        yeartf = new JTextField(7);
        setDateBtn = new JButton("Set");

        datePanel.add(dayjl);
        datePanel.add(daytf);
        datePanel.add(monthjl);
        datePanel.add(monthtf);
        datePanel.add(yearjl);
        datePanel.add(yeartf);
        datePanel.add(setDateBtn);


        JPanel infoPanel = new JPanel();
        JLabel jlCal = new JLabel("Cal intake");
        tfCal = new JTextField(10);
        tfCal.setEditable(false);
        JLabel jlFat = new JLabel("Fat intake");
        tfFat = new JTextField(10);
        tfFat.setEditable(false);
        JLabel jlCarb = new JLabel("Carb intake");
        tfCarb = new JTextField(10);
        tfCarb.setEditable(false);
        JLabel jlProtein = new JLabel("Protein intake");
        tfProtein = new JTextField(10);
        tfProtein.setEditable(false);

        JLabel jlNeeded = new JLabel("To desired cal intake");
        tfNeeded = new JTextField(10);
        tfNeeded.setEditable(false);

        infoPanel.add(jlCal);
        infoPanel.add(tfCal);
        infoPanel.add(jlFat);
        infoPanel.add(tfFat);
        infoPanel.add(jlCarb);
        infoPanel.add(tfCarb);
        infoPanel.add(jlProtein);
        infoPanel.add(tfProtein);
        infoPanel.add(jlNeeded);
        infoPanel.add(tfNeeded);

        JPanel foodDisplayP = new JPanel();
        jcbFood = new JComboBox<>(foodArr);
        JLabel servingsjl = new JLabel("Servings:");
        servingstf = new JTextField(3);
        selectEatenB = new JButton("Add eaten");
        JComboBox<? extends String> jcbRecipe = new JComboBox<>(recipeArr);

        foodDisplayP.add(jcbFood);
        foodDisplayP.add(servingsjl);
        foodDisplayP.add(servingstf);
        foodDisplayP.add(selectEatenB);


        JPanel weightPanel = new JPanel(); // the panel is not visible in output
        JLabel labelWeight = new JLabel("Current weight");
        JTextField weightf = new JTextField(5);

        JLabel weightChangejl = new JLabel("Your weight changed");
        JTextField weightChangejtf = new JTextField(5);
        weightChangejtf.setEditable(false);
        jbWeight = new JButton("Set");

        weightPanel.add(labelWeight);
        weightPanel.add(weightf);
        weightPanel.add(jbWeight);
        weightPanel.add(weightChangejl);
        weightPanel.add(weightChangejtf);

        GridLayout grid = new GridLayout(10, 1, 10, 30);
        frame.setLayout(grid);
        frame.add(foodPanel);
        frame.add(recipePanel);
        frame.add(jcbFood);
        frame.add(foodDisplayP);

        frame.add(jcbRecipe);
        frame.add(calPanel);
        frame.add(datePanel);
        frame.add(infoPanel);
        frame.add(weightPanel);

        frame.setVisible(true);
    }

    public void clearFoodInputs() {
        nametf.setText("");
        caltf.setText("");
        fattf.setText("");
        carbtf.setText("");
        proteintf.setText("");
    }

    public void updateEatFood(String[] food) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(food);
        jcbFood.removeAllItems();
        jcbFood.setModel(model);
    }

    public void clearCalorieInput(){
       calGoaltf.setText("");
    }

    public void dialogError(String message){ JOptionPane.showMessageDialog(f, message); }

    public JButton getFoodBtn(){
        return addFoodBtn;
    }
    public JButton getRecipeBtn(){
        return addRecipeBtn;
    }
    public JButton getCalorieBtn(){
        return calBtn;
    }
    public JButton getCurrentWeightBtn(){
        return jbWeight;
    }
    public JButton getDateBtn(){return setDateBtn;}
    public JButton getSelectEatenB(){
        return selectEatenB;
    }
    public String getFoodName(){
        return nametf.getText();
    }
    public String getFoodCal(){
        return caltf.getText();
    }
    public String getFoodFat(){
        return fattf.getText();
    }
    public String getFoodCarb(){
        return carbtf.getText();
    }
    public String getFoodProtein(){
        return proteintf.getText();
    }
    public String getCalorieIntake(){
        return calGoaltf.getText();
    }
    public String getSelectedFoodToBeEaten() {
        return this.jcbFood.getItemAt(jcbFood.getSelectedIndex());
    }
    public String getServings(){
        return servingstf.getText();
    }

    public JTextField getCalIntakeTF(){
        return tfCal;
    }
    public JTextField getFatIntakeTF(){
        return tfFat;
    }
    public JTextField getCarbIntakeTF(){
        return tfCarb;
    }
    public JTextField getProteinIntakeTF(){
        return tfProtein;
    }

}

