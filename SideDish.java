/**
 * @author Naqyuddin
 * Concrete subclass tracking secondary accompanying options.
 */
public class SideDish extends MenuItem implements Preparable {
    private String portionSize;
    private boolean isSpicy;

    public SideDish(String itemId, String itemName, double basePrice, String portionSize, boolean isSpicy) {
        super(itemId, itemName, basePrice);
        this.portionSize = portionSize;
        this.isSpicy = isSpicy;
    }

    @Override
    public double calculatePrice() {
        return portionSize.equalsIgnoreCase("Large") ? this.basePrice + 2.00 : this.basePrice;
    }

    @Override
    public void startPreparation() {
        System.out.println("[KITCHEN] Preheating fryer and preparing side item: " + itemName);
    }

    @Override
    public void completePreparation() {
        System.out.println("[KITCHEN] Side item (" + itemName + ") checked and packed.");
    }

    @Override
    public int getPrepTimeEstimation() {
        return 5;
    }
}