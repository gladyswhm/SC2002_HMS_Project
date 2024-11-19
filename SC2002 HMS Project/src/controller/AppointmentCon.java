package controller;

import java.util.List;
import java.util.Scanner;

import entity.appointment;
import entity.medicalrecord;
import entity.medicine;
import enum_class.AvailStatus;
import loader.Read;
import loader.Write;


 /**
 * The class Appointment con
 */ 
public class AppointmentCon {
    private String Appid;
    private String doctorId;
    private String patientId;
    private String date;       // YYYY-MM-DD
    private String timeSlot;   // HH:MM


/** 
 *
 * It is a constructor. 
 *
 * @param part  the part.  It is for 
 * @throws 
    
    // Constructor
    public AppointmentCon(String Appid
 * @throws  String doctorId
 * @throws  String patientId
 * @throws  String date
 * @throws  String timeSlot
 * @throws  AvailStatus status
 */
    private AvailStatus status;     // Pending, Accepted, Declined, Completed (for last part) 
    
    // Constructor
    public AppointmentCon(String Appid, String doctorId, String patientId, String date, String timeSlot, AvailStatus status) {

        this.Appid=Appid;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    

/** 
 *
 * Appointment update
 *
 * @param doctorId  the doctor identifier. 
 * @param sc  the sc. 
 */
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
                System.out.println("Enter the Appointment ID: ");
                String appID = sc.nextLine();
                
                acceptAppointment(doctorId, appID);
            };
            if(choice.equals("D")){
                System.out.println("--- Declining Appointment ---");
                System.out.println("Enter the Appointment ID: ");
                String appID = sc.nextLine();
                declineAppointment(doctorId, appID);
            };
            System.out.println("\nWould you like to accept or decline any appointments? (A/D): ");
            choice = sc.nextLine();
        } while(choice.equals("A") || choice.equals("D"));
        System.out.println("\n--- Appointment status has been updated ---");
    }



/** 
 *
 * Accept appointment
 *
 * @param doctorId  the doctor identifier. 
 * @param appID  the application identifier. 
 */
    public static void acceptAppointment(String doctorId, String appID) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");

        boolean found = false;

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) &&
                appointment.getAppID().equals(appID) &&
                appointment.getStatus() == AvailStatus.Pending) {
                
                appointment.setStatus(AvailStatus.Confirmed);
                found = true;
                break;
            }
        }

        if (found) {
            Write.saveAppointments(appointmentList);
            System.out.println("Appointment accepted for " + appID);
        } else {
            System.out.println("Pending appointment not found for acceptance.");
        }
    }


/** 
 *
 * Decline appointment
 *
 * @param doctorId  the doctor identifier. 
 * @param appID  the application identifier. 
 */
    public static void declineAppointment(String doctorId, String appID) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        boolean found = false;

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) &&
                appointment.getAppID().equals(appID) &&
                appointment.getStatus() == AvailStatus.Pending) {
                
                appointment.setStatus(AvailStatus.Available);
                found = true;
                break;
            }
        }

        if (found) {
            Write.saveAppointments(appointmentList);
            System.out.println("Appointment declined for " + appID);
        } else {
            System.out.println("Pending appointment not found for declining.");
        }
    }


/** 
 *
 * Display available appointments
 *
 * @param doctorId  the doctor identifier. 
 */
    public static void displayAvailableAppointments(String doctorId) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        System.out.println("--- Available Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus() == AvailStatus.Available) {
                System.out.println("Appointment ID: " + appointment.getAppID() + " Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot());
            }
        }
        System.out.println("------------------------------------");
    }


/** 
 *
 * Display pending appointments
 *
 * @param doctorId  the doctor identifier. 
 */
    public static void displayPendingAppointments(String doctorId) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        System.out.println("--- Pending Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus() == AvailStatus.Pending) {
                System.out.println("Appointment ID: " + appointment.getAppID() + " Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot());
            }
        }
        System.out.println("------------------------------------");
    }


/** 
 *
 * Display upcoming appointments
 *
 * @param doctorId  the doctor identifier. 
 */
    public static void displayUpcomingAppointments(String doctorId) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        System.out.println("--- Upcoming Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus() == AvailStatus.Confirmed) {
                System.out.println("Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot() + ", Patient ID: " + appointment.getDetails());
            }
        }
        System.out.println("------------------------------------");
    }


/** 
 *
 * Display available slots
 *
 * @param doctorId  the doctor identifier. 
 */
    public static void displayAvailableSlots(String doctorId) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        System.out.println("--- Pending Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus() == AvailStatus.Pending) {
                System.out.println("Appointment ID: " + appointment.getAppID() + " Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot());
            }
        }
        System.out.println("------------------------------------");
    }


/** 
 *
 * Display all doctor
 *
 * @param doctorId  the doctor identifier. 
 */
    public static void displayAllDoctor(String doctorId) { 

    List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
    System.out.println("--- Appointments for Doctor ID: " + doctorId + " ---");

    for (appointment appointment : appointmentList) {
        if (appointment.getDoctorId().equals(doctorId)) {
            System.out.println("Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot() + ", Patient ID: " + appointment.getDetails() + ", Status: " + appointment.getStatus());
        }
    }
    System.out.println("------------------------------------");
    }

    //admin display appointment list

/** 
 *
 * Display appointment list
 *
 */
    public static void displayAppointmentList() { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
    
        for (appointment app : appointmentList) {
            System.out.println("Appointment ID: " + app.getAppID() + ", Doctor ID: " + app.getDoctorId() + ", Date: " + app.getDate() + ", Time Slot: " + app.getTimeSlot() + ", Status: " + app.getStatus() + ", Details: " + app.getDetails());
        }
    }
    
    
    

    //patient to print available slots

/** 
 *
 * Display available appointment list
 *
 */
    public static void displayAvailableAppointmentList() { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        if (appointmentList.isEmpty()) {
            System.out.println("No available appointments found.");
            return;
        }
    
        boolean foundAvailable = false; 
    
        for (appointment app : appointmentList) {
            if (app.getStatus() == AvailStatus.Available) { 
                System.out.println("Appointment ID: " + app.getAppID() + ", Doctor ID: " + app.getDoctorId() + 
                                   ", Date: " + app.getDate() + ", Time Slot: " + app.getTimeSlot() + 
                                   ", Status: " + app.getStatus());
                foundAvailable = true; 
            }
        }
    
        if (!foundAvailable) {
            System.out.println("No available appointments.");
        }
    }
    
    


    //patient view scheduled appointment

/** 
 *
 * Display scheduled appointments
 *
 * @param userID  the user identifier. 
 */
    public static void displayScheduledAppointments(String userID) { 

        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
    
        boolean foundScheduled = false; 
    
        for (appointment app : appointmentList) {
            if (app.getDetails().contains(userID) && 
                (app.getStatus() == AvailStatus.Confirmed || app.getStatus() == AvailStatus.Pending)) {
                System.out.println("Appointment ID: " + app.getAppID() + 
                                   ", Doctor ID: " + app.getDoctorId() + 
                                   ", Date: " + app.getDate() + 
                                   ", Time Slot: " + app.getTimeSlot() + 
                                   ", Status: " + app.getStatus());
                foundScheduled = true; 
            }
        }
    
        if (!foundScheduled) {
            System.out.println("No scheduled appointments for user ID: " + userID);
        }
    }
    
    
    
    
    

   
}
