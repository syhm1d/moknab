import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Adam Syahmi
 * Main Execution controller operating user interactions via terminal scanning.
 */
public class RestaurantSystem {
    private String restaurantName;
    private ArrayList<Order> activeOrders;
    private MenuItem[] menuInventory; // Mandatory Array deployment

    public RestaurantSystem(String restaurantName) {
        this.restaurantName = restaurantName;
        this.activeOrders = new ArrayList<>();
        initializeCatalog();
    }

    private void initializeCatalog() {
        menuInventory = new MenuItem[4];
        menuInventory[0] = new NasiKerabu("NK01", "Nasi Kerabu Ayam Bakar", 12.00, "Blue", "Ayam Bakar", true);
        menuInventory[1] = new NasiKerabu("NK02", "Nasi Kerabu Daging Salai", 15.00, "Blue", "Daging Salai", false);
        menuInventory[2] = new SideDish("SD01", "Solok Lada Extra", 2.00, "Standard", false);
        menuInventory[3] = new SideDish("SD02", "Keropok Lekor Crispy", 5.00, "Large", true);
    }

    public void showMenuOptions() {
        System.out.println("\n--- " + restaurantName.toUpperCase() + " MENU ---");
        for (int i = 0; i < menuInventory.length; i++) {
            System.out.println(" [" + (i + 1) + "] " + menuInventory[i].getItemName() + " | Price: RM" + menuInventory[i].calculatePrice());
        }
        System.out.println(" -----------------------------------------");
    }

    public static void main(String[] args) {
        RestaurantSystem system = new RestaurantSystem("Group 5 Nasi Kerabu Central Kiosk");
        Scanner console = new Scanner(System.in);
        
        System.out.println("======= WELCOME USER WELCOME =======");
        System.out.print("Please enter your name: ");
        String nameInput = console.nextLine();
        System.out.print("Please enter your phone number: ");
        String phoneInput = console.nextLine();

        Customer userCustomer = new Customer("C-LIVE", nameInput, phoneInput);
        Order activeOrder = new Order("ORD-" + (System.currentTimeMillis() % 1000), userCustomer);
        
        boolean loopFlag = true;
        
        while (loopFlag) {
            system.showMenuOptions();
            System.out.println("Select choices:\n [1-4] Add Dish\n [5] Checkout Basket\n [6] Cancel Process");
            System.out.print("Enter choice option: ");
            
            try {
                int menuChoice = console.nextInt();
                
                if (menuChoice >= 1 && menuChoice <= 4) {
                    MenuItem targetedDish = system.menuInventory[menuChoice - 1];
                    activeOrder.addItem(targetedDish);
                    System.out.println(">>> Added: " + targetedDish.getItemName());
                } 
                else if (menuChoice == 5) {
                    loopFlag = false;
                    
                    // Core Execution & Billing
                    double amountOwed = activeOrder.computeFinalBill();
                    
                    // Instantiate structural printer helper dependency
                    OrderReceipt billingPrinter = new OrderReceipt(activeOrder);
                    billingPrinter.printInvoice(amountOwed);
                    
                    // Reward point logic updates
                    int computedPoints = (int) (amountOwed / 10) * 5;
                    userCustomer.addPoints(computedPoints);
                    activeOrder.setOrderStatus("Completed");
                    
                    // Trigger interface method calls polymorphically
                    System.out.println("\n--- Dispatched items to production workflows ---");
                    for (MenuItem item : activeOrder.getOrderItems()) {
                        if (item instanceof Preparable) {
                            Preparable designInterfacePointer = (Preparable) item;
                            designInterfacePointer.startPreparation();
                            designInterfacePointer.completePreparation();
                        }
                    }
                    
                    // Validate custom exception validations
                    System.out.print("\nWould you like to test loyalty points redemption? (yes/no): ");
                    console.nextLine(); // clean text buffer
                    String loyaltyChoice = console.nextLine();
                    if (loyaltyChoice.equalsIgnoreCase("yes")) {
                        System.out.print("Enter points value to spend: ");
                        int pointTarget = console.nextInt();
                        userCustomer.redeemPoints(pointTarget);
                    }
                } 
                else if (menuChoice == 6) {
                    loopFlag = false;
                    System.out.println("Order cancelled. Clearing session window.");
                } 
                else {
                    System.out.println("[WARNING] Code invalid. Choose selection choices between index 1 and 6.");
                }
                
            } catch (InputMismatchException err) {
                System.out.println("\n[INPUT FAULT CODE] Numbers only are accepted inside selection windows.");
                console.next(); // Clear scanner buffer track references
            } catch (OrderException businessException) {
                System.out.println("\n[TRANSACTION EXCEPTION BLOCKED]: " + businessException.getMessage());
            }
        }
        
        console.close();
        System.out.println("\nExecution complete. Kiosk closed cleanly.");
        System.out.println("Thank you for visiting " + system.restaurantName + "!");
    }
}