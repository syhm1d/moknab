/**
 * @author Afiq Irfan
 */
public class Drink extends MenuItem implements Preparable {

    public Drink(String itemId, String itemName, double basePrice) {
        super(itemId, itemName, basePrice);
    }

    @Override
    public double calculatePrice() {
        return this.getBasePrice();
    }

    @Override
    public void startPreparation() {
        System.out.println("[BARISTA] Brewing " + this.getItemName() + ".");
    }

    @Override
    public void completePreparation() {
        System.out.println("[BARISTA] Drink " + this.getItemName() + " is ready to be served.");
    }

    @Override
    public int getPrepTimeEstimation() {
        return 3;
    }
}
