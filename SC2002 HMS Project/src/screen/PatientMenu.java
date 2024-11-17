package screen;

import java.util.List;
import java.util.Scanner;

import account_manager.ChangePassword;
import controller.AppointmentCon;
import controller.AppointmentOutcomeCon;
import controller.PatientCon;
import entity.appointmentoutcome;
import entity.patient;

public class PatientMenu {

    static Scanner scanner = new Scanner(System.in);

    public static void showMenu(String userID, List<patient> patientList) { 
        
        int choice;

        do {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots"); //all available 
            System.out.println("4. Schedule an Appointment"); //choose by doctor
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Change Password");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    //done for view medical record
                    PatientCon.viewMedicalRecord(userID); 
                    break;
                case 2:
                    updatePersonalInfo(userID);
                    break;
                case 3:
                    
                    viewAvailableAppointments();
                    break;
                case 4:
                    PatientCon.scheduleAppointment(userID);
                    break;
                case 5:
                    PatientCon.rescheduleAppointment(userID);
                    break;
                case 6:
                    PatientCon.cancelAppointment(userID);
                    break;
                case 7:
                    viewScheduledAppointments(userID);
                    break;
                case 8:
                    viewPastAppointmentOutcomes(userID);
                    break;
                case 9:
                    // Change password
                    ChangePassword.changePatientPassword(userID, patientList);
                    break;
                case 10:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 10);
    }


    private static void viewMedicalRecord() {
        System.out.println("Displaying medical record...");

    }

    private static void updatePersonalInfo(String userID) {
    
        // Fetch the list of patients and find the matching patient using the userID
        List<patient> patients = PatientCon.getPatientList();
        patient patientToUpdate = null;
        for (patient p : patients) {
            if (p.getUserID().equals(userID)) {
                patientToUpdate = p;
                break;
            }
        }
    
        if (patientToUpdate != null) {
            System.out.println("Which information would you like to update?");
            System.out.println("1. Update Email");
            System.out.println("2. Update Phone Number");
            System.out.println("3. Update Both Email and Phone Number");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
    
            // Update the patient's contact info based on the user's choice
            PatientCon.updateContactInfo(patientToUpdate, choice);
            System.out.println("Personal information updated successfully.");
        } else {
            System.out.println("Patient ID not found.");
        }
    }
    


    private static void viewAvailableAppointments() {
        System.out.println("Displaying available appointment slots...");
        AppointmentCon.displayAvailableAppointmentList();
    }

    private static void scheduleAppointment() {
        System.out.println("Scheduling an appointment...");

    }

    private static void rescheduleAppointment() {
        System.out.println("Rescheduling an appointment...");
    }

    private static void cancelAppointment() {
        System.out.println("Canceling an appointment...");

    }

    private static void viewScheduledAppointments(String userID) {
        System.out.println("Displaying your scheduled appointments...");
        AppointmentCon.displayScheduledAppointments(userID);

    }

    private static void viewPastAppointmentOutcomes(String userID) {
        // Fetch and display the appointment outcomes for the entered ID
        List<appointmentoutcome> records = AppointmentOutcomeCon.getAppointmentOutcomeForPatient(userID);
    
        if (records.isEmpty()) {
            System.out.println("No outcome records found for Patient ID: " + userID);
        } else {
            System.out.println("\n--- Appointment Outcome Records ---");
            for (appointmentoutcome record : records) {
                System.out.println(record);
            }
        }
    }
    




    //  public static void main(String[] args){

    //      //PatientMenu patientMenu = new PatientMenu();  
    //      List<patient> patientList = PatientCon.getPatientList(); // Fetch the patient list
    //     PatientMenu.showMenu("P1001", patientList); // Pass both arguments
    //  }
    
}