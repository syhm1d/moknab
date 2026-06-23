/**
 * @author Adam Syahmi
 * Abstract base parent class defining baseline variables and pricing method.
 */
public abstract class MenuItem {
    protected final String itemId;
    protected final String itemName;
    protected double basePrice;

    public MenuItem(String itemId, String itemName, double basePrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.basePrice = basePrice;
    }

    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getBasePrice() { return basePrice; }

    // Abstract operational method to be overridden polymorphically
    public abstract double calculatePrice();

    @Override
    public String toString() {
        return String.format("%s (%s) - Base: RM%.2f", itemName, itemId, basePrice);
    }
}