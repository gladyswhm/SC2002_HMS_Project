package controller;

import java.util.List;

import entity.medicine;
import loader.Read;
import loader.Write;

public class InventoryCon {
    List<medicine> medicineList = Read.loadMedicineList("data/Medicine_List.csv");

    public List<medicine> getMedicineList() {
        return medicineList;
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
    
    public void updateStockLevel(String medicineName, int amount) {
        medicine medicine = findMedicineByName(medicineName);
        if (medicine != null) {
            // Update the stock level
            medicine.setStockLevel(medicine.getStockLevel() + amount);
            System.out.println("Stock level for " + medicineName + " updated to " + medicine.getStockLevel());
            Write.saveMedicineListToCSV(medicineList); 
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
    
}
