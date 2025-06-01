package com.restaurant.orderManagement.model.enums;

/**
 * Enum representing the available pasta toppings with their prices and dietary information
 * Author: Liaw Hang Sheng
 * Version: 1.0
 */
public enum PastaTopping
{
    BOLOGNESE(5.20, false, false),
    MARINARA(6.80, false, false),
    PRIMAVERA(5.20, true, false),
    TOMATO(4.00, true, true);

    private final double price;
    private final boolean isVegetarian;
    private final boolean isVegan;

    /**
     * Constructor for PastaTopping enum
     * @param price the additional cost of this topping
     * @param isVegetarian whether this topping is vegetarian
     * @param isVegan whether this topping is vegan
     */
    PastaTopping(double price, boolean isVegetarian, boolean isVegan)
    {
        this.price = price;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
    }

    /**
     * Gets the price of this topping
     * @return the price as a double
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Checks if this topping is vegan
     * @return true if vegan, false otherwise
     */
    public boolean isVegan()
    {
        return isVegan;
    }

    /**
     * Checks if this topping is vegetarian
     * @return true if vegetarian, false otherwise
     */
    public boolean isVegetarian()
    {
        return isVegetarian;
    }
}