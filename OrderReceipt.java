/**
 * @author Haiqal Razeeq / Afiq Irfan
 * Helper component representing system composition behavior to display receipts.
 */
public class OrderReceipt {
    private Order trackingOrder;

    public OrderReceipt(Order trackingOrder) {
        this.trackingOrder = trackingOrder;
    }

    public void printInvoice(double totalAmount) {
        System.out.println("\n=============================================");
        System.out.println("               FINAL INVOICE                 ");
        System.out.println("=============================================");
        System.out.println("Order Reference: " + trackingOrder.getOrderId());
        System.out.println("Customer Profile: " + trackingOrder.getCustomer().getDetails());
        System.out.println("---------------------------------------------");
        
        for (MenuItem item : trackingOrder.getOrderItems()) {
            System.out.printf(" * %-28s : RM%.2f\n", item.getItemName(), item.calculatePrice());
        }
        
        System.out.println("---------------------------------------------");
        System.out.printf("TOTAL AMOUNT CHARGED: RM%.2f\n", totalAmount);
        System.out.println("=============================================\n");
    }
}