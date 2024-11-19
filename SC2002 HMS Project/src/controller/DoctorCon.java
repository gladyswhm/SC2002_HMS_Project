package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entity.medicalrecord;
import loader.Read;
import loader.Write;


 /**
 * The class Doctor con
 */ 
public class DoctorCon {
    static List<AvailabilityCon> avail = Read.loadAvailability("data/Doctor_Availability.csv");
 
    //option 1: view medical record

/** 
 *
 * Display medical records
 *
 */
    public static void displayMedicalRecords(){ 

        List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");
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


/** 
 *
 * Add patient medical records
 *
 * @param doctorID  the doctor identifier. 
 * @param medicalRecords  the medical records. 
 * @param sc  the sc. 
 */
    public static void addPatientMedicalRecords(String doctorID, List<medicalrecord> medicalRecords, Scanner sc){ 

        int value = Read.getOutcomeID("data/Medical_Records.csv");
        value++;

        System.out.println("Enter Patient ID: ");
        String patID = sc.nextLine();

        System.out.println("Enter Patient Name: ");
        String patName = sc.nextLine();

        System.out.println("Enter new Diagnosis: ");
        String newdiagnosis = sc.nextLine();
        
        System.out.println("Enter new Treatment Plan: ");
        String newTP = sc.nextLine();

        System.out.println("Enter prescribed medication (separate by comma): ");
        String inputMed = sc.nextLine();
        List<String> PresMedication = new ArrayList<>(Arrays.asList(inputMed.split(",\\s*")));
            
        //add into list
        Write.saveNewMedicalRecord(Integer.toString(value), doctorID, patID, patName, newdiagnosis, newTP, PresMedication);
    }
    //edit medical record (option 3 for doctor)

/** 
 *
 * Update patient medical records
 *
 * @param medicalRecords  the medical records. 
 * @param sc  the sc. 
 * @param DoctorID  the doctor identifier. 
 */
    public static void updatePatientMedicalRecords(List<medicalrecord> medicalRecords, Scanner sc, String DoctorID) { 

        System.out.print("Enter Medical Record ID to update (e.g., 2004): ");
        String MRID = sc.nextLine();

        medicalrecord medicalRecordtoChange = findMedicalRecord(MRID, medicalRecords);
        if(medicalRecordtoChange != null){
            System.out.println("Updating Medical Record " + MRID + " for " + medicalRecordtoChange.getPatientName());
            
            System.out.println("Enter new Diagnosis: ");
            String newdiagnosis = sc.nextLine();
            
            System.out.println("Enter new Treatment Plan: ");
            String newTP = sc.nextLine();

            System.out.println("Enter prescribed medication (separate by comma): ");
            String inputMed = sc.nextLine();
            List<String> PresMedication = new ArrayList<>(Arrays.asList(inputMed.split(",\\s*")));
            
            DoctorCon.editMedicalRecord(MRID,newdiagnosis,newTP,PresMedication, DoctorID);
            System.out.println("Medical Record updated successfully for " + medicalRecordtoChange.getPatientName());
        }
        else System.out.println("Medical Record not found for: " + MRID);
    }
    

/** 
 *
 * Edit medical record
 *
 * @param MRID  the  MRID. 
 * @param newDiagnosis  the new diagnosis. 
 * @param newTreatmentPlan  the new treatment plan. 
 * @param newMedications  the new medications. 
 * @param DoctorID  the doctor identifier. 
 */
    public static void editMedicalRecord(String MRID, String newDiagnosis, String newTreatmentPlan, List<String> newMedications, String DoctorID) { 

        boolean found = false;
        List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");

        //iterate through records to find the matching patientId
        for (medicalrecord record : records) {
            System.out.println("Comparing: " + record.getMRID() + " with " + MRID);
            if (record.getMRID().trim().equalsIgnoreCase(MRID)) {
                record.setDiagnosis(newDiagnosis) ;
                record.setTreatmentPlan(newTreatmentPlan) ;
                record.setMedications(newMedications) ;
                found = true;
                break;
            }
        }

        if (found) {
            Write.saveMedicalRecord(records, DoctorID);
            System.out.println("Medical record updated successfully for: " + MRID);
        } else {
            System.out.println("Medical record not found for: " + MRID);
        }
    }


/** 
 *
 * Display available patient ids
 *
 */
    public static void displayAvailablePatientIds() { 

        List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");
        if (records.isEmpty()) {
            System.out.println("No medical records available.");
            return;
        }

        System.out.println("Available Patient IDs:");
        for (medicalrecord record : records) {
            System.out.println("ID: " + record.getMRID() +" Patient ID: " + record.getPatientId() + ", Name: " + record.getPatientName());
        }
        System.out.println("------------------------------------");
    }


/** 
 *
 * Find medical record
 *
 * @param MRID  the  MRID. 
 * @param medicalRecords  the medical records. 
 * @return medicalrecord
 */
    public static medicalrecord findMedicalRecord(String MRID, List<medicalrecord> medicalRecords){ 

        List<medicalrecord> records = Read.loadMedicalRecords("data/Medical_Records.csv");
        for(medicalrecord r : records){
            if(r.getMRID().equals(MRID)){
                return r;
            }
        }
        return null; //if not found
    }   
}
