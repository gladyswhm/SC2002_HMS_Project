/**
 * This class represents the Doctor Menu where a doctor can manage their medical records,
 * schedule, availability, appointments, and other functions relevant to their role.
 */
package screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import account_manager.ChangePassword;
import controller.AvailabilityCon;
import controller.AppointmentCon;
import controller.AppointmentOutcomeCon;
import controller.DoctorCon;
import entity.medicalrecord;
import entity.staff;

public class DoctorMenu implements UserMenu{
    private static List<medicalrecord> medicalRecords = new ArrayList<>();

    /**
     * Displays the main menu for the doctor with options to view and manage patient records,
     * schedule, availability, and appointments, as well as change the password or log out.
     *
     * @param doctorID The user ID of the logged-in doctor.
     * @param lines The list of staff members (for password change functionality).
     */
    public void showMenu(String doctorID, List<staff> lines) {
        Scanner sc = new Scanner(System.in);
        int option;
        do{
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Add New Medical Record");
            System.out.println("3. Update Patient Medical Records");
            System.out.println("4. View Personal Schedule");
            System.out.println("5. Set Availability for Appointments");
            System.out.println("6. Accept or Decline Appointment Requests");
            System.out.println("7. View Upcoming Appointments");
            System.out.println("8. Record Appointment Outcome");
            System.out.println("9. Change Password");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
    
            option = sc.nextInt();
            sc.nextLine();

            switch(option){
                case 1:
                    System.out.println("\n--- Viewing Patient Medical Records ---");
                    DoctorCon.displayMedicalRecords();
                    break;
                case 2:
                    DoctorCon.addPatientMedicalRecords(doctorID, medicalRecords, sc);
                    break;
                case 3:
                    System.out.println("\n--- Update Patient Medical Record ---");
                    DoctorCon.displayAvailablePatientIds();
                    DoctorCon.updatePatientMedicalRecords(medicalRecords, sc, doctorID);
                    break;
                case 4:
                    System.out.println("\n--- Viewing Personal Schedule ---");
                    AppointmentCon.displayAllDoctor(doctorID);
                    break;
                case 5:
                    System.out.println("\n--- Set Availability ---");
                    AvailabilityCon.setAvailability(doctorID, sc);
                    break;
                case 6:
                    System.out.println("\n--- Showing Appointment Requests ---");
                    AppointmentCon.displayPendingAppointments(doctorID);
                    AppointmentCon.AppointmentUpdate(doctorID, sc); //accept and decline appointment request
                    break;
                case 7:
                    AppointmentCon.displayUpcomingAppointments(doctorID);//view upcoming appointments
                    break;
                case 8:
                    AppointmentOutcomeCon.AppointmentOutcomeRecord(doctorID, sc);
                    break;
                case 9:
                    // Change Password
                    ChangePassword.changeStaffPassword(doctorID, lines);
                    break;
                case 10:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        }while(option!=10);
    }
}