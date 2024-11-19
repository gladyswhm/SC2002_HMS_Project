package entity;

import enum_class.DoctorAppointmentStatus;

/**
 * Represents the availability of a doctor for appointments.
 * Contains details such as appointment ID, doctor ID, patient ID, date, time slot, and status.
 */
public class doctoravailability {

    private static String Appid;                     // Appointment ID
    private static String DoctorID;                  // Doctor's ID
    private static String PatientID;                 // Patient's ID
    private static String date;                      // Appointment date
    private static String time;                      // Appointment time slot
    private static DoctorAppointmentStatus status;   // Status of the appointment (e.g., Available, Booked)

    /**
     * Constructs a doctor availability record with the specified details.
     *
     * @param Appid      the appointment ID
     * @param DoctorID   the doctor's ID
     * @param PatientID  the patient's ID
     * @param date       the appointment date
     * @param time       the appointment time slot
     * @param status     the status of the appointment
     */
    public doctoravailability(String Appid, String DoctorID, String PatientID, String date, String time, DoctorAppointmentStatus status) {
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    /**
     * Gets the appointment ID.
     *
     * @return the appointment ID
     */
    public static String geteAppID() {
        return Appid;
    }

    /**
     * Gets the doctor's ID.
     *
     * @return the doctor's ID
     */
    public static String getDoctorId() {
        return DoctorID;
    }

    /**
     * Gets the patient's ID.
     *
     * @return the patient's ID
     */
    public static String getPatientId() {
        return PatientID;
    }

    /**
     * Gets the appointment date.
     *
     * @return the appointment date
     */
    public static String getDate() {
        return date;
    }

    /**
     * Gets the appointment time slot.
     *
     * @return the appointment time slot
     */
    public static String getTimeSlot() {
        return time;
    }

    /**
     * Gets the status of the appointment.
     *
     * @return the appointment status
     */
    public static DoctorAppointmentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status the new appointment status
     * @return the updated appointment status
     */
    public DoctorAppointmentStatus setStatus(DoctorAppointmentStatus status) {
        this.status = status;
        return this.status;
    }
}
