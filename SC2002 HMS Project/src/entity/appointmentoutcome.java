package entity;

public class appointmentoutcome {
    private String Appid;
    private String patientId;
    private String doctorId;
    private String date;       // YYYY-MM-DD
    private String services;
    private String medication;
    private String amount;
    private String status;

    public appointmentoutcome(String Appid, String patientId, String doctorId, String date, String services, String medication, String amount, String status) {
        this.Appid = Appid;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.services = services;
        this.medication = medication;
        this.amount = amount;
        this.status=status;
    }

    public String getAppId() {
        return Appid;
    }
    
    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public String getServices() {
        return services;
    }

    public String getMedication() {
        return medication;
    }

    public String getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setAppId(String Appid){
        this.Appid = Appid;
    }
    
    public void setPatientId(String patientId){
        this.patientId = patientId;
    }

    public void setDoctorId(String doctorId){
        this.doctorId = doctorId;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setServices(String services){
        this.services = services;
    }

    public void setMedication(String medication){
        this.medication = medication;
    }

    public void setAmount(String amount){
        this.amount = amount;
    }

    public void setStatus(String status){
        this.status=status;
    }



    @Override
    public String toString() {
        return String.format("Appointment ID: %s, Patient ID: %s, Doctor ID: %s, Date: %s, Services: %s, Medication: %s, Notes: %s, Status: %s",
                Appid, patientId, doctorId, date, services, medication, amount, status);
    }
}