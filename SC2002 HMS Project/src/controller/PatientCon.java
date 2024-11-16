package controller;

import entity.patient;
import java.util.List;
import loader.Read;
import loader.Write;
import java.util.Scanner;

public class PatientCon {
    static List<patient> patientList = Read.readPatientList("SC2002 HMS Project/data/Patient_List.csv");

    public static List<patient> getPatientList() {
        return patientList;
    }
    
    public static void updateContactInfo(patient patients, int option) {
        Scanner scanner = new Scanner(System.in); // Initialize Scanner for user input

        if (option == 1) {  // Update email
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();  
            patients.setEmail(newEmail);  
        } else if (option == 2) {  // Update phone number
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();  
            patients.setPhonenumber(newPhoneNumber);  
        } else if (option == 3) {  // Update both email and phone number
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();  
            patients.setEmail(newEmail);  

            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();  
            patients.setPhonenumber(newPhoneNumber);  
        }
        Write.savePatientListToCSV(patientList);
    }

    

}
