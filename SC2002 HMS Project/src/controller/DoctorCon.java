package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entity.medicalrecord;
import loader.Read;
import loader.Write;

public class DoctorCon extends StaffCon {
    static List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");
    static List<AvailabilityCon> avail = Read.loadAvailability("data/Doctor_Availability.csv");

    //option 1: view medical record
    public static void displayMedicalRecords(){
        if (records.isEmpty()) {
            System.out.println("No medical records available.");
            return;
        }

        System.out.println("--- Showing all Medical Records ---");
        for (medicalrecord record : records) {
            System.out.println("Patient ID: " + record.getPatientId() + ", Name: " + record.getPatientName() + ", Diagnosis: " + record.getDiagnosis() + ", Treatment Plans: " + record.getTreatmentPlan() + ", Medications: " + record.getMedications());
        }
        System.out.println("------------------------------------");
    }

    //edit medical record (option 2 for doctor)
    public static void updatePatientMedicalRecords(List<medicalrecord> medicalRecords, Scanner sc) {
        System.out.print("Enter Patient ID to update (e.g., P1001): ");
        String patientID = sc.nextLine();

        medicalrecord medicalRecordtoChange = findMedicalRecord(patientID, medicalRecords);
        if(medicalRecordtoChange != null){
            System.out.println("Updating Medical Record for " + patientID + " " + medicalRecordtoChange.getPatientName());
            
            System.out.println("Enter new Diagnosis: ");
            String newdiagnosis = sc.nextLine();
            
            System.out.println("Enter new Treatment Plan: ");
            String newTP = sc.nextLine();

            System.out.println("Enter prescribed medication (separate by comma): ");
            String inputMed = sc.nextLine();
            List<String> PresMedication = new ArrayList<>(Arrays.asList(inputMed.split(",\\s*")));
            
            DoctorCon.editMedicalRecord(patientID,newdiagnosis,newTP,PresMedication);
            System.out.println("Medical Record updated successfully for " + medicalRecordtoChange.getPatientName());
        }
        else System.out.println("Medical Record not found for Patient ID: " + patientID);
    }
    
    public static void editMedicalRecord(String patientId, String newDiagnosis, String newTreatmentPlan, List<String> newMedications) {
        boolean found = false;

        //iterate through records to find the matching patientId
        for (medicalrecord record : records) {
            System.out.println("Comparing: " + record.getPatientId() + " with " + patientId);
            if (record.getPatientId().trim().equalsIgnoreCase(patientId)) {
                record.setDiagnosis(newDiagnosis) ;
                record.setTreatmentPlan(newTreatmentPlan) ;
                record.setMedications(newMedications) ;
                found = true;
                break;
            }
        }

        if (found) {
            Write.saveMedicalRecord(records);
            System.out.println("Medical record updated successfully for Patient ID: " + patientId);
        } else {
            System.out.println("Medical record not found for Patient ID: " + patientId);
        }
    }

    public static void displayAvailablePatientIds() {
        if (records.isEmpty()) {
            System.out.println("No medical records available.");
            return;
        }

        System.out.println("Available Patient IDs:");
        for (medicalrecord record : records) {
            System.out.println("Patient ID: " + record.getPatientId() + ", Name: " + record.getPatientName());
        }
        System.out.println("------------------------------------");
    }

    public static medicalrecord findMedicalRecord(String patientID, List<medicalrecord> medicalRecords){
        for(medicalrecord r : records){
            if(r.getPatientId().equals(patientID)){
                return r;
            }
        }
        return null; //if not found
    }

    //option 3
    
    
}