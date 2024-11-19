package account_manager;

import java.util.List;
import java.util.Scanner;

import entity.patient;
import entity.staff;
import loader.Write;

/**
 * This class allows users (patients or staff) to change their passwords.
 * It provides functionality for both patients and staff to update their passwords.
 */
public class ChangePassword {

    static Scanner sc = new Scanner(System.in);

    /**
     * Allows a patient to change their password.
     * The patient is required to enter their existing password, a new password, 
     * and confirm the new password. The change is successful if the new password 
     * matches the confirmation and the existing password is correct.
     *
     * @param userID  the user identifier of the patient
     * @param lines   the list of patients
     */
    public static void changePatientPassword(String userID, List<patient> lines) {

        // Used to check if change password is successful
        boolean changeSuccess = false;

        System.out.println("\n\n----- Change Password -----");

        while (!changeSuccess) {
            System.out.print("Existing Password: ");
            String existingPassword = sc.nextLine();
            System.out.print("New Password: ");
            String newPassword = sc.nextLine();
            System.out.print("Confirm New Password: ");
            String confirmPassword = sc.nextLine();

            // Traverse each patient in the list
            for (patient line : lines) {
                // Check if the entered userID and password match the patient's data
                if (line.getUserID().equals(userID) && line.getPassword().equals(existingPassword)) {
                    // If new password and confirm password are the same, allow password change
                    if (newPassword.equals(confirmPassword)) {
                        line.setPassword(newPassword);
                        Write.savePatientListToCSV(lines);

                        System.out.println("Password changed successfully.\n\n");
                        changeSuccess = true; // Indicate that the password change was successful
                        break;
                    }
                }
            }

            if (!changeSuccess) {
                System.out.println("Either your existing password is wrong or your new password and confirm new password do not match. Try again.\n\n");
            }
        }
    }

    /**
     * Allows a staff member to change their password.
     * The staff member is required to enter their existing password, a new password,
     * and confirm the new password. The change is successful if the new password 
     * matches the confirmation and the existing password is correct.
     *
     * @param userID  the user identifier of the staff
     * @param lines   the list of staff members
     */
    public static void changeStaffPassword(String userID, List<staff> lines) {

        // Used to check if change password is successful
        boolean changeSuccess = false;

        System.out.println("\n\n----- Change Password -----");

        while (!changeSuccess) {
            System.out.print("Existing Password: ");
            String existingPassword = sc.nextLine();
            System.out.print("New Password: ");
            String newPassword = sc.nextLine();
            System.out.print("Confirm New Password: ");
            String confirmPassword = sc.nextLine();

            // Traverse each staff member in the list
            for (staff line : lines) {
                // Check if the entered userID and password match the staff's data
                if (line.getUserID().equals(userID) && line.getPassword().equals(existingPassword)) {
                    // If new password and confirm password are the same, allow password change
                    if (newPassword.equals(confirmPassword)) {
                        line.setPassword(newPassword);
                        Write.saveStaffList(lines);

                        System.out.println("Password changed successfully.\n\n");
                        changeSuccess = true; // Indicate that the password change was successful
                        break;
                    }
                }
            }

            if (!changeSuccess) {
                System.out.println("Either your existing password is wrong or your new password and confirm new password do not match. Try again.\n\n");
            }
        }
    }
}
