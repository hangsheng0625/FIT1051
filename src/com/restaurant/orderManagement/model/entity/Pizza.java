package com.restaurant.orderManagement.model.entity;

import com.restaurant.orderManagement.model.enums.MealType;
import com.restaurant.orderManagement.model.enums.PizzaTopping;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a pizza with toppings, price calculation and meal type determination
 * Author: Student
 * Version: 1.0
 */
public class Pizza extends Food
{
    private List<PizzaTopping> toppings;

    /**
     * Default constructor for com.restaurant.orderManagement.model.entity.Pizza with no toppings
     */
    public Pizza()
    {
        super();
        this.toppings = new ArrayList<PizzaTopping>();
        this.price = calculatePrice();
    }

    /**
     * Constructor for com.restaurant.orderManagement.model.entity.Pizza with specified toppings
     * @param toppings list of pizza toppings
     */
    public Pizza(List<PizzaTopping> toppings)
    {
        super();
        this.toppings = new ArrayList<PizzaTopping>(toppings);
        this.price = calculatePrice();
    }

    /**
     * Adds a topping to the pizza
     * @param topping the pizza topping to add
     */
    public void addTopping(PizzaTopping topping)
    {
        toppings.add(topping);
        this.price = calculatePrice();
    }

    /**
     * Calculates the total price of the pizza including base price and toppings
     * @return the calculated price as a double
     */
    @Override
    public double calculatePrice()
    {
        double totalPrice = Food.BASE_PRICE;
        for (PizzaTopping topping : toppings)
        {
            totalPrice += topping.getPrice();
        }
        return totalPrice;
    }

    /**
     * Determines the meal type based on toppings
     * @return the meal type as a com.restaurant.orderManagement.model.enums.MealType enum
     */
    @Override
    public MealType getMealType()
    {
        if (toppings.isEmpty())
        {
            return MealType.VEGAN;
        }

        boolean hasNonVegetarian = false;
        boolean hasNonVegan = false;

        for (PizzaTopping topping : toppings)
        {
            if (!topping.isVegetarian())
            {
                hasNonVegetarian = true;
                break;
            }
            if (!topping.isVegan())
            {
                hasNonVegan = true;
            }
        }

        if (hasNonVegetarian)
        {
            return MealType.MEAT;
        }
        else if (hasNonVegan)
        {
            return MealType.VEGETARIAN;
        }
        else
        {
            return MealType.VEGAN;
        }
    }

    /**
     * Gets the list of toppings
     * @return list of pizza toppings
     */
    public List<PizzaTopping> getToppings()
    {
        return new ArrayList<PizzaTopping>(toppings);
    }

    /**
     * Sets the list of toppings
     * @param toppings new list of pizza toppings
     */
    public void setToppings(List<PizzaTopping> toppings)
    {
        this.toppings = new ArrayList<PizzaTopping>(toppings);
        this.price = calculatePrice();
    }

    /**
     * Returns string representation of the pizza
     * @return string description of the pizza
     */
    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder("Pizza");
        if (toppings.isEmpty())
        {
            description.append(" (Plain)");
        }
        else
        {
            description.append(" with ");
            for (int i = 0; i < toppings.size(); i++)
            {
                description.append(toppings.get(i).toString().toLowerCase());
                if (i < toppings.size() - 2)
                {
                    description.append(", ");
                }
                else if (i == toppings.size() - 2)
                {
                    description.append(" and ");
                }
            }
        }
        description.append(String.format(" - $%.2f", price));
        return description.toString();
    }
}