package controller;

import java.util.ArrayList;
import java.util.List;
import entity.medicalrecord;
import loader.Read;
import loader.Write;

public class DoctorCon extends StaffCon {
    static List<medicalrecord> records = Read.loadMedicalRecords("data/medical_records.csv");

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

    
}