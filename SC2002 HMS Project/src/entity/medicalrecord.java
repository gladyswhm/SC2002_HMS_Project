package entity;

import java.util.ArrayList;
import java.util.List;

public class medicalrecord {
    private String recordID;
    private String doctorID;
    private String patientId;
    private String patientName;
    private String diagnosis;
    private String treatmentPlan;
    private List<String> medications;
    
    public medicalrecord(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.medications = new ArrayList<>();
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public List<String> getMedications() {
        return medications;
    }
}
