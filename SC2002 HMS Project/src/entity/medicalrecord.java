package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a medical record for a patient, containing information such as diagnosis,
 * treatment plan, and prescribed medications.
 */
public class medicalrecord {

    private String recordID;         // Unique identifier for the medical record
    private String patientId;        // Unique identifier for the patient
    private String patientName;      // Name of the patient
    private String diagnosis;        // Diagnosis details
    private String treatmentPlan;    // Treatment plan details
    private List<String> medications; // List of prescribed medications

    /**
     * Constructs a medical record for a specified patient.
     *
     * @param patientId   the unique identifier of the patient
     * @param patientName the name of the patient
     */
    public medicalrecord(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.medications = new ArrayList<>();
    }

    /**
     * Sets the record ID for the medical record.
     *
     * @param recordID the unique identifier for the medical record
     */
    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    /**
     * Gets the record ID of the medical record.
     *
     * @return the record ID
     */
    public String getMRID() {
        return recordID;
    }

    /**
     * Gets the patient ID.
     *
     * @return the patient's unique identifier
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Gets the patient's name.
     *
     * @return the name of the patient
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Sets the diagnosis for the medical record.
     *
     * @param diagnosis the diagnosis details
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Gets the diagnosis details.
     *
     * @return the diagnosis details
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the treatment plan for the medical record.
     *
     * @param treatmentPlan the treatment plan details
     */
    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    /**
     * Gets the treatment plan details.
     *
     * @return the treatment plan details
     */
    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    /**
     * Sets the list of medications for the medical record.
     *
     * @param medications a list of prescribed medications
     */
    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    /**
     * Gets the list of medications prescribed to the patient.
     *
     * @return a list of medications
     */
    public List<String> getMedications() {
        return medications;
    }
}
