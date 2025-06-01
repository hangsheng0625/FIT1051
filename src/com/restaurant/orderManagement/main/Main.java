package com.restaurant.orderManagement.main;

import com.restaurant.orderManagement.service.OrderManager;

/**
 * com.restaurant.orderManagement.main.Main class to start the Takeaway com.restaurant.orderManagement.model.entity.Order Management System
 * Author: Student
 * Version: 1.0
 */
public class Main
{
    /**
     * com.restaurant.orderManagement.main.Main method to start the application
     * @param arguments command line arguments (not used)
     */
    public static void main(String[] arguments)
    {
        System.out.println("Starting Takeaway com.restaurant.orderManagement.model.entity.Order Management System...");

        OrderManager manager = new OrderManager();
        manager.run();

        System.out.println("Application terminated successfully.");
    }
}