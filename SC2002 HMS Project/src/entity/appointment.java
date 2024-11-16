package entity;

import enum_class.DoctorAppointmentStatus;

public class appointment {
    private static String Appid;
    private static String DoctorID;
    private static String PatientID;
    private static String date;
    private static String time;
    private static DoctorAppointmentStatus status;

    public appointment(String Appid, String DoctorID, String PatientID, String date, String time, DoctorAppointmentStatus status){
        this.Appid = Appid;
        this.DoctorID = DoctorID;
        this.PatientID = PatientID;
        this.date = date;
        this.time = time;
        this.status = status;

    }

    public static String geteAppID() {
        return Appid;
    }

    public static String getDoctorId() {
        return DoctorID;
    }

    public static String getPatientId() {
        return PatientID;
    }

    public static String getDate() {
        return date;
    }

    public static String getTimeSlot() {
        return time;
    }

    public static DoctorAppointmentStatus getStatus() {
        return status;
    }

    public static DoctorAppointmentStatus setStatus(DoctorAppointmentStatus statusnew) {
        status = statusnew;
        return status;
    }
}
