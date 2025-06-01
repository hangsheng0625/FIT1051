package com.restaurant.orderManagement.model.entity;

import com.restaurant.orderManagement.model.enums.MealType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a customer order with food items and customer details
 * Author: Student
 * Version: 1.0
 */
public class Order implements Serializable
{
    private List<Food> foodItems;
    private String customerName;
    private String contactNumber;
    private String deliveryAddress;
    private double totalCost;
    private MealType mealType;
    private LocalDateTime orderTime;

    /**
     * Default constructor for com.restaurant.orderManagement.model.entity.Order
     */
    public Order()
    {
        this.foodItems = new ArrayList<Food>();
        this.customerName = "";
        this.contactNumber = "";
        this.deliveryAddress = "";
        this.totalCost = 0.0;
        this.mealType = MealType.VEGAN;
        this.orderTime = LocalDateTime.now();
    }

    /**
     * Constructor for com.restaurant.orderManagement.model.entity.Order with customer details
     * @param customerName the customer's name
     * @param contactNumber the customer's contact number
     * @param deliveryAddress the delivery address
     */
    public Order(String customerName, String contactNumber, String deliveryAddress)
    {
        this.foodItems = new ArrayList<Food>();
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.deliveryAddress = deliveryAddress;
        this.totalCost = 0.0;
        this.mealType = MealType.VEGAN;
        this.orderTime = LocalDateTime.now();
    }

    /**
     * Adds a food item to the order and recalculates totals
     * @param foodItem the food item to add
     */
    public void addFoodItem(Food foodItem)
    {
        foodItems.add(foodItem);
        calculateTotalCost();
        determineMealType();
    }

    /**
     * Calculates the total cost of all food items in the order
     */
    private void calculateTotalCost()
    {
        totalCost = 0.0;
        for (Food foodItem : foodItems)
        {
            totalCost += foodItem.getPrice();
        }
    }

    /**
     * Determines the overall meal type of the order based on all food items
     */
    private void determineMealType()
    {
        if (foodItems.isEmpty())
        {
            mealType = MealType.VEGAN;
            return;
        }

        boolean hasMeat = false;
        boolean hasVegetarian = false;

        for (Food foodItem : foodItems)
        {
            MealType itemType = foodItem.getMealType();
            if (itemType == MealType.MEAT)
            {
                hasMeat = true;
                break;
            }
            else if (itemType == MealType.VEGETARIAN)
            {
                hasVegetarian = true;
            }
        }

        if (hasMeat)
        {
            mealType = MealType.MEAT;
        }
        else if (hasVegetarian)
        {
            mealType = MealType.VEGETARIAN;
        }
        else
        {
            mealType = MealType.VEGAN;
        }
    }

    /**
     * Gets the contact number
     * @return the contact number as a string
     */
    public String getContactNumber()
    {
        return contactNumber;
    }

    /**
     * Gets the customer name
     * @return the customer name as a string
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**
     * Gets the delivery address
     * @return the delivery address as a string
     */
    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }

    /**
     * Gets the list of food items
     * @return a copy of the food items list
     */
    public List<Food> getFoodItems()
    {
        return new ArrayList<Food>(foodItems);
    }

    /**
     * Gets the meal type
     * @return the meal type as a com.restaurant.orderManagement.model.enums.MealType enum
     */
    public MealType getMealType()
    {
        return mealType;
    }

    /**
     * Gets the order time
     * @return the order time as LocalDateTime
     */
    public LocalDateTime getOrderTime()
    {
        return orderTime;
    }

    /**
     * Gets the total cost
     * @return the total cost as a double
     */
    public double getTotalCost()
    {
        return totalCost;
    }

    /**
     * Sets the contact number
     * @param contactNumber the new contact number
     */
    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    /**
     * Sets the customer name
     * @param customerName the new customer name
     */
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    /**
     * Sets the delivery address
     * @param deliveryAddress the new delivery address
     */
    public void setDeliveryAddress(String deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Sets the food items list
     * @param foodItems the new list of food items
     */
    public void setFoodItems(List<Food> foodItems)
    {
        this.foodItems = new ArrayList<Food>(foodItems);
        calculateTotalCost();
        determineMealType();
    }

    /**
     * Returns string representation of the order
     * @return detailed string description of the order
     */
    @Override
    public String toString()
    {
        StringBuilder orderDetails = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        orderDetails.append("com.restaurant.orderManagement.model.entity.Order Details:\n");
        orderDetails.append("Customer: ").append(customerName).append("\n");
        orderDetails.append("Contact: ").append(contactNumber).append("\n");
        orderDetails.append("Address: ").append(deliveryAddress).append("\n");
        orderDetails.append("com.restaurant.orderManagement.model.entity.Order Time: ").append(orderTime.format(formatter)).append("\n");
        orderDetails.append("Meal Type: ").append(mealType.toString()).append("\n");
        orderDetails.append("com.restaurant.orderManagement.model.entity.Food Items:\n");

        for (int i = 0; i < foodItems.size(); i++)
        {
            orderDetails.append("  ").append(i + 1).append(". ").append(foodItems.get(i).toString()).append("\n");
        }

        orderDetails.append(String.format("Total Cost: $%.2f", totalCost));

        return orderDetails.toString();
    }
}