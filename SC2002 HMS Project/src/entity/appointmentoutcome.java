package entity;

public class appointmentoutcome {
    private String Appid;
    private String patientId;
    private String doctorId;
    private String date;       // YYYY-MM-DD
    private String services;
    private String medication;
    private String notes;

    public appointmentoutcome(String Appid, String patientId, String doctorId, String date, String services, String medication, String notes) {
        this.Appid = Appid;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.services = services;
        this.medication = medication;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return String.format("Appointment ID: %s, Patient ID: %s, Doctor ID: %s, Date: %s, Services: %s, Medication: %s, Notes: %s",
                Appid, patientId, doctorId, date, services, medication, notes);
    }
}
