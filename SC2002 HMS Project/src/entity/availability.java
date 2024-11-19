package entity;

import enum_class.AvailStatus;

/**
 * Represents the availability of a doctor for appointments.
 * Includes details such as appointment ID, doctor ID, date, time slot, 
 * availability status, and additional details or notes.
 */
public class availability {

    private String Appid;       // Appointment ID
    private String DoctorID;    // Doctor's ID
    private String date;        // Appointment Date
    private String time;        // Appointment Time Slot
    private AvailStatus status; // Availability status (e.g., Available, Booked)
    private String details;     // Additional appointment details

    /**
     * Constructs an availability record with the specified details.
     *
     * @param Appid   the appointment ID
     * @param DoctorID the doctor's ID
     * @param date    the date of the appointment
     * @param time    the time slot of the appointment
     * @param status  the availability status
     * @param details additional details about the appointment
     */
    public availability(String Appid, String DoctorID, String date, String time, AvailStatus status, String details) {
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.details = details;
    }

    /**
     * Gets the doctor's ID.
     *
     * @return the doctor's ID
     */
    public String getDoctorId() {
        return DoctorID;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return the appointment date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time slot of the appointment.
     *
     * @return the appointment time slot
     */
    public String getTimeSlot() {
        return time;
    }

    /**
     * Gets the availability status of the appointment.
     *
     * @return the availability status
     */
    public AvailStatus getStatus() {
        return status;
    }

    /**
     * Sets the availability status of the appointment.
     *
     * @param status the new availability status
     */
    public void setStatus(AvailStatus status) {
        this.status = status;
    }
}
