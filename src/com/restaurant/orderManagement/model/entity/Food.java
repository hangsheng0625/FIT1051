package com.restaurant.orderManagement.model.entity;

import com.restaurant.orderManagement.model.enums.MealType;

import java.io.Serializable;

/**
 * Abstract class representing a food item with price calculation and meal type determination
 * Author: Student
 * Version: 1.0
 */
public abstract class Food implements Serializable
{
    public static final double BASE_PRICE = 11.50;
    public double price;

    /**
     * Default constructor for com.restaurant.orderManagement.model.entity.Food
     */
    public Food()
    {
        this.price = 0.0;
    }

    /**
     * Abstract method to calculate the price of the food item
     * @return the calculated price as a double
     */
    public abstract double calculatePrice();

    /**
     * Abstract method to determine the meal type of the food item
     * @return the meal type as a com.restaurant.orderManagement.model.enums.MealType enum
     */
    public abstract MealType getMealType();

    /**
     * Gets the price of the food item
     * @return the price as a double
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Sets the price of the food item
     * @param price the new price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Abstract method for string representation of the food item
     * @return string representation of the food item
     */
    public abstract String toString();
}