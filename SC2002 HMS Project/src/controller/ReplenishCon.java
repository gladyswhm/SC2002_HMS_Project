package controller;

import java.util.List;
import java.util.Scanner;

import controller.InventoryCon;
import entity.medicine;
import entity.replenish;
import enum_class.ReplenishStatus;
import loader.Read;
import loader.Write;

public class ReplenishCon {
    
    static Scanner sc = new Scanner(System.in);
    
    static List<replenish> replenishList = Read.readReplenishmentList("data/Replenishment_List.csv");

    public List<replenish> getReplenishList() {
        return replenishList;
    }

    // Pharmacist 4: Submit Replenishment Request
    public static void replenishmentRequest()
    {
        System.out.println("\n\n----- Submit Replenishment Request -----");

        // Ask Pharmacist to enter new replenishment request details
        System.out.print("Medicine Name: ");
        String medName = sc.nextLine();
        System.out.print("Requesting Amount: ");
        int reqAmt = Integer.parseInt(sc.nextLine());
        ReplenishStatus setStatusPending = ReplenishStatus.valueOf("Pending");

        // Create new replenish object
        replenish newReplenish = new replenish(medName, reqAmt, setStatusPending);
        replenishList.add(newReplenish);

        // Write new entry to Replenishment_List CSV file
        Write.saveReplenishmentListToCSV(replenishList);

        System.out.println("New replenishment request added successfully.\n\n");
    }



    public void updateReplenishment(String name, Integer newQuantity, ReplenishStatus newStatus) {
        for (replenish replenish : replenishList) {
            if (replenish.getMedicineName().equalsIgnoreCase(name)) {
                // Update the replenishment request
                if (newQuantity != null) {
                    replenish.setRequestedAmount(newQuantity);
                }
                if (newStatus != null) {
                    replenish.setStatus(newStatus);
                }
    
                // If the status is not "no" (approved), update the stock level
                if (newStatus != ReplenishStatus.Rejected) {
                    InventoryCon inventoryCon = new InventoryCon();
                    inventoryCon.updateStockLevelAfterReplenishment(name, replenish.getRequestedAmount());
                }
    
                Write.saveReplenishmentListToCSV(replenishList);  // Save the updated replenishment list
                System.out.println("Updated replenishment request: " + replenish);
                return;
            }
        }
        System.out.println("Replenishment request not found.");
    }
    

    public void displayReplenishment(String filterType, String value) {
        System.out.println("Replenishment List:");
        for (replenish replenish : replenishList) {
            boolean matches = true;
            
            if (filterType.equals("status")) {
                try {
                    ReplenishStatus filterStatus = ReplenishStatus.valueOf(value);
                    if (replenish.getStatus() != filterStatus) {
                        matches = false;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid status value: " + value);
                    matches = false;
                }
            }
    
            if (filterType.equals("quantity")) {
                try {
                    int quantity = Integer.parseInt(value);
                    if (replenish.getRequestedAmount() != quantity) {
                        matches = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity value: " + value);
                    matches = false;
                }
            }
    
            if (matches) {
                System.out.println(replenish);
            }
        }
    }
      
}
