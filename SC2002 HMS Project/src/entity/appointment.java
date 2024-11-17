package entity;

import enum_class.AvailStatus;

public class appointment {
    private String Appid;      // Appointment ID
    private String DoctorID;   // Doctor's ID
    //private String PatientID;  // Patient's ID
    private String date;       // Appointment Date
    private String time;       // Appointment Time Slot
    private AvailStatus status;  // Status (Available, Booked, etc.)
    private String details;    // Appointment details (e.g., notes)

    // Constructor
    public appointment(String Appid, String DoctorID, String date, String time, AvailStatus status, String details) {
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        //this.PatientID = PatientID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.details = details;
    }

    // Getters and setters
    public String getAppID() {
        return Appid;
    }

    public void setAppID(String Appid) {
        this.Appid = Appid;
    }

    public String getDoctorId() {
        return DoctorID;
    }

    public void setDoctorId(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    /*public String getPatientId() {
        return PatientID;
    }

    public void setPatientId(String PatientID) {
        this.PatientID = PatientID;
    }*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return time;
    }

    public void setTimeSlot(String time) {
        this.time = time;
    }

    public AvailStatus getStatus() {
        return status;
    }

    public void setStatus(AvailStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
