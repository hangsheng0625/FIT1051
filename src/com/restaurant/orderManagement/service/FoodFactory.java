package com.restaurant.orderManagement.service;

import com.restaurant.orderManagement.model.entity.Food;
import java.util.Map;

/**
 * Factory interface for creating different types of food items
 * Author: Student
 * Version: 1.0
 */
public interface FoodFactory
{
    /**
     * Creates a food item based on the specified type and parameters
     * @param foodType the type of food to create
     * @param parameters additional parameters for food creation
     * @return the created food item
     */
    Food createFood(String foodType, Map<String, Object> parameters);

    /**
     * Creates a pre-defined menu item by name
     * @param menuItemName the name of the pre-defined menu item
     * @return the created food item
     */
    Food createMenuItemByName(String menuItemName);

    /**
     * Gets a list of all available pre-defined menu items
     * @return array of menu item names
     */
    String[] getAvailableMenuItems();
}