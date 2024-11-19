/**
 * This class represents the Administrator Menu where an administrator can manage hospital staff,
 * appointments, medication inventory, and approve replenishment requests.
 * It also allows the administrator to change their password and log out.
 */
package screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import account_manager.ChangePassword;
import controller.AdminCon;
import controller.InventoryCon;
import controller.ReplenishCon;
import controller.AppointmentCon;
import controller.AppointmentOutcomeCon;
import entity.appointment;
import entity.appointmentoutcome;
import entity.medicalrecord;
import entity.medicine;
import entity.replenish;
import entity.staff;
import enum_class.*;

public class AdminMenu implements UserMenu{

    static Scanner scanner = new Scanner(System.in);
    
    /**
     * Displays the main menu for the administrator with options to manage staff,
     * appointments, inventory, and other administrative functions.
     *
     * @param userID The user ID of the logged-in administrator.
     * @param staffList The list of hospital staff.
     */
    public void showMenu(String userID, List<staff> staffList) { 
        
        int choice;

        do {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                	manageStaff(); 
                    break;
                case 2:
                	displayAppointments();
                    break;
                case 3:
                    manageInventory(); 
                    break;
                case 4:
                	approveReplenishmentRequests();
                    break;
                case 5:
                    // Change Password
                    ChangePassword.changeStaffPassword(userID, staffList);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 6);

        
    }
    
    /**
     * Allows the administrator to manage hospital staff including adding, removing, updating,
     * and displaying staff members.
     */
    private static void manageStaff() {
        
       
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
                            System.out.println("Invalid gender. Please enter Male or Female.");
                        }
                    }

                    // Handle role input
                    Role role = null;
                    while (role == null) {
                        System.out.print("Enter role (Administrator/Doctor/Pharmacist): ");
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
                    Gender newGender = null;
                    while (newGender == null) {
                        System.out.print("Enter gender (Male/Female): ");
                        String genderInput = scanner.nextLine();
                        try {
                            newGender = Gender.valueOf(genderInput);  
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid gender. Please enter Male or Female.");
                        }
                    }

                    // Handle role input
                    Role newRole = null;
                    while (newRole == null) {
                        System.out.print("Enter role (Administrator/Doctor/Pharmacist): ");
                        String roleInput = scanner.nextLine();
                        try {
                            newRole = Role.valueOf(roleInput);  
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid role. Please enter a valid role.");
                        }
                    }
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    adminCon.updateStaff(staffIdToUpdate, newStaffId, newName, newGender, newRole, newAge);
                    break;
                case 4:
                    System.out.print("Enter filter type (Role/Gender/Age) or 'all' to display all: ");
                    String filterBy = scanner.nextLine();
                    String value = "";
                    if (!filterBy.equalsIgnoreCase("all")) {
                        System.out.print("Enter value for filter (Case sensitive): ");
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
    
    /**
     * Allows the administrator to manage medication inventory, including adding new medication,
     * updating stock levels, removing medication, and setting low stock alerts.
     */
    private static void manageInventory() { 
        InventoryCon inventoryCon = new InventoryCon();
        
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

                    // Check if medicine exists
                    medicine medToUpdate = inventoryCon.findMedicineByName(medNameToUpdate);
                    if (medToUpdate == null) {
                        System.out.println("Error: Medicine with name '" + medNameToUpdate + "' does not exist in the inventory.");
                    } else {
                        System.out.print("Enter new stock level: ");
                        int newStockLevel = scanner.nextInt();
                        inventoryCon.updateStockLevel(medNameToUpdate, newStockLevel);
                        System.out.println("Stock level updated successfully for " + medNameToUpdate + ".");
                    }
                    break;

                case 3: // Remove Medicine
                    System.out.print("Enter medicine name to remove: ");
                    String medNameToRemove = scanner.nextLine();
                    // Check if medicine exists
                    medicine medToRemove = inventoryCon.findMedicineByName(medNameToRemove);
                    if (medToRemove == null) {
                        System.out.println("Error: Medicine with name '" + medNameToRemove + "' does not exist in the inventory.");
                    } else {
                        inventoryCon.removeMedicine(medNameToRemove);
                        System.out.println("Medicine '" + medNameToRemove + "' has been successfully removed from the inventory.");
                    }
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

                    // Check if medicine exists
                    medicine medForAlert = inventoryCon.findMedicineByName(medNameToUpdateAlert);
                    if (medForAlert == null) {
                        System.out.println("Error: Medicine with name '" + medNameToUpdateAlert + "' does not exist in the inventory.");
                    } else {
                        System.out.print("Enter new low stock alert level: ");
                        int newLowStockAlert = scanner.nextInt();
                        inventoryCon.updateLowStockAlert(medNameToUpdateAlert, newLowStockAlert);
                        System.out.println("Low stock alert updated successfully for " + medNameToUpdateAlert + ".");
                    }
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

    /**
     * Approves or rejects replenishment requests based on the status and updates the inventory accordingly.
     */
    private static void approveReplenishmentRequests() {
        
    
        ReplenishCon replenishCon = new ReplenishCon();
        List<replenish> replenishList = replenishCon.getReplenishList();
    
        List<replenish> pendingRequests = replenishList.stream()
            .filter(request -> request.getStatus() == ReplenishStatus.Pending)
            .collect(Collectors.toList());
    
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending replenishment requests found.");
            return;
        }
    
        for (replenish request : pendingRequests) {
            System.out.println("\nRequest Details:");
            System.out.println("Name: " + request.getMedicineName());
            System.out.println("Quantity: " + request.getRequestedAmount());
            System.out.println("Current Status: " + request.getStatus());
            System.out.print("Approve this request? (yes/no): ");
            String input = scanner.nextLine();
    
            if (input.equalsIgnoreCase("yes")) {
                request.setStatus(ReplenishStatus.Approved);
                replenishCon.updateReplenishment(request.getMedicineName(), request.getRequestedAmount(), ReplenishStatus.Approved);
                System.out.println("Inventory updated for " + request.getMedicineName() + ": Stock level increased by " + request.getRequestedAmount());
            } else {
                request.setStatus(ReplenishStatus.Rejected);
                replenishCon.updateReplenishment(request.getMedicineName(), request.getRequestedAmount(), ReplenishStatus.Rejected);
            }
        }
    
        System.out.println("Replenishment requests have been processed.");
    }
    
    /**
     * Displays the appointment details and outcomes for the administrator to view or process.
     */
    private static void displayAppointments() {
    
        int choice;

        do {
            System.out.println("\n--- View Appointment Details ---");
            System.out.println("1. View All Appointments");
            System.out.println("2. View Appointment Outcome Records");
            System.out.println("3. Return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                AppointmentCon.displayAppointmentList();
                    break;

                case 2:
                    List<appointmentoutcome> records = AppointmentOutcomeCon.getAppointmentOutcomeList() ;
                    if (records.isEmpty()) {
                        System.out.println("No outcome records found.");
                    } else {
                        for (appointmentoutcome record : records) {
                            System.out.println(record);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 3);
    }
}