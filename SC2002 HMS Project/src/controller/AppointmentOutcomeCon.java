package controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entity.appointmentoutcome;
import entity.patient;
import loader.Read;
import loader.Write;

public class AppointmentOutcomeCon {

    static Scanner sc = new Scanner(System.in);

    static List<appointmentoutcome> appointmentOutcomeList = Read.loadAppointmentsOutcome("data/AppointmentOutcome_List.csv");

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


    // Pharmacist 1: View Appointment Outcome Record
    public static void viewOutcomeRecord()
    {
        System.out.println("\n\n----- View Appointment Outcome Record -----");
        System.out.print("Enter Appointment ID: ");
        String appID = sc.nextLine();
        
        // Used to check if there is such user
        boolean patientFound = false;

        // Traverse each line from the file
        for (appointmentoutcome line : appointmentOutcomeList)
        {
            // Check if there is such a patientID in the file
            if (line.getAppId().equals(appID))         // If found match
            {
                System.out.println("\n\nAppointment ID: " + line.getAppId());
                System.out.println("Patient's ID: " + line.getPatientId());
                System.out.println("Doctor's ID: " + line.getDoctorId());
                System.out.println("Date of Appointment: " + line.getDate());
                System.out.println("Services: " + line.getServices());
                System.out.println("Prescription: " + line.getMedication());
                System.out.println("Notes: \n" + line.getNotes());
                System.out.println("Prescription status: " + line.getStatus());

                patientFound = true;
                break;
            }     
        }

        if(patientFound == false)       // No such patient found
        {
            System.out.println("There is no such Appointment ID found.\n\n");
        }
    }
    
    
    // Pharmacist 2: Update Prescription Status
    public static void updateStatus()
    {
        System.out.println("\n\n----- Update Prescription Status -----");
        System.out.print("Enter Appointment ID: ");
        String appID = sc.nextLine();
        
        // Used to check if there is such user
        boolean patientFound = false;

        // Traverse each line from the file
        for (appointmentoutcome line : appointmentOutcomeList)
        {
            // Check if there is such a patientID in the file
            if (line.getAppId().equals(appID))         // If found match
            {
                System.out.println("Key in new prescription status: ");
                String newStatus = sc.nextLine();

                line.setStatus(newStatus);
                Write.saveAppointmentOutcome(appointmentOutcomeList);
                
                System.out.println("Prescription Status changed successfully.\n\n");
                patientFound = true;
                break;
            }     
        }

        if(patientFound == false)       // No such patient found
        {
            System.out.println("There is no such Appointment ID found.\n\n");
        }
    }
}