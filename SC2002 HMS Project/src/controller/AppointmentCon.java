package controller;

import java.util.List;
import java.util.Scanner;

import entity.appointment;
import entity.medicalrecord;
import enum_class.DoctorAppointmentStatus;
import enum_class.AvailStatus;
import loader.Read;
import loader.Write;

public class AppointmentCon {
    private String doctorId;
    private String patientId;
    private String date;       // YYYY-MM-DD
    private String timeSlot;   // HH:MM
    private DoctorAppointmentStatus status;     // Pending, Accepted, Declined, Completed (for last part)

    // Path to the CSV file to store appointment requests
    private static final String APPOINTMENT_FILE_PATH = "src/doctor/Appointment_List.csv";
    private static final String OUTCOME_FILE_PATH = "src/doctor/appointment_outcomerecord.csv";

    static List<AppointmentCon> appointmentList = Read.loadAppointments("data/Appointment_List.csv");

    // Constructor
    public AppointmentCon(String doctorId, String patientId, String date, String timeSlot, DoctorAppointmentStatus status) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    
    public static void AppointmentUpdate(String doctorId, Scanner sc){
        System.out.println("\nWould you like to accept or decline any appointments? (A/D): ");
        String choice = sc.nextLine();

        //if dont want to update anything
        if(!choice.equals("A") && !choice.equals("D")){
            System.out.println("\nNo Updates For Pending Appointments");
            return;
        }
        do{
            if(choice.equals("A")){
                System.out.println("--- Accepting Appointment ---");
                System.out.println("Enter the patient ID: ");
                String patientID = sc.nextLine();
                System.out.println("Enter the date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                System.out.println("Enter the time (HH-MM): ");
                String time = sc.nextLine();
                acceptAppointment(doctorId, patientID, date, time);
            };
            if(choice.equals("D")){
                System.out.println("--- Accepting Appointment ---");
                System.out.println("Enter the patient ID: ");
                String patientIDB = sc.nextLine();
                System.out.println("Enter the date (YYYY-MM-DD): ");
                String dateB = sc.nextLine();
                System.out.println("Enter the time (HH-MM): ");
                String timeB = sc.nextLine();
                declineAppointment(doctorId, patientIDB, dateB, timeB);
            };
            System.out.println("\nWould you like to accept or decline any appointments? (A/D): ");
            choice = sc.nextLine();
        } while(choice.equals("A") || choice.equals("D"));
        System.out.println("\n--- Appointment status has been updated ---");
    }

    //for appointment outcome record (option 7)
    public static void AppointmentOutcomeRecord(String doctorId, Scanner sc){
        displayUpcomingAppointments(doctorId);
        System.out.println("\n--- Recording Appointment Outcome ---");
        System.out.println("Enter the patientID: ");
        String aptID = sc.nextLine();
        System.out.println("Enter the date of appointment: ");
        String aptDate = sc.nextLine();
        System.out.println("Enter the type of service provided: ");
        String aptType = sc.nextLine();
        System.out.println("Enter the prescribed medications: ");
        String aptMedications = sc.nextLine();
        System.out.println("Enter the status: ");
        String aptStatus = sc.nextLine();
        DoctorAppointmentStatus status = DoctorAppointmentStatus.valueOf(aptStatus);

        //upload into csv file
        Write.saveAppointmentOutcomeRecord(doctorId, aptDate, aptID, aptType, aptMedications, aptStatus);
    }

    public static void acceptAppointment(String doctorId, String patientId, String date, String timeSlot) {
        List<AppointmentCon> appointmentList = Read.loadAppointments(APPOINTMENT_FILE_PATH);
        boolean found = false;

        for (AppointmentCon appointment : appointmentList) {
            if (appointment.this.doctorId().equals(doctorId) &&
                appointment.getPatientId().equals(patientId) &&
                appointment.getDate().equals(date) &&
                appointment.getTimeSlot().equals(timeSlot) &&
                appointment.getStatus().equals("Pending")) {
                
                appointment.setStatus("Accepted");
                found = true;
                break;
            }
        }

        if (found) {
            saveAppointments(appointmentList);
            System.out.println("Appointment accepted for Patient ID: " + patientId);
        } else {
            System.out.println("Pending appointment not found for acceptance.");
        }
    }

    public static void declineAppointment(String doctorId, String patientId, String date, String timeSlot) {
        boolean found = false;

        for (AppointmentCon appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) &&
                appointment.getPatientId().equals(patientId) &&
                appointment.getDate().equals(date) &&
                appointment.getTimeSlot().equals(timeSlot) &&
                appointment.getStatus().equals("Pending")) {
                
                appointment.setStatus(DoctorAppointmentStatus.Declined);
                found = true;
                break;
            }
        }

        if (found) {
            Write.saveAppointments(appointmentList);
            System.out.println("Appointment declined for Patient ID: " + patientId);
        } else {
            System.out.println("Pending appointment not found for declining.");
        }
    }

    public static void displayPendingAppointments(String doctorId) {
        System.out.println("--- Pending Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Pending")) {
                System.out.println("Patient ID: " + appointment.getPatientId() + ", Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot());
            }
        }
        System.out.println("------------------------------------");
    }

    public static void displayUpcomingAppointments(String doctorId) {
        System.out.println("--- Upcoming Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Accepted")) {
                System.out.println("Patient ID: " + appointment.getPatientId() + ", Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot());
            }
        }
        System.out.println("------------------------------------");
    }
}
