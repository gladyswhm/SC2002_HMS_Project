package entity;

/**
 * Represents the outcome of an appointment, including details such as patient, doctor, services provided, 
 * medication prescribed, and payment status.
 */
public class appointmentoutcome {
    private String Appid;        // Appointment ID
    private String patientId;    // Patient's ID
    private String doctorId;     // Doctor's ID
    private String date;         // Appointment Date (YYYY-MM-DD)
    private String services;     // Services provided during the appointment
    private String medication;   // Medication prescribed
    private String amount;       // Total amount charged
    private String status;       // Status of the appointment (e.g., Completed, Cancelled)
    private String payment;      // Payment status (e.g., Paid, Pending)

    /**
     * Constructs an appointment outcome with the specified details.
     *
     * @param Appid      the appointment ID
     * @param patientId  the patient's ID
     * @param doctorId   the doctor's ID
     * @param date       the date of the appointment
     * @param services   the services provided during the appointment
     * @param medication the medication prescribed during the appointment
     * @param amount     the total amount charged for the appointment
     * @param status     the status of the appointment
     * @param payment    the payment status of the appointment
     */
    public appointmentoutcome(String Appid, String patientId, String doctorId, String date, String services, String medication, String amount, String status, String payment) {
        this.Appid = Appid;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.services = services;
        this.medication = medication;
        this.amount = amount;
        this.status = status;
        this.payment = payment;
    }

    /**
     * Gets the appointment ID.
     *
     * @return the appointment ID
     */
    public String getAppId() {
        return Appid;
    }

    /**
     * Sets the appointment ID.
     *
     * @param Appid the new appointment ID
     */
    public void setAppId(String Appid) {
        this.Appid = Appid;
    }

    /**
     * Gets the patient ID.
     *
     * @return the patient ID
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientId the new patient ID
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the doctor ID.
     *
     * @return the doctor ID
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the doctor ID.
     *
     * @param doctorId the new doctor ID
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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
     * Sets the date of the appointment.
     *
     * @param date the new appointment date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the services provided during the appointment.
     *
     * @return the services provided
     */
    public String getServices() {
        return services;
    }

    /**
     * Sets the services provided during the appointment.
     *
     * @param services the new services provided
     */
    public void setServices(String services) {
        this.services = services;
    }

    /**
     * Gets the medication prescribed during the appointment.
     *
     * @return the medication prescribed
     */
    public String getMedication() {
        return medication;
    }

    /**
     * Sets the medication prescribed during the appointment.
     *
     * @param medication the new medication prescribed
     */
    public void setMedication(String medication) {
        this.medication = medication;
    }

    /**
     * Gets the total amount charged for the appointment.
     *
     * @return the total amount charged
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the total amount charged for the appointment.
     *
     * @param amount the new total amount charged
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Gets the status of the appointment.
     *
     * @return the appointment status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status the new appointment status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the payment status of the appointment.
     *
     * @return the payment status
     */
    public String getPayment() {
        return payment;
    }

    /**
     * Sets the payment status of the appointment.
     *
     * @param payment the new payment status
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * Returns a string representation of the appointment outcome.
     *
     * @return a formatted string containing appointment details
     */
    @Override
    public String toString() {
        return String.format("Appointment ID: %s, Patient ID: %s, Doctor ID: %s, Date: %s, Services: %s, Medication: %s, Quantity: %s, Status: %s, Payment Status: %s",
                Appid, patientId, doctorId, date, services, medication, amount, status, payment);
    }
}
