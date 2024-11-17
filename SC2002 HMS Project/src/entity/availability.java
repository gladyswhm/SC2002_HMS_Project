package entity;
import enum_class.AvailStatus;


public class availability {
    
    private String Appid;      // Appointment ID
    private String DoctorID;   // Doctor's ID
    //private String PatientID;  // Patient's ID
    private String date;       // Appointment Date
    private String time;       // Appointment Time Slot
    private AvailStatus status;  // Status (Available, Booked, etc.)
    private String details;    // Appointment details (e.g., notes)

    // Constructor
    public availability(String Appid, String DoctorID, String date, String time, AvailStatus status, String details) {
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        //this.PatientID = PatientID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.details = details;
    }

    public String getDoctorId() {
        return DoctorID;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return time;
    }

    public AvailStatus getStatus() {
        return status;
    }

    public void setStatus(AvailStatus status) {
        this.status = status;
    }
}