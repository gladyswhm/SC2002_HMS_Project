package account_manager;

import java.util.List;
import java.util.Scanner;
import loader.Read;
import entity.*;

public class Login {

    static Scanner sc = new Scanner(System.in);

    // Patient login
    public static Object[] patientLogin()
    {
        // filePath set to Patient_List
        String filePath = "data/Patient_List.csv";

        // Go to read method to read csv file
        List<patient> lines = Read.readPatientList(filePath);

        // Used to check if login is successful
        boolean loginSuccess = false;

        System.out.println("\n\n----- Login -----");

        while(!loginSuccess)
        {
            System.out.print("Patient ID: ");
            String patientID = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            // Used to check if there is such user
            boolean userFound = false;

            // Traverse each line from the file
            for (patient line : lines)
            {
                // Check if the entered userID and password match the data in the file
                if (line.getUserID().equals(patientID) && line.getPassword().equals(password))      // If found match
                {
                    System.out.println("Login successful. Welcome!\n\n");
                    userFound = true;
                    loginSuccess = true;
                    return new Object[]{line.getUserID(), lines};
                }
                else if (line.getUserID().equals(patientID))     // If user found but no matching password
                {
                    userFound = true;
                }
            }

            if (!loginSuccess)          // If login is not successful
            {
                if (userFound)          // But user is found
                {
                    System.out.println("Incorrect password. Please try again.\n\n");
                }
                else                    // Because no user was found
                {
                    System.out.println("Patient ID not found. Please try again.\n\n");
                }
            }
        }
        return null;
    }


    // Staff login
    public static Object[] staffLogin()
    {
        // filePath set to Staff_List
        String filePath = "data/Staff_List.csv";

        // Go to read method to read csv file
        List<staff> lines = Read.loadStaffList(filePath);

        // Used to check if login is successful
        boolean loginSuccess = false;

        System.out.println("\n\n----- Login -----");

        while(!loginSuccess)
        {
            System.out.print("Staff ID: ");
            String staffID = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            // Used to check if there is such user
            boolean userFound = false;

            // Traverse each line from the file
            for (staff line : lines)
            {
                // Check if the entered userID and password match the data in the file
                if (line.getUserID().equals(staffID) && line.getPassword().equals(password))      // If found match
                {
                    System.out.println("Login successful. Welcome!\n\n");
                    userFound = true;
                    loginSuccess = true;
                    return new Object[]{line.getRole(), line.getUserID(), lines};
                }
                else if (line.getUserID().equals(staffID))     // If user found but no matching password
                {
                    userFound = true;
                }
            }

            if (!loginSuccess)          // If login is not successful
            {
                if (userFound)          // But user is found
                {
                    System.out.println("Incorrect password. Please try again.\n\n");
                }
                else                    // Because no user was found
                {
                    System.out.println("Staff ID not found. Please try again.\n\n");
                }
            }
        }
        return null;
    }
}