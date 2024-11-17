import java.util.List;
import java.util.Scanner;
import entity.*;
import enum_class.*;
import screen.AdminMenu;
import screen.DoctorMenu;
import screen.PatientMenu;
import screen.PharmacistMenu;
import account_manager.Login;

// public class HMSApp {
//     public static void main(String[] args) throws Exception {
        
//         Scanner sc = new Scanner(System.in);

//         while(true)
//         {
//             System.out.println("\n\n----- Login -----");
//             System.out.println("P: Patient Login");
//             System.out.println("S: Staff Login");
//             System.out.println("E: Exit");

//             System.out.print("Choose an option: ");
//             String choice = sc.nextLine();

//             if(choice.equals("P") || choice.equals("S"))
//             {
//                 // Patient
//                 if(choice.equals("P"))
//                 {
//                     Object[] toBeUsed = Login.patientLogin();
//                     PatientMenu.showMenu((String) toBeUsed[0], (List<patient>) toBeUsed[1]);


//                 }

//                 // Staff
//                 if(choice.equals("S"))
//                 {
//                     Object[] toBeUsed = Login.staffLogin();
//                     Role role = (Role) toBeUsed[0];
//                     switch (role) {
//                         case Doctor:
//                             DoctorMenu.displayDoctorMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
//                             break;
                        
//                         case Pharmacist:
//                             PharmacistMenu.menu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
//                             break;

//                         case Administrator:
//                             AdminMenu.showMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
//                             break;
                    
//                         default:
//                             break;
//                     }
//                 }
//             }

//             else if(choice.equals("E"))
//             {
//                 break;
//             }

//             else
//             {
//                 System.out.println("You have entered an invalid choice! Try again!\n\n");
//                 continue;
//             }
//         }

//         sc.close();
//     }
// }



import screen.*;

public class HMSApp {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n----- Login -----");
            System.out.println("P: Patient Login");
            System.out.println("S: Staff Login");
            System.out.println("E: Exit");

            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            if (choice.equals("P")) {
                // Patient Login
                Object[] toBeUsed = Login.patientLogin();
                PatientMenu.showMenu((String) toBeUsed[0], (List<patient>) toBeUsed[1]);
            } 
            else if (choice.equals("S")) {
                // Staff Login
                Object[] toBeUsed = Login.staffLogin();
                Role role = (Role) toBeUsed[0];

                if (role == Role.Administrator) {
                    UserMenu adminMenu = new AdminMenu(); // Polymorphism applied for Admin Menu
                    adminMenu.showMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
                } else {
                    switch (role) {
                        case Doctor:
                            DoctorMenu.displayDoctorMenu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
                            break;
                        case Pharmacist:
                            PharmacistMenu.menu((String) toBeUsed[1], (List<staff>) toBeUsed[2]);
                            break;
                        default:
                            System.out.println("Invalid role!");
                            continue;
                    }
                }
            } 
            else if (choice.equals("E")) {
                System.out.println("Exiting the system...");
                break;
            } 
            else {
                System.out.println("You have entered an invalid choice! Try again!\n");
            }
        }

        sc.close();
    }
}
