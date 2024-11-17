package controller;

import java.util.List;
import java.util.Scanner;

import entity.appointment;
import enum_class.AvailStatus;
import loader.Read;
import loader.Write;

public class AvailabilityCon {
    private static final String AVAILABILITY_FILE_PATH = "data/Doctor_Availability.csv";

    private static int id = Read.getLatestAppointmentID("data/Doctor_Availability.csv"); // Availability ID

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
        this.notes = notes; // Default
    }

    public static void setAvailability(String doctorId, Scanner sc) {
        id++;  // Increment the availability ID to avoid conflicts
        System.out.println("Enter the date (DD/MM/YYYY): ");
        String newDate = sc.nextLine();
        System.out.println("Enter the time (HH:MM): ");
        String newTime = sc.nextLine();

        // Load the current availability data from the CSV file
        List<appointment> availabilityList = Read.loadAppointments(AVAILABILITY_FILE_PATH);

        // Add the new availability to the list
        availabilityList.add(new appointment(Integer.toString(id), doctorId, newDate, newTime, AvailStatus.Available, "nil"));

        // Write the updated list back to the CSV file, without overwriting existing data
        Write.saveAppointments(availabilityList);

        System.out.println("Availability added for Doctor ID: " + doctorId);
    }

    public String getDoctorId() {
        return doctorID;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public AvailStatus getStatus() {
        return status;
    }

    public void setStatus(AvailStatus status) {
        this.status = status;
    }
}
