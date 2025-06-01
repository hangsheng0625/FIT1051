import java.io.*;
import java.util.*;

/**
 * Main class for managing takeaway orders with queue management and file persistence
 * Author: Student
 * Version: 1.0
 */
public class OrderManager
{
    private static final String ORDERS_FILE = "orders.dat";
    private static final String CUSTOMERS_FILE = "customers.dat";

    private Queue<Order> orderQueue;
    private Map<String, List<Order>> customerHistory;
    private Scanner scanner;

    /**
     * Default constructor for OrderManager
     */
    public OrderManager()
    {
        this.orderQueue = new LinkedList<Order>();
        this.customerHistory = new HashMap<String, List<Order>>();
        this.scanner = new Scanner(System.in);
        loadOrdersFromFile();
        loadCustomerHistoryFromFile();
    }

    /**
     * Adds a new order to the queue and customer history
     * @param order the order to add
     */
    public void addOrder(Order order)
    {
        orderQueue.offer(order);

        String customerName = order.getCustomerName().toLowerCase();
        customerHistory.putIfAbsent(customerName, new ArrayList<Order>());
        customerHistory.get(customerName).add(order);

        saveOrdersToFile();
        saveCustomerHistoryToFile();
    }

    /**
     * Creates a new order by getting input from user
     */
    private void createOrder()
    {
        System.out.println("\n=== Create New Order ===");

        String customerName = getValidatedInput("Enter customer name: ", "Customer name");
        String contactNumber = getValidatedContactNumber();
        String deliveryAddress = getValidatedInput("Enter delivery address: ", "Delivery address");

        Order order = new Order(customerName, contactNumber, deliveryAddress);

        boolean addingItems = true;
        while (addingItems)
        {
            displayFoodMenu();
            int choice = getValidatedMenuChoice(1, 3);

            switch (choice)
            {
                case 1:
                    addPizzaToOrder(order);
                    break;
                case 2:
                    addPastaToOrder(order);
                    break;
                case 3:
                    if (order.getFoodItems().isEmpty())
                    {
                        System.out.println("Error: Order must contain at least one food item.");
                        continue;
                    }
                    addingItems = false;
                    break;
            }

            if (addingItems && choice != 3)
            {
                System.out.print("Add another item? (y/n): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("y") && !response.equals("yes"))
                {
                    if (order.getFoodItems().isEmpty())
                    {
                        System.out.println("Error: Order must contain at least one food item.");
                        addingItems = true;
                    }
                    else
                    {
                        addingItems = false;
                    }
                }
            }
        }

        addOrder(order);
        System.out.println("\nOrder created successfully!");
        System.out.println(order.toString());
    }

    /**
     * Adds a pasta item to the order based on user input
     * @param order the order to add the pasta to
     */
    private void addPastaToOrder(Order order)
    {
        System.out.println("\nPasta Toppings:");
        System.out.println("1. Plain pasta (vegan) - $" + String.format("%.2f", Food.BASE_PRICE));

        PastaTopping[] toppings = PastaTopping.values();
        for (int i = 0; i < toppings.length; i++)
        {
            System.out.println((i + 2) + ". " + toppings[i].toString().toLowerCase() +
                    " - $" + String.format("%.2f", Food.BASE_PRICE + toppings[i].getPrice()));
        }

        int choice = getValidatedMenuChoice(1, toppings.length + 1);

        Pasta pasta;
        if (choice == 1)
        {
            pasta = new Pasta();
        }
        else
        {
            pasta = new Pasta(toppings[choice - 2]);
        }

        order.addFoodItem(pasta);
        System.out.println("Added: " + pasta.toString());
    }

    /**
     * Adds a pizza item to the order based on user input
     * @param order the order to add the pizza to
     */
    private void addPizzaToOrder(Order order)
    {
        System.out.println("\nPizza Toppings (select multiple by entering numbers separated by spaces):");
        System.out.println("1. Plain pizza (no toppings) - $" + String.format("%.2f", Food.BASE_PRICE));

        PizzaTopping[] toppings = PizzaTopping.values();
        for (int i = 1; i < toppings.length; i++)
        {
            System.out.println((i + 1) + ". " + toppings[i].toString().toLowerCase() +
                    " - $" + String.format("%.2f", toppings[i].getPrice()) + " extra");
        }

        System.out.print("Enter your choices (e.g., '1 3 5' or '0' for plain): ");
        String input = scanner.nextLine().trim();

        List<PizzaTopping> selectedToppings = new ArrayList<PizzaTopping>();

        if (!input.equals("0"))
        {
            try
            {
                String[] choices = input.split("\\s+");
                Set<Integer> uniqueChoices = new HashSet<Integer>();

                for (String choice : choices)
                {
                    int toppingIndex = Integer.parseInt(choice) - 1;
                    if (toppingIndex >= 0 && toppingIndex < toppings.length)
                    {
                        uniqueChoices.add(toppingIndex);
                    }
                    else
                    {
                        throw new NumberFormatException("Invalid topping number: " + (toppingIndex + 1));
                    }
                }

                for (int index : uniqueChoices)
                {
                    selectedToppings.add(toppings[index]);
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Creating plain pizza.");
            }
        }

        Pizza pizza = new Pizza(selectedToppings);
        order.addFoodItem(pizza);
        System.out.println("Added: " + pizza.toString());
    }

    /**
     * Delivers the next order in the queue (FIFO)
     */
    private void deliverOrder()
    {
        if (orderQueue.isEmpty())
        {
            System.out.println("No orders to deliver.");
            return;
        }

        Order deliveredOrder = orderQueue.poll();
        System.out.println("\n=== Order Delivered ===");
        System.out.println(deliveredOrder.toString());

        saveOrdersToFile();
    }

    /**
     * Displays the food menu for item selection
     */
    private void displayFoodMenu()
    {
        System.out.println("\nSelect food item:");
        System.out.println("1. Pizza");
        System.out.println("2. Pasta");
        System.out.println("3. Finish adding items");
        System.out.print("Enter choice: ");
    }

    /**
     * Displays the main menu
     */
    private void displayMainMenu()
    {
        System.out.println("\n=== Takeaway Order Management System ===");
        System.out.println("1. Enter the details of a customer order");
        System.out.println("2. Deliver an order");
        System.out.println("3. Print out details of all orders");
        System.out.println("4. Search orders by customer name");
        System.out.println("5. View customer order history");
        System.out.println("6. Filter orders by meal type");
        System.out.println("7. Export orders to text file");
        System.out.println("8. Exit the program");
        System.out.print("Enter your choice: ");
    }

    /**
     * Exports all current orders to a text file
     */
    private void exportOrdersToFile()
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("orders_export.txt")))
        {
            writer.println("TAKEAWAY ORDER EXPORT");
            writer.println("Generated: " + new Date());
            writer.println("Total Orders: " + orderQueue.size());
            writer.println("=" .repeat(50));

            int orderNumber = 1;
            for (Order order : orderQueue)
            {
                writer.println("\nOrder #" + orderNumber++);
                writer.println(order.toString());
                writer.println("-".repeat(30));
            }

            System.out.println("Orders exported to orders_export.txt successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error exporting orders: " + e.getMessage());
        }
    }

    /**
     * Filters and displays orders by meal type
     */
    private void filterOrdersByMealType()
    {
        if (orderQueue.isEmpty())
        {
            System.out.println("No orders in the system.");
            return;
        }

        System.out.println("\nSelect meal type to filter:");
        System.out.println("1. Meat");
        System.out.println("2. Vegetarian");
        System.out.println("3. Vegan");

        int choice = getValidatedMenuChoice(1, 3);
        MealType selectedType;

        switch (choice)
        {
            case 1:
                selectedType = MealType.MEAT;
                break;
            case 2:
                selectedType = MealType.VEGETARIAN;
                break;
            case 3:
                selectedType = MealType.VEGAN;
                break;
            default:
                return;
        }

        System.out.println("\n=== " + selectedType.toString() + " Orders ===");
        int count = 0;
        for (Order order : orderQueue)
        {
            if (order.getMealType() == selectedType)
            {
                System.out.println("\nOrder #" + (++count));
                System.out.println(order.toString());
                System.out.println("-".repeat(30));
            }
        }

        if (count == 0)
        {
            System.out.println("No orders found for " + selectedType.toString().toLowerCase() + " meal type.");
        }
    }

    /**
     * Gets validated contact number input from user
     * @return validated contact number string
     */
    private String getValidatedContactNumber()
    {
        while (true)
        {
            System.out.print("Enter contact number: ");
            String contact = scanner.nextLine().trim();

            if (contact.isEmpty())
            {
                System.out.println("Error: Contact number cannot be empty.");
                continue;
            }

            if (contact.matches("\\d{8,15}"))
            {
                return contact;
            }
            else
            {
                System.out.println("Error: Contact number must be 8-15 digits only.");
            }
        }
    }

    /**
     * Gets validated string input from user
     * @param prompt the prompt to display
     * @param fieldName the name of the field for error messages
     * @return validated input string
     */
    private String getValidatedInput(String prompt, String fieldName)
    {
        while (true)
        {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty())
            {
                System.out.println("Error: " + fieldName + " cannot be empty.");
                continue;
            }

            return input;
        }
    }

    /**
     * Gets validated menu choice from user
     * @param min minimum valid choice
     * @param max maximum valid choice
     * @return validated menu choice
     */
    private int getValidatedMenuChoice(int min, int max)
    {
        while (true)
        {
            try
            {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max)
                {
                    return choice;
                }
                else
                {
                    System.out.println("Error: Please enter a number between " + min + " and " + max + ".");
                    System.out.print("Enter choice: ");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Error: Please enter a valid number.");
                System.out.print("Enter choice: ");
            }
        }
    }

    /**
     * Loads customer history from file
     */
    @SuppressWarnings("unchecked")
    private void loadCustomerHistoryFromFile()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMERS_FILE)))
        {
            customerHistory = (Map<String, List<Order>>) ois.readObject();
        }
        catch (FileNotFoundException e)
        {
            // File doesn't exist yet, start with empty history
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Warning: Could not load customer history.");
        }
    }

    /**
     * Loads orders from file
     */
    @SuppressWarnings("unchecked")
    private void loadOrdersFromFile()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDERS_FILE)))
        {
            orderQueue = (Queue<Order>) ois.readObject();
        }
        catch (FileNotFoundException e)
        {
            // File doesn't exist yet, start with empty queue
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Warning: Could not load previous orders.");
        }
    }



    /**
     * Prints all orders in the queue
     */
    private void printAllOrders()
    {
        if (orderQueue.isEmpty())
        {
            System.out.println("No orders in the system.");
            return;
        }

        System.out.println("\n=== All Current Orders ===");
        int orderNumber = 1;
        for (Order order : orderQueue)
        {
            System.out.println("\nOrder #" + orderNumber++);
            System.out.println(order.toString());
            System.out.println("-".repeat(30));
        }

        System.out.println("\nTotal orders waiting: " + orderQueue.size());
    }

    /**
     * Main program loop
     */
    public void run()
    {
        System.out.println("Welcome to the Takeaway Order Management System!");

        while (true)
        {
            displayMainMenu();
            int choice = getValidatedMenuChoice(1, 8);

            switch (choice)
            {
                case 1:
                    createOrder();
                    break;
                case 2:
                    deliverOrder();
                    break;
                case 3:
                    printAllOrders();
                    break;
                case 4:
                    searchOrdersByCustomer();
                    break;
                case 5:
                    viewCustomerHistory();
                    break;
                case 6:
                    filterOrdersByMealType();
                    break;
                case 7:
                    exportOrdersToFile();
                    break;
                case 8:
                    saveOrdersToFile();
                    saveCustomerHistoryToFile();
                    System.out.println("Thank you for using the Order Management System!");
                    return;
            }
        }
    }

    /**
     * Saves customer history to file
     */
    private void saveCustomerHistoryToFile()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMERS_FILE)))
        {
            oos.writeObject(customerHistory);
        }
        catch (IOException e)
        {
            System.out.println("Warning: Could not save customer history.");
        }
    }

    /**
     * Saves orders to file
     */
    private void saveOrdersToFile()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE)))
        {
            oos.writeObject(orderQueue);
        }
        catch (IOException e)
        {
            System.out.println("Warning: Could not save orders.");
        }
    }

    /**
     * Searches orders by customer name
     */
    private void searchOrdersByCustomer()
    {
        if (orderQueue.isEmpty())
        {
            System.out.println("No orders in the system.");
            return;
        }

        String searchName = getValidatedInput("Enter customer name to search: ", "Customer name").toLowerCase();

        System.out.println("\n=== Search Results for '" + searchName + "' ===");
        int count = 0;
        for (Order order : orderQueue)
        {
            if (order.getCustomerName().toLowerCase().contains(searchName))
            {
                System.out.println("\nOrder #" + (++count));
                System.out.println(order.toString());
                System.out.println("-".repeat(30));
            }
        }

        if (count == 0)
        {
            System.out.println("No orders found for customer name containing '" + searchName + "'.");
        }
    }

    /**
     * Views customer order history
     */
    private void viewCustomerHistory()
    {
        if (customerHistory.isEmpty())
        {
            System.out.println("No customer history available.");
            return;
        }

        String customerName = getValidatedInput("Enter customer name: ", "Customer name").toLowerCase();

        if (customerHistory.containsKey(customerName))
        {
            List<Order> history = customerHistory.get(customerName);
            System.out.println("\n=== Order History for " + customerName + " ===");
            System.out.println("Total orders: " + history.size());

            for (int i = 0; i < history.size(); i++)
            {
                System.out.println("\nHistorical Order #" + (i + 1));
                System.out.println(history.get(i).toString());
                System.out.println("-".repeat(30));
            }
        }
        else
        {
            System.out.println("No order history found for customer '" + customerName + "'.");
        }
    }
}