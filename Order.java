import java.util.ArrayList;
/**
 * @author Haiqal Razeeq
 */
public class Order {
    private String orderId;
    private ArrayList<MenuItem> orderItems;
    private String orderStatus;
    private double totalPrice;
    private Customer customer;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderItems = new ArrayList<>();
        this.orderStatus = "Pending";
        this.totalPrice = 0.0;
    }

    public void addItem(MenuItem item) {
        orderItems.add(item);
    }

    public void setOrderStatus(String status) {
        this.orderStatus = status;
    }

    public double computeFinalBill() throws OrderException {
        if (orderItems.isEmpty()) {
            throw new OrderException("Order Error: Cannot checkout an empty shopping basket for Order ID: " + orderId);
        }
        
        totalPrice = 0.0;
        for (MenuItem item : orderItems) {
            totalPrice += item.calculatePrice(); 
        }
        return totalPrice;
    }

    public ArrayList<MenuItem> getOrderItems() { return orderItems; }
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public String getOrderStatus() { return orderStatus; }
}