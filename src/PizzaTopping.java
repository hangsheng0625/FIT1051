/**
 * Enum representing the available pizza toppings with their prices
 * Author: Student
 * Version: 1.0
 */
public enum PizzaTopping
{
    HAM(2.00, false, false),
    CHEESE(2.00, true, false),
    PINEAPPLE(2.50, true, true),
    MUSHROOMS(2.00, true, true),
    TOMATO(2.00, true, true),
    SEAFOOD(3.50, false, false);

    private final double price;
    private final boolean isVegetarian;
    private final boolean isVegan;

    /**
     * Constructor for PizzaTopping enum
     * @param price the additional cost of this topping
     * @param isVegetarian whether this topping is vegetarian
     * @param isVegan whether this topping is vegan
     */
    PizzaTopping(double price, boolean isVegetarian, boolean isVegan)
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
     * Checks if this topping is vegetarian
     * @return true if vegetarian, false otherwise
     */
    public boolean isVegetarian()
    {
        return isVegetarian;
    }

    /**
     * Checks if this topping is vegan
     * @return true if vegan, false otherwise
     */
    public boolean isVegan()
    {
        return isVegan;
    }
}