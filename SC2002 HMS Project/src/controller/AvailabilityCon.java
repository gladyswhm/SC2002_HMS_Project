package controller;

import java.util.List;
import java.util.Scanner;

import entity.appointment;
import enum_class.AvailStatus;
import loader.Read;
import loader.Write;

public class AvailabilityCon {
    private static final String AVAILABILITY_FILE_PATH = "data/Doctor_Availability.csv";

    private int id;
    private String doctorID;
    private String date; // YYYY-MM-DD
    private String timeSlot; // HH:MM
    private AvailStatus status; // either Available or Booked
    private String notes;


    public AvailabilityCon(int id, String doctorID, String date, String timeSlot, AvailStatus status, String notes) {
        this.id = id;
        this.doctorID = doctorID;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = status;
        this.notes = notes; //default
    }

    public static void setAvailability(String doctorId, Scanner sc) {
        int id = Read.getOutcomeID("data/Doctor_Availability.csv"); // Availability ID
        id++;  
        System.out.println("Enter the date (DD/MM/YYYY): ");
        String newDate = sc.nextLine();
        System.out.println("Enter the time (HH:MM): ");
        String newTime = sc.nextLine();

        List<appointment> availabilityList = Read.loadAppointments(AVAILABILITY_FILE_PATH);

        availabilityList.add(new appointment(Integer.toString(id), doctorId, newDate, newTime, AvailStatus.Available, "nil"));

        Write.saveAppointments(availabilityList);

        System.out.println("Availability added for Doctor ID: " + doctorId);
    }

}
