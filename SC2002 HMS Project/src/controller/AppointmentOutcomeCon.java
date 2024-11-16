package controller;

import java.util.List;
import java.util.stream.Collectors;

import entity.appointmentoutcome;
import loader.Read;

public class AppointmentOutcomeCon {

    static List<appointmentoutcome> appointmentOutcomeList = Read.loadAppointmentsOutcome("SC2002 HMS Project/data/AppointmentOutcome_List.csv");

     //admin display appointment outcome
     public static List<appointmentoutcome> getAppointmentOutcomeList() {
        return appointmentOutcomeList;
    }

    //patient view their appointment records
    public static List<appointmentoutcome> getAppointmentOutcomeForPatient(String patientId) {
        return appointmentOutcomeList.stream()
                                     .filter(record -> record.getPatientId().equals(patientId))
                                     .collect(Collectors.toList());
    }

}
