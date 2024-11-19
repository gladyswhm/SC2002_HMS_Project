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
import enum_class.AvailStatus;
import entity.appointment;
import entity.appointmentoutcome;

/**
 * The Write class provides utility methods for saving various data lists to the respective CSV files.
 */
public class Write {

    /**
     * Saves the patient list to a CSV file.
     *
     * @param patientList the list of patient objects to be saved.
     */
    public static void savePatientListToCSV(List<patient> patientList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Patient_List.csv"))) {

            for (patient p : patientList) {
                writer.write(
                    p.getUserID() + "," +
                    p.getPassword() + "," +
                    p.getName() + "," +
                    p.getDobAsFormattedString() + ", " +
                    p.getGender() + "," +
                    p.getBlood() + "," +
                    p.getEmail() + "," +
                    p.getPhonenumber()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving patient list: " + e.getMessage());
        }
    }

    /**
     * Saves the staff list to a CSV file.
     *
     * @param staffList the List<staff> objects to be saved.
     */
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

    /**
     * Saves medical records to a CSV file.
     *
     * @param records   the List<medicalrecord> objects to be saved.
     * @param doctorID  the ID of the doctor associated with the records.
     */
    public static void saveMedicalRecord(List<medicalrecord> records, String doctorID) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Medical_Records.csv"))) {
            for (medicalrecord record : records) {
                writer.write(record.getMRID() + "," + doctorID + "," + record.getPatientId() + "," + record.getPatientName() + "," + record.getDiagnosis() + "," + record.getTreatmentPlan() + "," + String.join(";", record.getMedications()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving medical records: " + e.getMessage());
        }
    }

    /**
     * Saves a new medical record to a CSV file.
     *
     * @param mrID      the medical record ID.
     * @param doctorID  the doctor ID associated with the record.
     * @param patID     the patient ID.
     * @param patName   the name of the patient.
     * @param diag      the diagnosis made by doctor. 
     * @param treat     the treatment plan.
     * @param medication the list of medications required associated with the record.
     */
    public static void saveNewMedicalRecord(String mrID, String doctorID, String patID, String patName, String diag, String treat, List<String> medication) {

        String newMedRec = mrID + "," + doctorID + "," + patID + "," + patName + "," + diag + "," + treat + "," + medication;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Medical_Records.csv", true))) {
            writer.newLine();
            writer.write(newMedRec);
        } catch (IOException e) {
            System.out.println("Error saving new medical record: " + e.getMessage());
        }
    }

    /**
     * Saves the appointment list to a CSV file.
     *
     * @param appointmentList the List<appointment> objects to be saved.
     */
    public static void saveAppointments(List<appointment> appointmentList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Doctor_Availability.csv"))) {
            for (appointment ap : appointmentList) {
                writer.write(ap.getAppID() + "," + ap.getDoctorId() + "," + ap.getDate() + "," + ap.getTimeSlot() + "," + ap.getStatus() + "," + ap.getDetails());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointment requests: " + e.getMessage());
        }
    }

    /**
     * Saves the appointment outcomes list to a CSV file.
     *
     * @param appointmentoutcomelist the List<appointmentoutcome> objects to be saved.
     */
    public static void saveAppointmentOutcome(List<appointmentoutcome> appointmentoutcomelist) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/AppointmentOutcome_List.csv"))) {
            for (appointmentoutcome outcome : appointmentoutcomelist) {
                writer.write(
                    outcome.getAppId() + "," +
                    outcome.getPatientId() + "," +
                    outcome.getDoctorId() + "," +
                    outcome.getDate() + "," +
                    outcome.getServices() + "," +
                    outcome.getMedication() + "," +
                    outcome.getAmount() + "," +
                    outcome.getStatus() + "," +
                    outcome.getPayment()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointment outcome records: " + e.getMessage());
        }
    }

    /**
     * Saves the medicine list to a CSV file.
     *
     * @param medicineList the List<medicine> objects to be saved.
     */
    public static void saveMedicineListToCSV(List<medicine> medicineList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Medicine_List.csv"))) {
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

    /**
     * Saves the replenishment list to a CSV file.
     *
     * @param replenishmentList the List<replenish> objects to be saved.
     */
    public static void saveReplenishmentListToCSV(List<replenish> replenishmentList) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Replenishment_List.csv"))) {
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
}