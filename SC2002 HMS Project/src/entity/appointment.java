package entity;

import enum_class.DoctorAppointmentStatus;

public class appointment {
    private String Appid;
    private String DoctorID;
    private String PatientID;
    private String date;
    private String time;
    private DoctorAppointmentStatus status;

    public appointment(String Appid, String DoctorID, String PatientID, String date, String time, DoctorAppointmentStatus status){
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.date = date;
        this.time = time;
        this.status = status;

    }

    public String geteAppID() {
        return Appid;
    }

    public String getDoctorId() {
        return DoctorID;
    }

    public String getPatientId() {
        return PatientID;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return time;
    }

    public DoctorAppointmentStatus getStatus() {
        return status;
    }

    public DoctorAppointmentStatus setStatus(DoctorAppointmentStatus status) {
        this.status = status;
        return this.status;
    }
}
