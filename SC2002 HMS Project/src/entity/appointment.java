package entity;

import enum_class.AvailStatus;

/**
 * Represents an appointment in the system.
 * Each appointment has details such as an ID, doctor's ID, date, time slot, status, and additional notes.
 */
public class appointment {
    private String Appid;      // Appointment ID
    private String DoctorID;   // Doctor's ID
    //private String PatientID;  // Patient's ID (commented out)
    private String date;       // Appointment Date
    private String time;       // Appointment Time Slot
    private AvailStatus status;  // Status (Available, Booked, etc.)
    private String details;    // Appointment details (e.g., notes)

    /**
     * Constructs a new Appointment with the given details.
     *
     * @param Appid    the unique identifier for the appointment
     * @param DoctorID the ID of the doctor assigned to the appointment
     * @param date     the date of the appointment
     * @param time     the time slot of the appointment
     * @param status   the status of the appointment (e.g., Available, Booked)
     * @param details  additional details or notes about the appointment
     */
    public appointment(String Appid, String DoctorID, String date, String time, AvailStatus status, String details) {
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        //this.PatientID = PatientID; // Currently not used
        this.date = date;
        this.time = time;
        this.status = status;
        this.details = details;
    }

    /**
     * Gets the appointment ID.
     *
     * @return the appointment ID
     */
    public String getAppID() {
        return Appid;
    }

    /**
     * Sets the appointment ID.
     *
     * @param Appid the new appointment ID
     */
    public void setAppID(String Appid) {
        this.Appid = Appid;
    }

    /**
     * Gets the doctor's ID for the appointment.
     *
     * @return the doctor's ID
     */
    public String getDoctorId() {
        return DoctorID;
    }

    /**
     * Sets the doctor's ID for the appointment.
     *
     * @param DoctorID the new doctor's ID
     */
    public void setDoctorId(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    /*
     * Gets the patient's ID for the appointment.
     *
     * @return the patient's ID
     */
    /*public String getPatientId() {
        return PatientID;
    }

    /**
     * Sets the patient's ID for the appointment.
     *
     * @param PatientID the new patient's ID
     */
    /*public void setPatientId(String PatientID) {
        this.PatientID = PatientID;
    }*/

    /**
     * Gets the date of the appointment.
     *
     * @return the date of the appointment
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param date the new date of the appointment
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time slot of the appointment.
     *
     * @return the time slot of the appointment
     */
    public String getTimeSlot() {
        return time;
    }

    /**
     * Sets the time slot of the appointment.
     *
     * @param time the new time slot of the appointment
     */
    public void setTimeSlot(String time) {
        this.time = time;
    }

    /**
     * Gets the status of the appointment.
     *
     * @return the appointment status
     */
    public AvailStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status the new status of the appointment
     */
    public void setStatus(AvailStatus status) {
        this.status = status;
    }

    /**
     * Gets the additional details or notes for the appointment.
     *
     * @return the appointment details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the additional details or notes for the appointment.
     *
     * @param details the new details or notes for the appointment
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
