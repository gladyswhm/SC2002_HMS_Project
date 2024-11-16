package account_manager;

import java.util.List;
import java.util.Scanner;

import entity.patient;
import entity.staff;
import loader.Write;

public class ChangePassword {

    static Scanner sc = new Scanner(System.in);

    
    // Patient change password
    public static void changePatientPassword(String userID, List<patient> lines)
    {
        // Used to check if change password is successful
        boolean changeSuccess = false;

        System.out.println("\n\n----- Change Password -----");


        while(!changeSuccess)
        {
            System.out.print("Existing Password: ");
            String existingPassword = sc.nextLine();
            System.out.print("New Password: ");
            String newPassword = sc.nextLine();
            System.out.print("Confirm New Password: ");
            String confirmPassword = sc.nextLine();

            // Traverse each line from the file
            for (patient line : lines)
            {
                // Check if the entered userID and password match the data in the file
                if (line.getUserID().equals(userID) && line.getPassword().equals(existingPassword))         // If found match
                {
                    if (newPassword.equals(confirmPassword))        // If new password and confirm password is the same then allow change
                    {
                        line.setPassword(newPassword);
                        Write.savePatientListToCSV(lines);

                        System.out.println("Password changed successfully.\n\n");
                        changeSuccess = true;   // Indicate that the password change was successful
                        break;
                    }
                }     
            }

            if(!changeSuccess)
            {
                System.out.println("Either your existing password is wrong or your new password and confirm new password does not match. Try again.\n\n");
            }
        }
    }

    
    // Staff change password
    public static void changeStaffPassword(String userID, List<staff> lines)
    {
        // Used to check if change password is successful
        boolean changeSuccess = false;

        System.out.println("\n\n----- Change Password -----");


        while(!changeSuccess)
        {
            System.out.print("Existing Password: ");
            String existingPassword = sc.nextLine();
            System.out.print("New Password: ");
            String newPassword = sc.nextLine();
            System.out.print("Confirm New Password: ");
            String confirmPassword = sc.nextLine();

            // Traverse each line from the file
            for (staff line : lines)
            {
                // Check if the entered userID and password match the data in the file
                if (line.getUserID().equals(userID) && line.getPassword().equals(existingPassword))         // If found match
                {
                    if (newPassword.equals(confirmPassword))        // If new password and confirm password is the same then allow change
                    {
                        line.setPassword(newPassword);
                        Write.saveStaffList(lines);

                        System.out.println("Password changed successfully.\n\n");
                        changeSuccess = true;   // Indicate that the password change was successful
                        break;
                    }
                }     
            }

            if(!changeSuccess)
            {
                System.out.println("Either your existing password is wrong or your new password and confirm new password does not match. Try again.\n\n");
            }
        }
    }
}