package controller;

import java.util.List;
import java.util.Scanner;

import enum_class.AvailStatus;
import loader.Read;
import loader.Write;

public class AvailabilityCon {
    private String doctorID;
    private String date; //YYYY-MM-DD
    private String timeSlot; //HH:MM
    private AvailStatus status; //either Available or Booked

    private static final String AVAILABILITY_FILE_PATH = "data/Doctor_Availability.csv";

    public AvailabilityCon(String doctorID, String date, String timeSlot, AvailStatus status) {
        this.doctorID = doctorID;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    public static void setAvailability(String doctorId, Scanner sc) {
        System.out.println("Enter the date (YYYY-MM-DD): ");
        String newdate = sc.nextLine();
        System.out.println("Enter the time (HH-MM): ");
        String newtime = sc.nextLine();
        List<AvailabilityCon> availabilityList = Read.loadAvailability(AVAILABILITY_FILE_PATH);
        availabilityList.add(new AvailabilityCon(doctorId, newdate, newtime, AvailStatus.Available));
        Write.saveDoctorAvailability(availabilityList);
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
