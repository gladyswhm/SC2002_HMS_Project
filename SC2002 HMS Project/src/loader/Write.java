package loader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import controller.AppointmentCon;
import controller.AvailabilityCon;
import entity.medicalrecord;
import entity.medicine;
import entity.patient;
import entity.replenish;
import entity.staff;
import enum_class.DoctorAppointmentStatus;
import entity.appointment;
import entity.doctoravailability;

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

    public static void saveDoctorAvailability(List<AvailabilityCon> availabilityList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Doctor_Availability.csv"))) {
            for (AvailabilityCon availability : availabilityList) {
                writer.write(doctoravailability.getDoctorId() + "," + doctoravailability.getDate() + "," + doctoravailability.getTimeSlot() + "," + doctoravailability.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving availability: " + e.getMessage());
        }
    }

    public static void saveAppointments(List<appointment> appointmentList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Appointment_List"))) {
            for (appointment ap : appointmentList) {
                writer.write(ap.getDoctorId() + "," + ap.getPatientId() + "," + ap.getDate() + "," + ap.getTimeSlot() + "," + ap.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointment requests: " + e.getMessage());
        }
    }

    public static void saveAppointmentOutcomeRecord(String doctorId, String aptDate, String aptID, String aptType, String aptMedications, DoctorAppointmentStatus aptStatus) {
    
        String outcomeRecord = doctorId + "," + aptDate + "," + aptID + "," + aptType + "," + aptMedications + "," + aptStatus;
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/AppointmentOutcome_List", true))) {
            writer.write(outcomeRecord);  
            writer.newLine();    
        } catch (IOException e) {
            System.out.println("Error saving appointment outcome record: " + e.getMessage());
        }
    }

    public static void saveStaffList(List<staff> staffList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SC2002 HMS Project/data/Staff_List.csv"))) {
            for (staff staffMember : staffList) {
                writer.write(
                    staffMember.getUserID() + "," +
                    staffMember.getPassword() + "," +
                    staffMember.getName() + "," +
                    staffMember.getRole().toString() + "," +
                    staffMember.getGender().toString() + "," +
                    staffMember.getAge()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving staff records: " + e.getMessage());
        }
    }


 
 
    public static void saveMedicineListToCSV(List<medicine> medicineList) { 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("SC2002 HMS Project/data/Medicine_List.csv"))) { 
        // Write medicine data without header 
        for (medicine med : medicineList) { 
            writer.write( 
                med.getName() + "," + 
                med.getStockLevel() + "," + 
                med.getLowStockAlert() 
            ); 
            writer.newLine(); 
        } 
 
        System.out.println("Medicine list saved to Medicine_List.csv."); 
 
    } catch (IOException e) { 
        System.out.println("Error saving medicine list: " + e.getMessage()); 
    } 
} 
 
    public static void saveReplenishmentListToCSV(List<replenish> replenishmentList) { 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("SC2002 HMS Project/data/Replenishment_List.csv"))) { 
        // Write replenishment data without header 
        for (replenish replenishment : replenishmentList) { 
            writer.write( 
                replenishment.getMedicineName() + "," + 
                replenishment.getRequestedAmount() + "," + 
                replenishment.getStatus() 
            ); 
            writer.newLine(); 
        } 
 
        System.out.println("Replenishment list saved to Replenishment_List.csv."); 
 
    } catch (IOException e) { 
        System.out.println("Error saving replenishment list: " + e.getMessage()); 
    } 
} 
 
    public static void savePatientListToCSV(List<patient> patientList) { 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SC2002 HMS Project/data/Patient_List.csv"))) { 
             
            for (patient p : patientList) { 
                writer.write( 
                    p.getUserID() + "," + 
                    p.getPassword() + "," + 
                    p.getName() + "," + 
                    p.getDobAsFormattedString()+", "+ 
                    p.getGender() + "," + 
                    p.getBlood() + "," + 
                    p.getEmail() + "," + 
                    p.getPhonenumber() 
                ); 
                writer.newLine();  
            } 
 
            System.out.println("Patient list saved to Patient_List.csv."); 
 
        } catch (IOException e) { 
            System.out.println("Error saving patient list: " + e.getMessage()); 
        } 
    }
}