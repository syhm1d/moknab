import java.util.ArrayList;
/**
 * @author Haiqal Razeeq
 */
public class Order {
    private String orderId;
    private ArrayList<MenuItem> orderItems; //Association with MenuItem class
    private String orderStatus;
    private double totalPrice;
    private Customer customer; // Association with Customer class

    public Order(String orderId, Customer customer) { //Constructor to initialize the Order with the associated Customer
        this.orderId = orderId;
        this.customer = customer;
        this.orderItems = new ArrayList<>(); // Initialize the orderItems list
        this.orderStatus = "Pending";
        this.totalPrice = 0.0;
    }

    public void addItem(MenuItem item) { //apply association with MenuItem class
        orderItems.add(item);
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
    }

    public double computeFinalBill() throws OrderException { //apply exception handling
        if (orderItems.isEmpty()) {
            throw new OrderException("Order Error: Cannot checkout an empty shopping basket for Order ID: " + orderId);
        }
        
        totalPrice = 0.0;
        for (MenuItem item : orderItems) {
            totalPrice += item.calculatePrice(); 
        }
        return totalPrice;
    }

    public ArrayList<MenuItem> getOrderItems() { return orderItems; } //apply encapsulation
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; } //apply encapsulation
    public String getOrderStatus() { return orderStatus; }
}