package controller;

import entity.appointment;
import entity.medicalrecord;
import entity.patient;
import enum_class.AvailStatus;

import java.util.List;
import loader.Read;
import loader.Write;
import java.util.Scanner;


 /**
 * The class Patient con
 */ 
public class PatientCon {
    static List<patient> patientList = Read.readPatientList("data/Patient_List.csv");


/** 
 *
 * Gets the patient list
 *
 * @return the patient list
 */
    public static List<patient> getPatientList() { 

        return patientList;
    }

    static List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");
    

/** 
 *
 * Update contact info
 *
 * @param patients  the patients. 
 * @param option  the option. 
 */
    public static void updateContactInfo(patient patients, int option) { 

        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        if (option == 1) {  // Update email
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();  
            patients.setEmail(newEmail);  
        } else if (option == 2) {  // Update phone number
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();  
            patients.setPhonenumber(newPhoneNumber);  
        } else if (option == 3) {  // Update both email and phone number
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();  
            patients.setEmail(newEmail);  

            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();  
            patients.setPhonenumber(newPhoneNumber);  
        }
        Write.savePatientListToCSV(patientList);
    }


/** 
 *
 * View medical record
 *
 * @param patientID  the patient identifier. 
 */
    public static void viewMedicalRecord(String patientID){ 

        boolean found = false;

    // Iterate through the records to find the one matching the patient's ID
        for (medicalrecord record : records) {

            if (record.getPatientId().equalsIgnoreCase(patientID)) {
                System.out.print("Appointment ID: " + record.getMRID());
                System.out.print(", Diagnosis: " + record.getDiagnosis());
                System.out.print(", Treatment Plan: " + record.getTreatmentPlan() + "\n");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No medical record found for Patient ID: " + patientID);
        }
    }

    //option 4

/** 
 *
 * Schedule appointment
 *
 * @param patientID  the patient identifier. 
 */
    public static void scheduleAppointment(String patientID){ 

        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        boolean found = false;

        System.out.print("Enter doctor you want to book: ");
        String doctorID = scanner.nextLine();
        AppointmentCon.displayAvailableAppointments(doctorID);

        System.out.print("Enter Appointment ID you want to book: ");
        String aptID = scanner.nextLine();

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorID) &&
                appointment.getAppID().equals(aptID)) {
                
                appointment.setStatus(AvailStatus.Pending);
                appointment.setDetails(patientID);
                found = true;
                break;
            }
        }
        if (found) {
            Write.saveAppointments(appointmentList);
            System.out.println("Pending appointment (awaiting doctors' approval): " + aptID);
        } else {
            System.out.println("Pending appointment not found for acceptance.");
        }
    }

    //option 5: reschedule an appointment

/** 
 *
 * Reschedule appointment
 *
 * @param patientID  the patient identifier. 
 */
    public static void rescheduleAppointment(String patientID){ 

        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        boolean found = false;

        AppointmentCon.displayScheduledAppointments(patientID);

        System.out.println("------------------------------------------------------");
        System.out.print("Enter Appointment ID you want to reschedule: ");
        String id = scanner.nextLine().trim();

        for (appointment appointment : appointmentList) {
            if (appointment.getAppID().trim().equals(id)) {

                AppointmentCon.displayAvailableAppointmentList();
                System.out.print("Enter new Appointment ID: ");
                String NEWaptID = scanner.nextLine();
                for (appointment ap : appointmentList) {
                    if (ap.getAppID().trim().equals(NEWaptID)) {
                        
                        ap.setStatus(AvailStatus.Pending);
                        ap.setDetails(patientID);
                        found = true;
                        break;
                    }
                }
                appointment.setStatus(AvailStatus.Available);
                appointment.setDetails("nil");
            }
        }
        if (found) {
            Write.saveAppointments(appointmentList);
            System.out.println("Appointment rescheduled successfully");
        } else {
            System.out.println("Not found.");
        }
    }

    //option 6: cancel appointment

/** 
 *
 * Cancel appointment
 *
 * @param patientID  the patient identifier. 
 */
    public static void cancelAppointment(String patientID){ 

        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        boolean found = false;

        AppointmentCon.displayScheduledAppointments(patientID);

        System.out.print("Enter Appointment ID you want to cancel: ");
        String aptID = scanner.nextLine();

        for (appointment appointment : appointmentList) {
            if (appointment.getAppID().equals(aptID)) {
                
                appointment.setStatus(AvailStatus.Available);
                appointment.setDetails("nil");
                found = true;
                break;
            }
        }
        if (found) {
            Write.saveAppointments(appointmentList);
            System.out.println("Appointment cancelled for " + aptID);
        } else {
            System.out.println("Pending appointment not found for acceptance.");
        }
    }
}
