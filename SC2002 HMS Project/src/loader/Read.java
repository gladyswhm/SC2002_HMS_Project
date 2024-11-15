package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.medicalrecord;
import enum_class.AvailStatus;
import enum_class.DoctorAppointmentStatus;
import controller.AppointmentCon;
import controller.AvailabilityCon;

public class Read {
    //read medical records
    public static List<medicalrecord> loadMedicalRecords(String FILE_PATH) {
        List<medicalrecord> records = new ArrayList<>();
        File file = new File(FILE_PATH);
    
        if (!file.exists()) {
            System.out.println("No medical records found.");
            return records;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                
                try {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String diag = fields[2].trim();
                    String treatment = fields[3].trim();
                    List<String> meds = new ArrayList<>(Arrays.asList(fields[4].trim().split(";"))); // Split medications by semicolon
    
                    // Create a new MedicalRecord and set fields
                    medicalrecord record = new medicalrecord(id, name);
                    record.setDiagnosis(diag);
                    record.setTreatmentPlan(treatment);
                    record.setMedications(meds);
    
                    // Add the record to the list
                    records.add(record);
    
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line with invalid patient ID: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading medical records: " + e.getMessage());
        }
        return records;
    }

    //read doctor availability
    public static List<AvailabilityCon> loadAvailability(String AVAILABILITY_FILE_PATH) {
        List<AvailabilityCon> availabilityList = new ArrayList<>();
        File file = new File(AVAILABILITY_FILE_PATH);

        if (!file.exists()) {
            System.out.println("No availability records found.");
            return availabilityList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 4) continue;

                String doctorId = fields[0].trim();
                String date = fields[1].trim();
                String timeSlot = fields[2].trim();
                String s = fields[3].trim();
                AvailStatus status = AvailStatus.valueOf(s);
                
                availabilityList.add(new AvailabilityCon(doctorId, date, timeSlot, status));
            }
        } catch (IOException e) {
            System.out.println("Error loading availability: " + e.getMessage());
        }
        return availabilityList;
    }

    // Read file (USER part)
    public static List<String> readFile(String filePath)
    {
        List<String> lines = new ArrayList<>();     // Initialize an empty list to avoid returning null in case of exceptions
        
        try
        {
            lines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.\n\n");
            e.printStackTrace();
        }
        
        return lines;       // Return the list (even if it's empty in case of an exception)
    }

    //load appointments
    public static List<AppointmentCon> loadAppointments(String APPOINTMENT_FILE_PATH) {
        List<AppointmentCon> appointmentList = new ArrayList<>();
        File file = new File(APPOINTMENT_FILE_PATH);

        if (!file.exists()) {
            System.out.println("No appointment requests found.");
            return appointmentList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 5) continue;

                String doctorId = fields[0].trim();
                String patientId = fields[1].trim();
                String date = fields[2].trim();
                String timeSlot = fields[3].trim();
                String s = fields[4].trim();
                DoctorAppointmentStatus status = DoctorAppointmentStatus.valueOf(s);


                appointmentList.add(new AppointmentCon(doctorId, patientId, date, timeSlot, status));
            }
        } catch (IOException e) {
            System.out.println("Error loading appointment requests: " + e.getMessage());
        }
        return appointmentList;
    }

}
