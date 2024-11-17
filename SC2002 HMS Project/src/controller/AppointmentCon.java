package controller;

import java.util.List;
import java.util.Scanner;

import entity.appointment;
import entity.medicalrecord;
import entity.medicine;
import enum_class.AvailStatus;
import loader.Read;
import loader.Write;

public class AppointmentCon {
    private String Appid;
    private String doctorId;
    private String patientId;
    private String date;       // YYYY-MM-DD
    private String timeSlot;   // HH:MM
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

    public static void displayPendingAppointments(String doctorId) {
        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        System.out.println("--- Pending Appointments for Doctor ID: " + doctorId + " ---");

        for (appointment appointment : appointmentList) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus() == AvailStatus.Pending) {
                System.out.println("Appointment ID: " + appointment.getAppID() + "Date: " + appointment.getDate() + ", Time Slot: " + appointment.getTimeSlot());
            }
        }
        System.out.println("------------------------------------");
    }

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
    public static void displayAppointmentList() {
        List<appointment> appointmentList = Read.loadAppointments("data/Doctor_Availability.csv");
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
    
        int idWidth = 15;
        int doctorIdWidth = 10;
        int dateWidth = 15;
        int timeSlotWidth = 15;
        int statusWidth = 10;
        int detailsWidth = 30;

        System.out.println(String.format("%-" + idWidth + "s%-" + doctorIdWidth + "s%-" + dateWidth + "s%-" + timeSlotWidth + "s%-" + statusWidth + "s%-" + detailsWidth + "s",
                "Appointment ID", "Doctor ID", "Date", "Time Slot", "Status", "Details"));
        System.out.println("----------------------------------------------------------------------------");
 
        for (appointment app : appointmentList) {
            System.out.println(String.format("%-" + idWidth + "s%-" + doctorIdWidth + "s%-" + dateWidth + "s%-" + timeSlotWidth + "s%-" + statusWidth + "s%-" + detailsWidth + "s",
                    app.getAppID(), app.getDoctorId(), app.getDate(), app.getTimeSlot(), app.getStatus(), app.getDetails()));
        }
        System.out.println("--------------------------");
    }
    

   
}
