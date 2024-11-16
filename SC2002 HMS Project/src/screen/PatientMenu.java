package screen;

import java.util.List;
import java.util.Scanner;
import controller.PatientCon;
import entity.patient;

public class PatientMenu {

    public void showMenu() { 
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    viewMedicalRecord(); 
                    break;
                case 2:
                    updatePersonalInfo();
                    break;
                case 3:
                    viewAvailableAppointments();
                    break;
                case 4:
                    scheduleAppointment();
                    break;
                case 5:
                    rescheduleAppointment();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                case 7:
                    viewScheduledAppointments();
                    break;
                case 8:
                    viewPastAppointmentOutcomes();
                    break;
                case 9:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 9);
    }


    private void viewMedicalRecord() {
        System.out.println("Displaying medical record...");

    }

    private void updatePersonalInfo() {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for Patient ID to identify the correct patient
        System.out.print("Enter your Patient ID (case sensative): ");
        String patientId = scanner.nextLine();

        // Fetch the list of patients and find the matching patient
        List<patient> patients = PatientCon.getPatientList();
        patient patientToUpdate = null;
        for (patient p : patients) {
            if (p.getUserID().equals(patientId)) {
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


    private void viewAvailableAppointments() {
        System.out.println("Displaying available appointment slots...");

    }

    private void scheduleAppointment() {
        System.out.println("Scheduling an appointment...");

    }

    private void rescheduleAppointment() {
        System.out.println("Rescheduling an appointment...");
    }

    private void cancelAppointment() {
        System.out.println("Canceling an appointment...");

    }

    private void viewScheduledAppointments() {
        System.out.println("Displaying scheduled appointments...");

    }

    private void viewPastAppointmentOutcomes() {
        System.out.println("Displaying past appointment outcomes...");

    }



    public static void main(String[] args){

        PatientMenu patientMenu = new PatientMenu();  
        patientMenu.showMenu();  
    }
}