package com.restaurant.orderManagement.main;

import com.restaurant.orderManagement.service.OrderManager;

/**
 * Main class to start the Takeaway Order Management System
 * Author: Liaw Hang Sheng
 * Version: 1.0
 */
public class Main
{
    /**
     * Main method to start the application
     * @param arguments command line arguments (not used)
     */
    public static void main(String[] arguments)
    {
        System.out.println("Starting Takeaway Order Management System...");

        OrderManager manager = new OrderManager();
        manager.run();

        System.out.println("Application terminated successfully.");
    }
}