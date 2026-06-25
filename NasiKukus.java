/**
 * @author Afiq Irfan
 */
public class NasiKukus extends MenuItem implements Preparable { 

    public NasiKukus(String itemId, String itemName, double basePrice) {
        super(itemId, itemName, basePrice);
    }

    @Override
    public double calculatePrice() {
        return this.getBasePrice(); 
    }

    @Override
    public void startPreparation() {
        System.out.println("[KITCHEN] Preparing " + this.getItemName());
    }

    @Override
    public void completePreparation() {
        System.out.println("[KITCHEN] " + this.getItemName() + " is ready to be served.");
    }

    @Override
    public int getPrepTimeEstimation() {
        return 7;
    }
}
