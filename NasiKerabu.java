/**
 * @author Afiq Irfan
 * Specialized concrete MenuItem representing customized traditional rice plates.
 */
public class NasiKerabu extends MenuItem implements Preparable {
    private String riceColor;
    private String proteinType;
    private boolean addSolokLada;

    public NasiKerabu(String itemId, String itemName, double basePrice, String riceColor, String proteinType, boolean addSolokLada) {
        super(itemId, itemName, basePrice);
        this.riceColor = riceColor;
        this.proteinType = proteinType;
        this.addSolokLada = addSolokLada;
    }

    @Override
    public double calculatePrice() {
        double finalPrice = this.basePrice;
        if (proteinType.equalsIgnoreCase("Ayam Bakar")) finalPrice += 3.50;
        else if (proteinType.equalsIgnoreCase("Daging Salai")) finalPrice += 5.00;
        
        if (addSolokLada) finalPrice += 1.50;
        return finalPrice;
    }

    @Override
    public void startPreparation() {
        System.out.println("[KITCHEN] Steaming " + riceColor + " rice and grilling " + proteinType + ".");
    }

    @Override
    public void completePreparation() {
        System.out.println("[KITCHEN] Nasi Kerabu garnishing finalized. Ready to plate!");
    }

    @Override
    public int getPrepTimeEstimation() {
        return proteinType.equalsIgnoreCase("Daging Salai") ? 15 : 10;
    }

    public String getCustomizationDetails() {
        return String.format("Rice: %s, Protein: %s, Solok Lada: %b", riceColor, proteinType, addSolokLada);
    }
}