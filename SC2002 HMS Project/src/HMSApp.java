import java.util.List;
import java.util.Scanner;
import entity.*;
import enum_class.*;
import screen.*;
import screen.AdminMenu;
import screen.DoctorMenu;
import screen.PatientMenu;
import screen.PharmacistMenu;
import account_manager.Login;

/**
 * The HMSApp class is the entry point of the Hospital Management System (HMS) application.
 * It handles user login for both patients and staff members (Doctor, Pharmacist, and Administrator) 
 * and navigates them to their respective menus based on their roles.
 */
public class HMSApp {

    /**
     * The main method initiates the application, prompts the user to log in, and navigates to 
     * the corresponding menu based on the user role.
     * 
     * It allows three options:
     * 1. Patient Login
     * 2. Staff Login (Doctor, Pharmacist, Administrator)
     * 3. Exit the system
     *
     * Based on the selected option, the appropriate login method is invoked and the user is
     * directed to their respective menu after successful authentication.
     *
     * @param args The command-line arguments (not used in this application).
     * @throws Exception If an error occurs during the login or menu navigation process.
     */
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("\n\n----- Login -----");
            System.out.println("P: Patient Login");
            System.out.println("S: Staff Login");
            System.out.println("E: Exit");

            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            if(choice.equals("P") || choice.equals("S"))
            {
                // Patient login
                if(choice.equals("P"))
                {
                    Object[] toBeUsed = Login.patientLogin();
                    PatientMenu.showMenu((String) toBeUsed[0], (List<patient>) toBeUsed[1]);
                }

                // Staff login
                if(choice.equals("S"))
                {
                    Object[] toBeUsed = Login.staffLogin();
                    Role role = (Role) toBeUsed[0];
                    switch (role) {
                        case Doctor:
                            UserMenu doctorMenu = new DoctorMenu(); // Polymorphism for Doctor Menu    
                            doctorMenu.showMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
                            break;
                        
                        case Pharmacist:
                            UserMenu pharmacistMenu = new PharmacistMenu(); // Polymorphism for Pharmacist Menu    
                            pharmacistMenu.showMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
                            break;

                        case Administrator:
                            UserMenu adminMenu = new AdminMenu(); // Polymorphism for Admin Menu
                            adminMenu.showMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
                            break;
                    
                        default:
                            break;
                    }
                }
            }

            else if(choice.equals("E"))
            {
                System.out.println("Exiting the system...");
                break;
            }

            else
            {
                System.out.println("You have entered an invalid choice! Try again!\n\n");
                continue;
            }
        }

        sc.close();
    }
}