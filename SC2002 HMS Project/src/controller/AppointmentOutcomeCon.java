package controller;

import java.util.List;
import entity.appointmentoutcome;
import loader.Read;

public class AppointmentOutcomeCon {

    private String Appid;
    private String patientId;
    private String doctorId;
    private String date;       // YYYY-MM-DD
    private String services;
    private String medication;
    private String notes;
    

    static List<appointmentoutcome> appointmentOutcomeList = Read.loadAppointmentsOutcome("SC2002 HMS Project/data/AppointmentOutcome_List.csv");

     //admin display appointment outcome
     public static List<appointmentoutcome> getAppointmentOutcomeList() {
        return appointmentOutcomeList;
    }
}
