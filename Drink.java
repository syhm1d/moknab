/**
 * @author Afiq Irfan
 */
public class Drink extends MenuItem implements Preparable { //apply inheritance and interface

    public Drink(String itemId, String itemName, double basePrice) {
        super(itemId, itemName, basePrice); //apply inheritance
    }

    @Override //apply polymorphim  
    public double calculatePrice() {
        return this.getBasePrice();
    }

    @Override //apply polymorphim
    public void startPreparation() {
        System.out.println("[BARISTA] Brewing " + this.getItemName() + ".");
    }

    @Override //apply polymorphim
    public void completePreparation() {
        System.out.println("[BARISTA] Drink " + this.getItemName() + " is ready to be served.");
    }

    @Override //apply polymorphim
    public int getPrepTimeEstimation() {
        return 3;
    }
}
