package loader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import controller.AppointmentCon;
import controller.AvailabilityCon;
import entity.medicalrecord;
import entity.staff;
import entity.appointment;

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
                writer.write(availability.getDoctorId() + "," + availability.getDate() + "," + availability.getTimeSlot() + "," + availability.getStatus());
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

    private static void saveAppointmentOutcomeRecord(String doctorId, String aptDate, String aptID, String aptType, String aptMedications, String aptStatus) {
    
        String outcomeRecord = doctorId + "," + aptDate + "," + aptID + "," + aptType + "," + aptMedications + "," + aptStatus;
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/AppointmentOutcome_List", true))) {
            writer.write(outcomeRecord);  
            writer.newLine();    
        } catch (IOException e) {
            System.out.println("Error saving appointment outcome record: " + e.getMessage());
        }
    }

    public static void saveStaffList(List<staff> staffList) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Staff_List.csv"))) {
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
    
}
