package screen;

import java.util.List;
import java.util.Scanner;

import account_manager.ChangePassword;
import controller.AppointmentOutcomeCon;
import controller.InventoryCon;
import controller.ReplenishCon;
import entity.staff;

public class PharmacistMenu implements UserMenu{

    static Scanner sc = new Scanner(System.in);
    
    public void showMenu(String userID, List<staff> lines)
    {
        int menuChoice;

        do
        {
            System.out.println("\n\nPharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");

            System.out.print("Enter your choice: ");
            menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    // View Appointment Outcome Record
                    AppointmentOutcomeCon.viewOutcomeRecord();
                    break;
                
                case 2:
                    // Update Prescription Status
                    AppointmentOutcomeCon.updateStatus();
                    break;

                case 3:
                    // View Medication Inventory
                    InventoryCon.viewInventory();
                    break;

                case 4:
                    // Submit Replenishment Request
                    ReplenishCon.replenishmentRequest();
                    break;

                case 5:
                    // Change Password
                    ChangePassword.changeStaffPassword(userID, lines);
                    break;
                
                case 6:
                    // Logout
                    System.out.println("Logging out...");
                    break;
            
                default:
                    System.out.println("Invalid choice selected. Please try again.");
                    break;
            }

        } while(menuChoice!=6);
    }
}