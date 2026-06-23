/**
 * @author Naqyuddin
 * Manages customer parameters, registration profiles, and loyalty mechanics.
 */
public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;
    private int loyaltyPoints;

    public Customer(String customerId, String name, String phoneNumber) {
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer ID cannot be empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("Phone number cannot be empty");
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.loyaltyPoints = 0;
    }

    public void addPoints(int points) {
        if (points > 0) {
            this.loyaltyPoints += points;
            System.out.println(" >>> " + points + " loyalty points added to " + this.name);
        }
    }

    public void redeemPoints(int pointsToRedeem) throws OrderException {
        if (pointsToRedeem > this.loyaltyPoints) {
            // Uses the updated OrderException class
            throw new OrderException("Points Error: " + name + " only has " 
                    + loyaltyPoints + " points available, but requested to redeem " + pointsToRedeem);
        }
        this.loyaltyPoints -= pointsToRedeem;
        System.out.println("Successfully spent " + pointsToRedeem + " loyalty points.");
    }

    public String getDetails() {
        return String.format("ID: %s | Name: %s | Active Points Balance: %d", customerId, name, loyaltyPoints);
    }

    public int getLoyaltyPoints() { return loyaltyPoints; }
    public String getName() { return name; }
}