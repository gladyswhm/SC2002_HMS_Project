package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import entity.patient;
import entity.appointment;
import entity.appointmentoutcome;
import entity.medicalrecord;
import entity.medicine;
import entity.replenish;
import entity.staff;
import enum_class.*;
import controller.AppointmentCon;
import controller.AppointmentOutcomeCon;
import controller.AvailabilityCon;

public class Read {
    
    // Read Patient_List csv file
    public static List<patient> readPatientList(String filePath)
    {
        List<patient> patientList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  // Updated format
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
    
                // Extract and trim data
                String userID = parts[0].trim();
                String password = parts[1].trim();
                String name = parts[2].trim();
                
                // Parse the date of birth
                Date dob = dateFormat.parse(parts[3].trim());
                
                // Parse Gender and BloodType enums
                Gender gender = Gender.valueOf(parts[4].trim());  // This assumes proper case matching
                BloodType bloodType = BloodType.fromString(parts[5].trim());  // Use BloodType's fromString method
    
                String email = parts[6].trim();
                String phonenumber = parts[7].trim();
    
                patient newPatient = new patient(userID, password, name, dob, gender, bloodType, email, phonenumber);
                patientList.add(newPatient);
            }
        } catch (IOException | ParseException | IllegalArgumentException e) {
            e.printStackTrace();  // Handle exceptions (e.g., wrong enum values, invalid date)
        }
        return patientList;
    }


    // Read Staff_List csv file
    public static List<staff> loadStaffList(String FILE_PATH)
    {
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
                    String fileStaffID = fields[0].trim();      // 1st column in csv file is staffID
                    String filePassword = fields[1].trim();    // 2nd column in csv file is password
                    String fileName = fields[2].trim();    // 3rd column in csv file is name
                    Role fileRole = Role.valueOf(fields[3].trim());    // 4th column in csv file is role
                    Gender fileGender = Gender.valueOf(fields[4].trim());    // 5th column in csv file is gender
                    int fileAge = Integer.parseInt(fields[5].trim());    // 6th column in csv file is age

                    staff staffMember = new staff(fileStaffID, filePassword, fileName, fileGender, fileRole, fileAge);
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
                    String doctorid = fields[1].trim();
                    String patid = fields[2].trim();
                    String name = fields[3].trim();
                    String diag = fields[4].trim();
                    String treatment = fields[5].trim();
                    List<String> meds = new ArrayList<>(Arrays.asList(fields[6].trim().split(";"))); // Split medications by semicolon
    
                    // Create a new MedicalRecord and set fields
                    medicalrecord record = new medicalrecord(patid, name);
                    record.setRecordID(id);
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

    //read doctor availability - accepting and declining done here too
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

                String id = fields[0].trim();
                String doctorId = fields[1].trim();
                String date = fields[2].trim();
                String timeSlot = fields[3].trim();
                String s = fields[4].trim();
                AvailStatus status = AvailStatus.valueOf(s);
                String notes = fields[5].trim();
                
                availabilityList.add(new AvailabilityCon(Integer.parseInt(id),doctorId, date, timeSlot, status, notes));
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

    //load appointments list
    public static List<appointment> loadAppointments(String APPOINTMENT_FILE_PATH) {
        List<appointment> appointmentList = new ArrayList<>();
        File file = new File(APPOINTMENT_FILE_PATH);

        if (!file.exists()) {
            System.out.println("No appointment requests found.");
            return appointmentList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 6) continue;

                String Appid = fields[0].trim();
                String doctorId = fields[1].trim();
                String date = fields[2].trim();
                String timeSlot = fields[3].trim();
                String s = fields[4].trim();
                AvailStatus status = AvailStatus.valueOf(s);
                String details = fields[5].trim();

                appointmentList.add(new appointment(Appid,doctorId, date, timeSlot, status, details));
            }
        } catch (IOException e) {
            System.out.println("Error loading appointment requests: " + e.getMessage());
        }
        return appointmentList;
    }

    public static List<appointmentoutcome> loadAppointmentsOutcome(String APPOINTMENT_FILE_PATH) {
    List<appointmentoutcome> appointmentOutcomeList = new ArrayList<>();
    File file = new File(APPOINTMENT_FILE_PATH);

    if (!file.exists()) {
        System.out.println("No appointment outcome records found.");
        return appointmentOutcomeList;
    }

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
            // Skip BOM if it's the first line
            if (firstLine && line.startsWith("\uFEFF")) {
                line = line.substring(1); // Remove BOM
                firstLine = false;
            }

            line = line.trim(); // Remove unwanted characters
            String[] fields = line.split(",");
            if (fields.length < 8) {
                System.out.println("Skipping invalid line: " + line);
                continue;
            }

            String Appid = fields[0].trim();
            String patientId = fields[1].trim();
            String doctorId = fields[2].trim();
            String date = fields[3].trim();
            String services = fields[4].trim();
            String medication = fields[5].trim();
            String notes = fields[6].trim();
            String status = fields[7].trim();


            appointmentOutcomeList.add(new appointmentoutcome(Appid, patientId, doctorId, date, services, medication, notes,status));
        }
    } catch (IOException e) {
        System.out.println("Error loading appointment outcome records: " + e.getMessage());
    }
    return appointmentOutcomeList;
}
    

    // read medicine
    public static List<medicine> loadMedicineList(String FILE_PATH) {
        List<medicine> medicineList = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);


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

    
    public static int getOutcomeID(String filepath) {
        int latestId = 13113;  //for testing
        String line = null;
        String lastnonempty = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            while ((line = reader.readLine()) != null) {
                if(!line.trim().isEmpty()){
                    lastnonempty = line;
                }
            }
            
            String[] fields = lastnonempty.split(",");
            latestId = Integer.parseInt(fields[0].trim());


        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing ID: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        return latestId;
    
    }

}