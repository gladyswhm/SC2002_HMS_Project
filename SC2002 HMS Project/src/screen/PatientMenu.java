package screen;

import java.util.List;
import java.util.Scanner;

import account_manager.ChangePassword;
import controller.AppointmentCon;
import controller.AppointmentOutcomeCon;
import controller.PatientCon;
import entity.appointmentoutcome;
import entity.patient;


 /**
 * The PatientMenu class allows patients to manage their personal information,
 * appointments, medical records, and other functionalities specific to their role.
 */
public class PatientMenu {

    static Scanner scanner = new Scanner(System.in);

    /**
     * Displays the main menu for the patient with options to view and manage personal information,
     * medical records, appointments, and other patient-related functions.
     *
     * @param userID The user identifier of the logged-in patient.
     * @param patientList The list of all patients in the system.
     */
    public static void showMenu(String userID, List<patient> patientList) {  
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
            System.out.println("9. Make Payment");
            System.out.println("10. Change Password");
            System.out.println("11. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // View medical record
                    viewPatientDetails(userID);
                    PatientCon.viewMedicalRecord(userID); 
                    break;
                case 2:
                    // Update personal information
                    updatePersonalInfo(userID);
                    break;
                case 3:
                    // View available appointment slots
                    viewAvailableAppointments();
                    break;
                case 4:
                    // Schedule an appointment
                    PatientCon.scheduleAppointment(userID);
                    break;
                case 5:
                    // Reschedule an appointment
                    PatientCon.rescheduleAppointment(userID);
                    break;
                case 6:
                    // Cancel an appointment
                    PatientCon.cancelAppointment(userID);
                    break;
                case 7:
                    // View scheduled appointments
                    viewScheduledAppointments(userID);
                    break;
                case 8:
                    // View past appointment outcome records
                    viewPastAppointmentOutcomes(userID);
                    break;
                case 9:
                    // Make payment for appointments
                    AppointmentOutcomeCon.makePayment(userID);
                    break;
                case 10:
                    // Change patient password
                    ChangePassword.changePatientPassword(userID, patientList);
                    break;
                case 11:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 11);
    }

    /**
     * Displays the personal details of the patient.
     *
     * @param userID The user identifier of the patient.
     */
    private static void viewPatientDetails(String userID) { 
        List<patient> patients = PatientCon.getPatientList();
        patient patient = null;
        for (patient p : patients) {
            if (p.getUserID().equals(userID)) {
                System.out.println("Name: " + p.getName());
                System.out.println("Phone Number: " + p.getPhonenumber());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Blood Type: " + p.getBlood());
                System.out.println("Date of Birth: " + p.getDobAsFormattedString());
                System.out.println("Gender: " + p.getGender());
                patient = p;
                break;
            }
        }
    }

    /**
     * Allows the patient to update their personal information such as email or phone number, or both.
     *
     * @param userID The user identifier of the patient.
     */
    private static void updatePersonalInfo(String userID) { 
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

    /**
     * Displays the available appointment slots.
     */
    private static void viewAvailableAppointments() { 
        System.out.println("Displaying available appointment slots...");
        AppointmentCon.displayAvailableAppointmentList();
    }

    /**
     * Schedules a new appointment.
     */
    private static void scheduleAppointment() { 
        System.out.println("Scheduling an appointment...");
    }

    /**
     * Reschedules an existing appointment.
     */
    private static void rescheduleAppointment() { 
        System.out.println("Rescheduling an appointment...");
    }

    /**
     * Cancels an existing appointment.
     */
    private static void cancelAppointment() { 
        System.out.println("Canceling an appointment...");
    }

    /**
     * Displays the scheduled appointments for the patient.
     *
     * @param userID The user identifier of the patient.
     */
    private static void viewScheduledAppointments(String userID) { 
        System.out.println("Displaying your scheduled appointments...");
        AppointmentCon.displayScheduledAppointments(userID);
    }

    /**
     * Displays the past appointment outcome records for the patient.
     *
     * @param userID The user identifier of the patient.
     */
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
}