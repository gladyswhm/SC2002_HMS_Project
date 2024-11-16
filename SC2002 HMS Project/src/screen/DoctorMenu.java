package screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.AvailabilityCon;
import controller.AppointmentCon;
import controller.DoctorCon;
import entity.medicalrecord;

public class DoctorMenu {
    private static List<medicalrecord> medicalRecords = new ArrayList<>();

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
                    DoctorCon.displayMedicalRecords();
                    break;
                case 2:
                    System.out.println("\n--- Update Patient Medical Record ---");
                    DoctorCon.displayAvailablePatientIds();
                    DoctorCon.updatePatientMedicalRecords(medicalRecords, sc);
                    break;
                case 3:
                    System.out.println("\n--- Viewing Personal Schedule ---");

                    break;
                case 4:
                    System.out.println("\n--- Set Availability ---");
                    AvailabilityCon.setAvailability(doctorID, sc);
                    break;
                case 5:
                    System.out.println("\n--- Showing Appointment Requests ---");
                    AppointmentCon.displayPendingAppointments(doctorID);
                    AppointmentCon.AppointmentUpdate(doctorID, sc); //accept and decline appointment request
                    break;
                case 6:
                    AppointmentCon.displayUpcomingAppointments(doctorID);//view upcoming appointments
                    break;
                case 7:
                    AppointmentCon.AppointmentOutcomeRecord(doctorID, sc);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        }while(option!=8);
    }
    //public static void main(String[] args){

        //DoctorMenu doctormenu = new DoctorMenu();  
        //doctormenu.displayDoctorMenu("D1001");  
    //}
}