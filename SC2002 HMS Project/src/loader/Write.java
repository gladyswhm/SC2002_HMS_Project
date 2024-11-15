package loader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entity.medicalrecord;

public class Write {
    public static void saveMedicalRecord(List<medicalrecord> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/medical_records.csv"))) {
            for (medicalrecord record : records) {
                writer.write(record.getPatientId() + "," + record.getPatientName() + "," + record.getDiagnosis() + "," + record.getTreatmentPlan() + "," + String.join(";", record.getMedications()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving medical records: " + e.getMessage());
        }
    }
}
