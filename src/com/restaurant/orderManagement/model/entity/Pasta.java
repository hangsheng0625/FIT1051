package com.restaurant.orderManagement.model.entity;

import com.restaurant.orderManagement.model.enums.MealType;
import com.restaurant.orderManagement.model.enums.PastaTopping;

/**
 * Class representing a pasta with a single topping, price calculation and meal type determination
 * Author: Student
 * Version: 1.0
 */
public class Pasta extends Food
{
    private PastaTopping topping;

    /**
     * Default constructor for com.restaurant.orderManagement.model.entity.Pasta with no topping (plain pasta)
     */
    public Pasta()
    {
        super();
        this.topping = null;
        this.price = calculatePrice();
    }

    /**
     * Constructor for com.restaurant.orderManagement.model.entity.Pasta with specified topping
     * @param topping the pasta topping
     */
    public Pasta(PastaTopping topping)
    {
        super();
        this.topping = topping;
        this.price = calculatePrice();
    }

    /**
     * Calculates the total price of the pasta including base price and topping
     * @return the calculated price as a double
     */
    @Override
    public double calculatePrice()
    {
        double totalPrice = Food.BASE_PRICE;
        if (topping != null)
        {
            totalPrice += topping.getPrice();
        }
        return totalPrice;
    }

    /**
     * Determines the meal type based on topping
     * @return the meal type as a com.restaurant.orderManagement.model.enums.MealType enum
     */
    @Override
    public MealType getMealType()
    {
        if (topping == null)
        {
            return MealType.VEGAN;
        }

        if (!topping.isVegetarian())
        {
            return MealType.MEAT;
        }
        else if (!topping.isVegan())
        {
            return MealType.VEGETARIAN;
        }
        else
        {
            return MealType.VEGAN;
        }
    }

    /**
     * Gets the pasta topping
     * @return the pasta topping or null if plain
     */
    public PastaTopping getTopping()
    {
        return topping;
    }

    /**
     * Sets the pasta topping
     * @param topping the new pasta topping
     */
    public void setTopping(PastaTopping topping)
    {
        this.topping = topping;
        this.price = calculatePrice();
    }

    /**
     * Returns string representation of the pasta
     * @return string description of the pasta
     */
    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder("Pasta");
        if (topping == null)
        {
            description.append(" (Plain)");
        }
        else
        {
            description.append(" ").append(topping.toString().toLowerCase());
        }
        description.append(String.format(" - $%.2f", price));
        return description.toString();
    }
}