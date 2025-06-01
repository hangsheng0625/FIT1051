package com.restaurant.orderManagement.service;

import com.restaurant.orderManagement.model.entity.*;
import com.restaurant.orderManagement.model.enums.*;
import java.util.*;

/**
 * Concrete factory implementation for creating restaurant food items
 * Author: Liaw Hang Sheng
 * Version: 1.0
 */
public class RestaurantFoodFactory implements FoodFactory
{
    private static final String[] MENU_ITEMS = {
            "hawaiian_pizza",
            "meat_lovers_pizza",
            "vegetarian_supreme_pizza",
            "margherita_pizza",
            "seafood_deluxe_pizza",
            "classic_marinara_pasta",
            "creamy_primavera_pasta",
            "hearty_bolognese_pasta",
            "simple_tomato_pasta"
    };

    /**
     * Default constructor for RestaurantFoodFactory
     */
    public RestaurantFoodFactory()
    {
        // Default constructor
    }

    /**
     * Creates Classic Marinara pasta
     * @return Marinara pasta
     */
    private Pasta createClassicMarinaraPasta()
    {
        return new Pasta(PastaTopping.MARINARA);
    }

    /**
     * Creates Creamy Primavera pasta
     * @return Primavera pasta
     */
    private Pasta createCreamyPrimaveraPasta()
    {
        return new Pasta(PastaTopping.PRIMAVERA);
    }

    /**
     * Creates a food item based on type and parameters
     * @param foodType the type of food (pizza or pasta)
     * @param parameters map containing creation parameters
     * @return the created food item
     */
    @Override
    public Food createFood(String foodType, Map<String, Object> parameters)
    {
        if (foodType == null)
        {
            throw new IllegalArgumentException("Food type cannot be null");
        }

        switch (foodType.toLowerCase())
        {
            case "pizza":
                return createPizza(parameters);
            case "pasta":
                return createPasta(parameters);
            default:
                throw new IllegalArgumentException("Unknown food type: " + foodType);
        }
    }

    /**
     * Creates Hawaiian pizza (ham, pineapple, cheese)
     * @return Hawaiian pizza
     */
    private Pizza createHawaiianPizza()
    {
        List<PizzaTopping> toppings = Arrays.asList(
                PizzaTopping.HAM,
                PizzaTopping.PINEAPPLE,
                PizzaTopping.CHEESE
        );
        return new Pizza(toppings);
    }

    /**
     * Creates Hearty Bolognese pasta
     * @return Bolognese pasta
     */
    private Pasta createHeartyBolognese()
    {
        return new Pasta(PastaTopping.BOLOGNESE);
    }

    /**
     * Creates Margherita pizza (cheese, tomato)
     * @return Margherita pizza
     */
    private Pizza createMargheritaPizza()
    {
        List<PizzaTopping> toppings = Arrays.asList(
                PizzaTopping.CHEESE,
                PizzaTopping.TOMATO
        );
        return new Pizza(toppings);
    }

    /**
     * Creates Meat Lovers pizza (ham, seafood, cheese)
     * @return Meat Lovers pizza
     */
    private Pizza createMeatLoversPizza()
    {
        List<PizzaTopping> toppings = Arrays.asList(
                PizzaTopping.HAM,
                PizzaTopping.SEAFOOD,
                PizzaTopping.CHEESE
        );
        return new Pizza(toppings);
    }

    /**
     * Creates a pre-defined menu item by name
     * @param menuItemName the name of the menu item
     * @return the created food item
     */
    @Override
    public Food createMenuItemByName(String menuItemName)
    {
        if (menuItemName == null)
        {
            throw new IllegalArgumentException("Menu item name cannot be null");
        }

        switch (menuItemName.toLowerCase())
        {
            // Pizza menu items
            case "hawaiian_pizza":
                return createHawaiianPizza();
            case "meat_lovers_pizza":
                return createMeatLoversPizza();
            case "vegetarian_supreme_pizza":
                return createVegetarianSupremePizza();
            case "margherita_pizza":
                return createMargheritaPizza();
            case "seafood_deluxe_pizza":
                return createSeafoodDeluxePizza();

            // Pasta menu items
            case "classic_marinara_pasta":
                return createClassicMarinaraPasta();
            case "creamy_primavera_pasta":
                return createCreamyPrimaveraPasta();
            case "hearty_bolognese_pasta":
                return createHeartyBolognese();
            case "simple_tomato_pasta":
                return createSimpleTomatoPasta();

            default:
                throw new IllegalArgumentException("Unknown menu item: " + menuItemName);
        }
    }

    /**
     * Creates a custom pasta based on parameters
     * @param parameters map containing pasta parameters
     * @return the created pasta
     */
    private Pasta createPasta(Map<String, Object> parameters)
    {
        if (parameters == null || parameters.isEmpty())
        {
            return new Pasta(); // Plain pasta
        }

        PastaTopping topping = (PastaTopping) parameters.get("topping");

        if (topping == null)
        {
            return new Pasta();
        }

        return new Pasta(topping);
    }

    /**
     * Creates a custom pizza based on parameters
     * @param parameters map containing pizza parameters
     * @return the created pizza
     */
    private Pizza createPizza(Map<String, Object> parameters)
    {
        if (parameters == null || parameters.isEmpty())
        {
            return new Pizza(); // Plain pizza
        }

        @SuppressWarnings("unchecked")
        List<PizzaTopping> toppings = (List<PizzaTopping>) parameters.get("toppings");

        if (toppings == null)
        {
            return new Pizza();
        }

        return new Pizza(toppings);
    }

    /**
     * Creates Seafood Deluxe pizza (seafood, cheese, tomato)
     * @return Seafood Deluxe pizza
     */
    private Pizza createSeafoodDeluxePizza()
    {
        List<PizzaTopping> toppings = Arrays.asList(
                PizzaTopping.SEAFOOD,
                PizzaTopping.CHEESE,
                PizzaTopping.TOMATO
        );
        return new Pizza(toppings);
    }

    /**
     * Creates Simple Tomato pasta
     * @return Tomato pasta
     */
    private Pasta createSimpleTomatoPasta()
    {
        return new Pasta(PastaTopping.TOMATO);
    }

    /**
     * Creates Vegetarian Supreme pizza (cheese, mushrooms, tomato, pineapple)
     * @return Vegetarian Supreme pizza
     */
    private Pizza createVegetarianSupremePizza()
    {
        List<PizzaTopping> toppings = Arrays.asList(
                PizzaTopping.CHEESE,
                PizzaTopping.MUSHROOMS,
                PizzaTopping.TOMATO,
                PizzaTopping.PINEAPPLE
        );
        return new Pizza(toppings);
    }

    /**
     * Gets all available pre-defined menu items
     * @return array of menu item names
     */
    @Override
    public String[] getAvailableMenuItems()
    {
        return MENU_ITEMS.clone();
    }

    /**
     * Gets a detailed description of a menu item showing ingredients
     * @param menuItemName the name of the menu item
     * @return detailed description with ingredients
     */
    @Override
    public String getMenuItemDescription(String menuItemName)
    {
        if (menuItemName == null)
        {
            throw new IllegalArgumentException("Menu item name cannot be null");
        }

        switch (menuItemName.toLowerCase())
        {
            // Pizza descriptions
            case "hawaiian_pizza":
                return "Ham, pineapple, and cheese";
            case "meat_lovers_pizza":
                return "Ham, seafood, and cheese";
            case "vegetarian_supreme_pizza":
                return "Cheese, mushrooms, tomato, and pineapple";
            case "margherita_pizza":
                return "Cheese and tomato";
            case "seafood_deluxe_pizza":
                return "Seafood, cheese, and tomato";

            // Pasta descriptions
            case "classic_marinara_pasta":
                return "Pasta with marinara sauce (contains meat)";
            case "creamy_primavera_pasta":
                return "Pasta with primavera sauce (vegetarian)";
            case "hearty_bolognese_pasta":
                return "Pasta with bolognese sauce (contains meat)";
            case "simple_tomato_pasta":
                return "Pasta with tomato sauce (vegan)";

            default:
                return "Description not available";
        }
    }
}