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
public interface Food {
    float getProtein();
    float getCarb();
    float getFat();
    float getCalories();
    String getName();
}
