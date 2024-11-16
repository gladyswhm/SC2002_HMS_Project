package controller;

import entity.medicalrecord;
import entity.patient;
import java.util.List;
import loader.Read;
import loader.Write;
import java.util.Scanner;

public class PatientCon {
    static List<patient> patientList = Read.readPatientList("SC2002 HMS Project/data/Patient_List.csv");

    public static List<patient> getPatientList() {
        return patientList;
    }

    static List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");
    
    public static void updateContactInfo(patient patients, int option) {
        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        if (option == 1) {  // Update email
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();  
            patients.setEmail(newEmail);  
        } else if (option == 2) {  // Update phone number
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();  
            patients.setPhonenumber(newPhoneNumber);  
        } else if (option == 3) {  // Update both email and phone number
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();  
            patients.setEmail(newEmail);  

            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();  
            patients.setPhonenumber(newPhoneNumber);  
        }
        Write.savePatientListToCSV(patientList);
    }

    public static void viewMedicalRecord(String patientID){
        boolean found = false;

    // Iterate through the records to find the one matching the patient's ID
        for (medicalrecord record : records) {
            if (record.getPatientId().equalsIgnoreCase(patientID)) {
                System.out.println("Medical Record for Patient: " + record.getPatientName());
                System.out.println("Diagnosis: " + record.getDiagnosis());
                System.out.println("Treatment Plan: " + record.getTreatmentPlan());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No medical record found for Patient ID: " + patientID);
        }
    }
}