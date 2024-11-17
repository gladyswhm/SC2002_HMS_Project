package controller;

import java.util.List;

import entity.appointmentoutcome;
import entity.medicine;
import loader.Read;
import loader.Write;

public class InventoryCon {
    static List<medicine> medicineList = Read.loadMedicineList("data/Medicine_List.csv");

    public List<medicine> getMedicineList() {
        return medicineList;
    }

    // Pharmacist 3: View Medication Inventory
    public static void viewInventory()
    {
        System.out.println("\n\n----- View Medication Inventory -----");

        // Traverse each line from the file
        for (medicine line : medicineList)
        {
            System.out.println("\nMedicine name: " + line.getName());
            System.out.println("Quantity: " + line.getStockLevel());
        }
    }


    // Update stock level after dispense
    public static void dispensedMedicine(String medName, int dispensedAmt)
    {
        // Traverse each line from the file
        for (medicine line : medicineList)
        {
            if(line.getName().equals(medName))
            {
                int newAmt = line.getStockLevel() - dispensedAmt;
                line.setStockLevel(newAmt);
                Write.saveMedicineListToCSV(medicineList);
            }
        }
    }


    public void addMedicine(medicine medicine) {
        medicineList.add(medicine); 
        Write.saveMedicineListToCSV(medicineList); 
    }
    

    public boolean removeMedicine(String medicineName) {
        medicine medicineToRemove = findMedicineByName(medicineName);
        if (medicineToRemove != null) {
            medicineList.remove(medicineToRemove);
            Write.saveMedicineListToCSV(medicineList);
            return true;
        }
        return false;
    }
    

    public medicine findMedicineByName(String name) {
        for (medicine medicine : medicineList) {
            if (medicine.getName().equalsIgnoreCase(name)) {
                return medicine;
            }
        }
        return null;
    }
    
    public void updateStockLevel(String medicineName, int newStockLevel) {
        medicine medicine = findMedicineByName(medicineName);
        if (medicine != null) {
            System.out.println("Before update: " + medicine.getStockLevel());
            medicine.setStockLevel(newStockLevel);
            System.out.println("After update: " + medicine.getStockLevel());
            Write.saveMedicineListToCSV(medicineList);
            System.out.println("Stock level updated for " + medicineName + ": " + newStockLevel);
        } else {
            System.out.println("Medicine not found: " + medicineName);
        }
    }

    public void updateLowStockAlert(String medicineName, int newLowStockAlert) {
        medicine medicine = findMedicineByName(medicineName);
        if (medicine != null) {
            medicine.setLowStockAlert(newLowStockAlert);
            Write.saveMedicineListToCSV(medicineList); 
            System.out.println("Low stock alert updated for " + medicineName + ": " + newLowStockAlert);
        } else {
            System.out.println("Medicine not found: " + medicineName);
        }
    }

    // New method to update stock level after replenishment
    public void updateStockLevelAfterReplenishment(String medicineName, int replenishmentQuantity) {
        medicine medicine = findMedicineByName(medicineName);
        if (medicine != null) {
            int updatedStockLevel = medicine.getStockLevel() + replenishmentQuantity;
            medicine.setStockLevel(updatedStockLevel);
            Write.saveMedicineListToCSV(medicineList); 
            System.out.println("Updated stock level for " + medicineName + ": " + updatedStockLevel);
        } else {
            System.out.println("Medicine not found in inventory: " + medicineName);
        }
    }
}