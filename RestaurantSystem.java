import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * @author Afiq Irfan
 */
public class RestaurantSystem {
    private String restaurantName;
    private MenuItem[] menuInventory;

    public RestaurantSystem(String restaurantName) {
        this.restaurantName = restaurantName;
        initializeCatalog();
    }

    private void initializeCatalog() {
        menuInventory = new MenuItem[12];
        
        //MAKANAN
        menuInventory[0] = new NasiKerabu("NK01", "Nasi Kerabu Ayam Bakar", 12.00);
        menuInventory[1] = new NasiKerabu("NK02", "Nasi Kerabu Daging Bakar", 15.00);
        menuInventory[2] = new NasiKukus("NK03", "Nasi Kukus Ayam Berrempah", 10.00);
        
        //MINUMAN
        menuInventory[3] = new Drink("W01", "Teh O Ais", 2.00);
        menuInventory[4] = new Drink("W02", "Teh Ais", 2.50);
        menuInventory[5] = new Drink("W03", "Milo Ais", 3.00);
        menuInventory[6] = new Drink("W04", "Kopi Ais", 2.50);
        menuInventory[7] = new Drink("W05", "Extra Joss", 2.00);
        
        //SAMPINGAN
        menuInventory[8] = new SideDish("SD01", "Nasi Lebih", 2.00);
        menuInventory[9] = new SideDish("SD02", "Lemak Bakar", 3.00);
        menuInventory[10] = new SideDish("SD03", "Solok Lada", 2.00);
        menuInventory[11] = new SideDish("SD04", "Keropok Lekor Krispi", 5.00);
    }

    public void showCategorizedMenu(int stage) {
            System.out.println("===============================================");
            System.out.println("                    MOKNAB                     "); 
            System.out.println("===============================================");   
            System.out.println("-----------------------------------------------");
        if (stage == 1) {
            System.out.println("           Please select main dishes           ");
            System.out.println("-----------------------------------------------");
            for (int i = 0; i <= 2; i++) {
                System.out.printf("  [%d] %-25s | RM%.2f\n", (i + 1), menuInventory[i].getItemName(), menuInventory[i].calculatePrice());
            }
        } else if (stage == 2) {
            System.out.println("             Please select drinks              ");
            System.out.println("-----------------------------------------------");
            for (int i = 3; i <= 7; i++) {
                System.out.printf("  [%d] %-25s | RM%.2f\n", (i + 1), menuInventory[i].getItemName(), menuInventory[i].calculatePrice());
            }
        } else if (stage == 3) {
            System.out.println("          Please select side dishes            ");
            System.out.println("-----------------------------------------------");
            for (int i = 8; i <= 11; i++) {
                System.out.printf("  [%d] %-25s | RM%.2f\n", (i + 1), menuInventory[i].getItemName(), menuInventory[i].calculatePrice());
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.println("===============================================");
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private static Customer handleCustomerRegistration(Scanner console) {
        System.out.println("===============================================");
        System.out.println("                    MOKNAB                     "); 
        System.out.println("===============================================");
        System.out.print("Please enter your name: ");
        String nameInput = console.nextLine();
        System.out.print("Please enter your phone number: ");
        String phoneInput = console.nextLine();
        return new Customer("C001", nameInput, phoneInput);
    }

    private void processOrderingWorkflow(Scanner console, Customer customer, Order activeOrder) {
        int currentStage = 1;
        boolean systemRunning = true;

        while (systemRunning) {
            clearScreen();
            this.showCategorizedMenu(currentStage);
            
            if (currentStage == 1) System.out.println("  [1-3] Add Main dish to Order");
            else if (currentStage == 2) System.out.println("  [4-8] Add Drink to Order");
            else if (currentStage == 3) System.out.println("  [9-12] Add Side Dish to Order");

            System.out.println("  [13]  Confirm & Proceed");
            System.out.println("  [14]  Cancel Order");
            System.out.println("===============================================");
            System.out.print("Enter your choice: ");
            
            try {
                int menuChoice = console.nextInt();
                
                if (menuChoice == 13) {
                    if (currentStage < 3) {
                        currentStage++;
                        System.out.println("\n>>> Moving to next menu category...");
                        try { Thread.sleep(1000); } catch (InterruptedException e) {}
                    } else {
                        systemRunning = false;
                        finalizeCheckout(console, activeOrder);
                    }
                } else if (menuChoice == 14) {
                    systemRunning = false;
                    System.out.println("\nOrder cancelled. Clearing session window.");
                    try { Thread.sleep(1200); } catch (InterruptedException e) {}
                    clearScreen();
                } else {
                    MenuItem targetedDish = null;
                    if (currentStage == 1 && (menuChoice >= 1 && menuChoice <= 3)) targetedDish = menuInventory[menuChoice - 1];
                    else if (currentStage == 2 && (menuChoice >= 4 && menuChoice <= 8)) targetedDish = menuInventory[menuChoice - 1];
                    else if (currentStage == 3 && (menuChoice >= 9 && menuChoice <= 12)) targetedDish = menuInventory[menuChoice - 1];

                    if (targetedDish != null) {
                        activeOrder.addItem(targetedDish);
                        System.out.println("\n>>> Added to Order: " + targetedDish.getItemName());
                        System.out.println("Press Enter to continue adding items or select [13] to Confirm...");
                        console.nextLine(); console.nextLine();
                    } else {
                        System.out.println("\n[WARNING] Invalid selection for the current menu stage!");
                        System.out.println("Press Enter to try again...");
                        console.nextLine(); console.nextLine();
                    }
                }
            } catch (InputMismatchException err) {
                System.out.println("\n[ERROR] Invalid input type! Please enter a valid integer corresponding to the menu options.");
                console.next();
                System.out.println("Press Enter to try again...");
                console.nextLine(); console.nextLine();
            } catch (OrderException businessException) {
                System.out.println("\n[ERROR] " + businessException.getMessage());
                System.out.println("Press Enter to continue...");
                console.nextLine(); console.nextLine();
            }
        }
    }

    private static void finalizeCheckout(Scanner console, Order activeOrder) throws OrderException {
        clearScreen();
        
        double amountOwed = activeOrder.computeFinalBill();
        OrderReceipt billingPrinter = new OrderReceipt(activeOrder);
        activeOrder.setOrderStatus("Completed");
        System.out.println("===============================================");
        System.out.println("                    MOKNAB                     "); 
        System.out.println("===============================================");
        System.out.println("---------- Preparation in Progress ------------");
        for (MenuItem item : activeOrder.getOrderItems()) {
            if (item instanceof Preparable) {
                ((Preparable) item).startPreparation();
                ((Preparable) item).completePreparation();
            }
        }
        System.out.println("===============================================");
        console.nextLine(); console.nextLine();
        clearScreen();
        billingPrinter.printInvoice(amountOwed);
        System.out.println("\nPlease make payment by showing the invoice above.");
        System.out.println("Press Enter to finalize the session...");
        console.nextLine(); console.nextLine();
    }

    public static void main(String[] args) {
        RestaurantSystem system = new RestaurantSystem("MOKNAB");
        Scanner console = new Scanner(System.in);
        
        Customer userCustomer = handleCustomerRegistration(console);
        Order activeOrder = new Order("ORD-" + (System.currentTimeMillis() % 1000), userCustomer);
        
        system.processOrderingWorkflow(console, userCustomer, activeOrder);
        
        console.close();
        System.out.println("Thank you for visiting " + system.restaurantName + "!");
    }
}