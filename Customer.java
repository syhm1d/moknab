/**
 * @author Afiq Irfan
 */
public class Customer {
    private String customerId;
    private String name;
    private String phoneNumber;

    public Customer(String customerId, String name, String phoneNumber) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty, please provide a valid name");
        if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("Phone number cannot be empty, please provide a valid phone number");
        this.customerId = customerId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getDetails() {
        return "\nID: " + customerId + 
        "\nName: " + name + 
        "\nPhone: " + phoneNumber;
    }

    public String getName() { return name;}
    public String getPhoneNumber() { return phoneNumber;}
    public String getCustomerId() { return customerId;}
}