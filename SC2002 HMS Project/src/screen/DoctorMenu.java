package screen;

import java.util.Scanner;

public class DoctorMenu {

    public static void displayDoctorMenu(String doctorID) {
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
    
            option = sc.nextInt();
            sc.nextLine();

            switch(option){
                case 1:
                    System.out.println("\n--- Viewing Patient Medical Records ---");
                    
                    break;
                case 2:
                    System.out.println("\n--- Update Patient Medical Record ---");
                    
                    break;
                case 3:
                    System.out.println("\n--- Viewing Personal Schedule ---");
                    break;
                case 4:
                    System.out.println("\n--- Set Availability ---");
                    break;
                case 5:
                    System.out.println("\n--- Showing Appointment Requests ---");

                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        }while(option!=8);
    }
    
}