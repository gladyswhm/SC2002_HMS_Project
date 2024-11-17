package controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entity.appointment;
import entity.appointmentoutcome;
import entity.patient;
import entity.replenish;
import enum_class.AvailStatus;
import enum_class.ReplenishStatus;
import loader.Read;
import loader.Write;

public class AppointmentOutcomeCon {

    static Scanner sc = new Scanner(System.in);
    private static int OUTid = Read.getOutcomeID("data/AppointmentOutcome_List.csv");

    static List<appointmentoutcome> appointmentOutcomeList = Read.loadAppointmentsOutcome("data/AppointmentOutcome_List.csv");

     //admin display appointment outcome
     public static List<appointmentoutcome> getAppointmentOutcomeList() {
        return appointmentOutcomeList;
    }

    //patient view their appointment records
    public static List<appointmentoutcome> getAppointmentOutcomeForPatient(String patientId) {
        return appointmentOutcomeList.stream()
                                     .filter(record -> record.getPatientId().equals(patientId))
                                     .collect(Collectors.toList());
    }


    // Pharmacist 1: View Appointment Outcome Record
    public static void viewOutcomeRecord()
    {
        System.out.println("\n\n----- View Appointment Outcome Record -----");
        System.out.print("Enter Appointment ID (e.g. 13001): ");
        String appID = sc.nextLine();
        
        // Used to check if there is such an appointment
        boolean aptFound = false;


        // Traverse each line from the file
        for (appointmentoutcome line : appointmentOutcomeList)
        {
            // Check if there is such a patientID in the file
            if (line.getAppId().equals(appID))         // If found match
            {
                System.out.println("\n\nAppointment ID: " + line.getAppId());
                System.out.println("Patient's ID: " + line.getPatientId());
                System.out.println("Doctor's ID: " + line.getDoctorId());
                System.out.println("Date of Appointment: " + line.getDate());
                System.out.println("Services: " + line.getServices());
                System.out.println("Prescription: " + line.getMedication());
                System.out.println("Amount: " + line.getAmount());
                System.out.println("Prescription status: " + line.getStatus());
                System.out.println("Payment status: " + line.getPayment());

                aptFound = true;
                break;
            }     
        }

        if(aptFound == false)       // No such appointment found
        {
            System.out.println("There is no such Appointment ID found.\n\n");
        }
    }
    
    
    // Pharmacist 2: Update Prescription Status
    public static void updateStatus()
    {
        System.out.println("\n\n----- Update Prescription Status -----");
        System.out.print("Enter Appointment ID: ");
        String appID = sc.nextLine();
        
        // Used to check if there is such an appointment
        boolean aptFound = false;

        // Traverse each line from the file
        for (appointmentoutcome line : appointmentOutcomeList)
        {
            // Check if there is such a aptID in the file
            if (line.getAppId().equals(appID))         // If found match
            {
                System.out.println("Would you like to change prescription status to 'dispensed'? (Y/N)");
                String choice = sc.nextLine();
                
                if (choice.equals("Y"))
                {
                    if (!(line.getStatus().equals("Dispensed")))
                    {
                        String newStatus = "Dispensed";
                        line.setStatus(newStatus);
                        Write.saveAppointmentOutcome(appointmentOutcomeList);

                        // Update stock level in Medicine_list
                        InventoryCon.dispensedMedicine(line.getMedication(), Integer.parseInt(line.getAmount()));
                        System.out.println("Prescription Status changed successfully.\n\n");
                    }
                    else
                    {
                        System.out.println("Medication have already been dispensed.\n\n");
                    }
                }

                else if (choice.equals("N"))
                {
                    System.out.println("Prescription Status not changed.\n\n");
                }
                
                aptFound = true;
                break;
            }     
        }

        if(aptFound == false)       // No such appointment found
        {
            System.out.println("There is no such Appointment ID found.\n\n");
        }
    }

    //for appointment outcome record (option 7)
    public static void AppointmentOutcomeRecord(String doctorId, Scanner sc){
        OUTid++;
        AppointmentCon.displayUpcomingAppointments(doctorId);
        System.out.println("\n--- Recording Appointment Outcome ---");
        System.out.println("Enter the patientID: ");
        String patID = sc.nextLine();
        System.out.println("Enter the date of appointment: ");
        String aptDate = sc.nextLine();
        System.out.println("Enter the type of service provided: ");
        String aptType = sc.nextLine();
        System.out.println("Enter the prescribed medications: ");
        String aptMedications = sc.nextLine();
        System.out.println("Enter the amount required: ");
        String amount = sc.nextLine();

        // Create new appointment object
        appointmentoutcome newOut = new appointmentoutcome(Integer.toString(OUTid), patID, doctorId,aptDate, aptType,aptMedications, amount, "Pending", "Unpaid");
        appointmentOutcomeList.add(newOut);

        // Write new entry to Replenishment_List CSV file
        Write.saveAppointmentOutcome(appointmentOutcomeList);
    }
    
    public static void makePayment(String userID){
        boolean found = false;

        System.out.println("--- Make Payment ---");
        System.out.print("Enter Appointment ID (e.g. 13001): ");
        String appID = sc.nextLine();
        String method;

        for (appointmentoutcome list : appointmentOutcomeList) {
            if (list.getAppId().equals(appID)) {
                if (!(list.getPayment().equals("Paid"))){
                    do{
                        System.out.println("Select payment method:");
                        System.out.println("1. Cash");
                        System.out.println("2. Card");
                        method = sc.nextLine();
                        if(method.equals("1") || method.equals("2")){
                            list.setPayment("Paid");
                        }
                        else{
                            System.out.println("You have selected an invalid payment method.\n");
                        }
                    }while(!(method.equals("1") || method.equals("2")));
                    
                    
                    found = true;
                    break;
                }

                else{
                    System.out.println("You have already paid.");
                }
                
            }
        }

        if (found) {
            Write.saveAppointmentOutcome(appointmentOutcomeList);
            System.out.println("Payment has been made");
        } 
    }

}