/**
 * @author Naqyuddin
 */
public class SideDish extends MenuItem implements Preparable { //apply inheritance and interface
    

    public SideDish(String itemId, String itemName, double basePrice) {
        super(itemId, itemName, basePrice);
    }

    @Override //apply polymorphim
    public double calculatePrice() {
        return this.getBasePrice();
    }

    @Override //apply polymorphim
    public void startPreparation() {
        System.out.println("[KITCHEN] Preparing " + this.getItemName());
    }

    @Override //apply polymorphim
    public void completePreparation() {
        System.out.println("[KITCHEN] " + this.getItemName() + " is ready to be served.");
    }

    @Override //apply polymorphim
    public int getPrepTimeEstimation() {
        return 4;
    }
}
