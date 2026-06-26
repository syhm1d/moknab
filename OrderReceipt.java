/**
 * @author Haiqal Razeeq
 */
public class OrderReceipt {
    private Order trackingOrder; //Association with Order class

    public OrderReceipt(Order trackingOrder) { //Constructor to initialize the OrderReceipt with the associated Order
        this.trackingOrder = trackingOrder;
    }

    public void printInvoice(double totalAmount) {
        System.out.println("===============================================");
        System.out.println("                    MOKNAB                     "); 
        System.out.println("===============================================");
        System.out.println("===============================================");
        System.out.println("                 FINAL INVOICE                 ");
        System.out.println("===============================================");
        System.out.println("Order Reference: " + trackingOrder.getOrderId());
        System.out.println("Customer Profile: " + trackingOrder.getCustomer().getDetails());
        System.out.println("-----------------------------------------------");
        
        for (MenuItem item : trackingOrder.getOrderItems()) {
            System.out.printf(" * %-28s : RM%.2f\n", item.getItemName(), item.calculatePrice());
        }
        
        System.out.println("-----------------------------------------------");
        System.out.printf("TOTAL AMOUNT CHARGED: RM%.2f\n", totalAmount);
        System.out.println("================================================");
    }
}