package screen;

import java.util.List;
import java.util.Scanner;

import account_manager.ChangePassword;
import controller.AppointmentOutcomeCon;
import entity.staff;

public class PharmacistMenu {

    public static void menu(String userID, List<staff> lines)
    {
        Scanner sc = new Scanner(System.in);
        int menuChoice;

        do
        {
            System.out.println("\n\nPharmacist Menu:");
            System.out.println("\t1: View Appointment Outcome Record");
            System.out.println("\t2: Update Prescription Status");
            System.out.println("\t3: View Medication Inventory");
            System.out.println("\t4: Submit Replenishment Request");
            System.out.println("\t5: Change Password");
            System.out.println("\t6: Logout");

            System.out.print("Enter your choice: ");
            menuChoice = sc.nextInt();

            switch (menuChoice) {
                case 1:
                    // View Appointment Outcome Record
                    AppointmentOutcomeCon.viewOutcomeRecord();
                    break;
                
                case 2:
                    // Update Prescription Status
                    //Pharmacist.updateStatus();
                    break;

                case 3:
                    // View Medication Inventory
                    //Pharmacist.viewInventory();
                    break;

                case 4:
                    // Submit Replenishment Request
                    //Pharmacist.replenishmentRequest();
                    break;

                case 5:
                    // Change Password
                    ChangePassword.changeStaffPassword(userID, lines);
                    break;
                
                case 6:
                    // Logout
                    break;
            
                default:
                    System.out.println("Invalid choice selected. Please try again.");
                    break;
            }

        } while(menuChoice!=6);
    }
}