package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import entity.medicalrecord;
import entity.medicine;
import entity.replenish;
import entity.staff;
import enum_class.*;
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

      //read staff
      public static List<staff> loadStaffList(String FILE_PATH) {
        List<staff> staffList = new ArrayList<>();
        File file = new File(FILE_PATH);
    
        if (!file.exists()) {
            System.out.println("No staff records found.");
            return staffList;
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
    
                try {
                    String staffId = fields[0].trim();
                    String password = fields[1].trim();
                    String name = fields[2].trim();
    
                    // Directly use the values from the CSV without toUpperCase()
                    String genderString = fields[4].trim();
                    String roleString = fields[3].trim();
                    //System.out.println("Parsing gender: " + genderString + ", role: " + roleString);
    
                
                    Gender gender = Gender.valueOf(genderString); 
                    Role role = Role.valueOf(roleString);          
    
                    int age = Integer.parseInt(fields[5].trim());
    
                    // Create a new staff object and add it to the list
                    staff staffMember = new staff(staffId, password, name, gender, role, age);
                    staffList.add(staffMember);
    
                } catch (NumberFormatException e) {
                   // System.out.println("Skipping line with invalid age: " + line);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //System.out.println("Skipping incomplete line: " + line);
                } catch (IllegalArgumentException e) {
                   // System.out.println("Skipping line with invalid gender or role: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading staff records: " + e.getMessage());
        }
    
        return staffList;
    }
    

    // read medicine
    public static List<medicine> loadMedicineList(String FILE_PATH) {
        List<medicine> medicineList = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        // Check if file exists
        if (!Files.exists(path)) {
            System.out.println("Medicine records file not found at path: " + FILE_PATH);
            return medicineList;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                try {
                    String name = fields[0].trim();
                    int stockLevel = Integer.parseInt(fields[1].trim());
                    int lowStockAlert = Integer.parseInt(fields[2].trim());

                    medicine med = new medicine(name, stockLevel, lowStockAlert);
                    medicineList.add(med);

                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading medicine records: " + e.getMessage());
        }

        return medicineList;
    }

    //read replenish
    public static List<replenish> readReplenishmentList(String filePath) {
    List<replenish> replenishList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            ReplenishStatus status = ReplenishStatus.valueOf(parts[2].trim());
            replenishList.add(new replenish(name, quantity, status));

        }
    } catch (IOException | IllegalArgumentException e) {
        e.printStackTrace();
    }
    return replenishList;
}



}