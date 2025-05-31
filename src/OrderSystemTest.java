import java.util.Arrays;
import java.util.List;

/**
 * Test class to demonstrate the functionality of the order management system
 * Author: Student
 * Version: 1.0
 */
public class OrderSystemTest
{
    /**
     * Main method to run basic tests
     * @param arguments command line arguments (not used)
     */
    public static void main(String[] arguments)
    {
        System.out.println("=== Order Management System Tests ===\n");

        testPizzaCreation();
        testPastaCreation();
        testOrderCreation();
        testMealTypeClassification();
        testPriceCalculation();

        System.out.println("\n=== All tests completed ===");
    }

    /**
     * Tests meal type classification for different food combinations
     */
    private static void testMealTypeClassification()
    {
        System.out.println("Testing meal type classification:");

        // Test vegan order
        Order veganOrder = new Order("Alice", "12345678", "123 Vegan St");
        veganOrder.addFoodItem(new Pizza(Arrays.asList(PizzaTopping.TOMATO, PizzaTopping.MUSHROOMS)));
        veganOrder.addFoodItem(new Pasta(PastaTopping.TOMATO));
        System.out.println("Vegan order meal type: " + veganOrder.getMealType());

        // Test vegetarian order
        Order vegetarianOrder = new Order("Bob", "87654321", "456 Veggie Ave");
        vegetarianOrder.addFoodItem(new Pizza(Arrays.asList(PizzaTopping.CHEESE, PizzaTopping.MUSHROOMS)));
        vegetarianOrder.addFoodItem(new Pasta(PastaTopping.PRIMAVERA));
        System.out.println("Vegetarian order meal type: " + vegetarianOrder.getMealType());

        // Test meat order
        Order meatOrder = new Order("Charlie", "11223344", "789 Meat Rd");
        meatOrder.addFoodItem(new Pizza(Arrays.asList(PizzaTopping.HAM, PizzaTopping.CHEESE)));
        meatOrder.addFoodItem(new Pasta(PastaTopping.BOLOGNESE));
        System.out.println("Meat order meal type: " + meatOrder.getMealType());

        System.out.println();
    }

    /**
     * Tests order creation and functionality
     */
    private static void testOrderCreation()
    {
        System.out.println("Testing order creation:");

        Order testOrder = new Order("John Doe", "12345678", "123 Main St");

        Pizza pizza = new Pizza(Arrays.asList(PizzaTopping.HAM, PizzaTopping.CHEESE, PizzaTopping.PINEAPPLE));
        Pasta pasta = new Pasta(PastaTopping.MARINARA);

        testOrder.addFoodItem(pizza);
        testOrder.addFoodItem(pasta);

        System.out.println("Order created successfully!");
        System.out.println("Total cost: $" + String.format("%.2f", testOrder.getTotalCost()));
        System.out.println("Meal type: " + testOrder.getMealType());
        System.out.println();
    }

    /**
     * Tests pasta creation and functionality
     */
    private static void testPastaCreation()
    {
        System.out.println("Testing pasta creation:");

        Pasta plainPasta = new Pasta();
        System.out.println("Plain pasta: " + plainPasta.toString());
        System.out.println("Price: $" + String.format("%.2f", plainPasta.getPrice()));
        System.out.println("Meal type: " + plainPasta.getMealType());

        Pasta bolognese = new Pasta(PastaTopping.BOLOGNESE);
        System.out.println("Bolognese pasta: " + bolognese.toString());
        System.out.println("Price: $" + String.format("%.2f", bolognese.getPrice()));
        System.out.println("Meal type: " + bolognese.getMealType());

        System.out.println();
    }

    /**
     * Tests pizza creation and functionality
     */
    private static void testPizzaCreation()
    {
        System.out.println("Testing pizza creation:");

        Pizza plainPizza = new Pizza();
        System.out.println("Plain pizza: " + plainPizza.toString());
        System.out.println("Price: $" + String.format("%.2f", plainPizza.getPrice()));
        System.out.println("Meal type: " + plainPizza.getMealType());

        List<PizzaTopping> toppings = Arrays.asList(PizzaTopping.HAM, PizzaTopping.CHEESE, PizzaTopping.PINEAPPLE);
        Pizza hawaiianPizza = new Pizza(toppings);
        System.out.println("Hawaiian pizza: " + hawaiianPizza.toString());
        System.out.println("Price: $" + String.format("%.2f", hawaiianPizza.getPrice()));
        System.out.println("Meal type: " + hawaiianPizza.getMealType());

        System.out.println();
    }

    /**
     * Tests price calculation for various food items
     */
    private static void testPriceCalculation()
    {
        System.out.println("Testing price calculations:");

        // Test pizza pricing
        Pizza expensivePizza = new Pizza(Arrays.asList(PizzaTopping.SEAFOOD, PizzaTopping.PINEAPPLE, PizzaTopping.HAM));
        System.out.println("Expensive pizza price: $" + String.format("%.2f", expensivePizza.getPrice()));

        // Test pasta pricing
        Pasta expensivePasta = new Pasta(PastaTopping.MARINARA);
        System.out.println("Marinara pasta price: $" + String.format("%.2f", expensivePasta.getPrice()));

        // Test order total
        Order expensiveOrder = new Order("Rich Customer", "99999999", "Expensive St");
        expensiveOrder.addFoodItem(expensivePizza);
        expensiveOrder.addFoodItem(expensivePasta);
        System.out.println("Total expensive order: $" + String.format("%.2f", expensiveOrder.getTotalCost()));

        System.out.println();
    }
}