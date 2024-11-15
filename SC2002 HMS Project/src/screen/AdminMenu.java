package screen;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import controller.AdminCon;
import controller.InventoryCon;
import entity.medicine;
import enum_class.*;

public class AdminMenu {

    public void showMenu() { 
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                	manageStaff(); 
                    break;
                case 2:
                	//admin.displayAppointments();
                    break;
                case 3:
                    manageInventory(); 
                    break;
                case 4:
                	approveReplenishmentRequests();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 5);

        
    }
    
    private void manageStaff() {
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        AdminCon adminCon = new AdminCon();

        do {
            System.out.println("\n--- Staff Management ---");
            System.out.println("1. Add Staff");
            System.out.println("2. Remove Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Display Staff");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // Add staff
                    System.out.print("Enter staff ID: ");
                    String staffId = scanner.nextLine();
                    System.out.print("Enter staff password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    Gender gender = null;
                    while (gender == null) {
                        System.out.print("Enter gender (Male/Female): ");
                        String genderInput = scanner.nextLine();
                        try {
                            gender = Gender.valueOf(genderInput);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid gender. Please enter MALE or FEMALE.");
                        }
                    }

                    // Handle role input
                    Role role = null;
                    while (role == null) {
                        System.out.print("Enter role (Admin/Doctor/Pharmacist): ");
                        String roleInput = scanner.nextLine();
                        try {
                            role = Role.valueOf(roleInput);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid role. Please enter a valid role.");
                        }
                    }
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    adminCon.addStaff(staffId, password, name, gender, role, age);
                    break;
                case 2:
                    // Remove staff
                    System.out.print("Enter staff ID to remove: ");
                    String staffIdToRemove = scanner.nextLine();
                    adminCon.removeStaff(staffIdToRemove);
                    break;
                case 3:
                    // Update staff
                    System.out.print("Enter current staff ID to update: ");
                    String staffIdToUpdate = scanner.nextLine();
                    System.out.print("Enter new staff ID: ");
                    String newStaffId = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new role: ");
                    String newRole = scanner.nextLine();
                    System.out.print("Enter new gender: ");
                    String newGender = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    adminCon.updateStaff(staffIdToUpdate, newStaffId, newName, newRole, newGender, newAge);
                    break;
                case 4:
                    System.out.print("Enter filter type (role/gender/age) or 'all' to display all: ");
                    String filterBy = scanner.nextLine();
                    String value = "";
                    if (!filterBy.equalsIgnoreCase("all")) {
                        System.out.print("Enter value for filter: ");
                        value = scanner.nextLine();
                    }
                    adminCon.displayStaff(filterBy, value);
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 5);
    }
    
    
    public void manageInventory() { 
        InventoryCon inventoryCon = new InventoryCon();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
        	System.out.println("\n--- View and Manage Medication Inventory ---");
            System.out.println("1. Add Medication");
            System.out.println("2. Update Stock Level");
            System.out.println("3. Remove Medication");
            System.out.println("4. View Medication Inventory");
            System.out.println("5. Update Low Stock Alert");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); 
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1: // Add Medicine
                    System.out.print("Enter medicine name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter stock level: ");
                    int stockLevel = scanner.nextInt();
                    System.out.print("Enter low stock alert level: ");
                    int lowStockAlert = scanner.nextInt();
                    medicine newMedicine = new medicine(name, stockLevel, lowStockAlert);
                    inventoryCon.addMedicine(newMedicine); 
                    break;

                case 2: // Update Stock Level
                    System.out.print("Enter medicine name to update stock level: ");
                    String medNameToUpdate = scanner.nextLine();
                    System.out.print("Enter new stock level: ");
                    int newStockLevel = scanner.nextInt();
                    inventoryCon.updateStockLevel(medNameToUpdate, newStockLevel);
                    break;

                case 3: // Remove Medicine
                    System.out.print("Enter medicine name to remove: ");
                    String medNameToRemove = scanner.nextLine();
                    inventoryCon.removeMedicine(medNameToRemove);
                    break;

                case 4: // View Medicines
                    System.out.println("Current Medication Inventory:");
                    for (medicine medicine : inventoryCon.getMedicineList()) {
                        System.out.println(medicine);
                    }
                    break;

                case 5: // Update Low Stock Alert
                    System.out.print("Enter medicine name to update low stock alert: ");
                    String medNameToUpdateAlert = scanner.nextLine();
                    System.out.print("Enter new low stock alert level: ");
                    int newLowStockAlert = scanner.nextInt();
                    inventoryCon.updateLowStockAlert(medNameToUpdateAlert, newLowStockAlert);
                    break;

                case 6: // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private void approveReplenishmentRequests() {
        List<Replenishment> replenishmentList = SystemInitialization.readReplenishmentList("Replenishment_List.csv");
        ReplenishmentRequest replenishmentRequest = ReplenishmentRequest.getInstance(replenishmentList);

        // Retrieve the singleton inventory instance
        Inventory inventory = Inventory.getInstance();

        Scanner scanner = new Scanner(System.in);
        
        // Filter out approved or rejected requests
        List<Replenishment> pendingRequests = replenishmentList.stream()
            .filter(request -> !request.getStatus().equalsIgnoreCase("Approved") && !request.getStatus().equalsIgnoreCase("Rejected"))
            .collect(Collectors.toList());
        
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending replenishment requests found.");
            return;
        }

        for (Replenishment request : pendingRequests) {
            System.out.println("\nRequest Details:");
            System.out.println("Name: " + request.getMedicineName());
            System.out.println("Quantity: " + request.getRequestedAmount());
            System.out.println("Current Status: " + request.getStatus());
            System.out.print("Approve this request? (yes/no): ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("yes")) {
                // Update replenishment status
            	admin.updateReplenishmentStatus(request.getMedicineName(), request.getRequestedAmount(), "Approved");
                
                // Update inventory stock level
                inventory.updateStockLevel(request.getMedicineName(), request.getRequestedAmount());
                System.out.println("Inventory updated for " + request.getMedicineName() + ": Stock level increased by " + request.getRequestedAmount());
            } else {
                admin.updateReplenishmentStatus(request.getMedicineName(), request.getRequestedAmount(), "Rejected");
            }
        }

        System.out.println("Replenishment requests have been processed.");
    }


}