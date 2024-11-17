package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import entity.appointment;
import entity.medicine;
import entity.staff;
import enum_class.*;
import loader.Read;
import loader.Write;

public class AdminCon implements StaffCon{
    List<staff> staffList = Read.loadStaffList("data/Staff_List.csv");

    public void addStaff(String staffId, String password, String name, Gender gender, Role role, int age) {
    staff newStaff = new staff(staffId, password, name, gender, role, age);
    staffList.add(newStaff);
    Write.saveStaffList(staffList);
    }

    public boolean removeStaff(String staffId) {
        boolean removed = staffList.removeIf(user -> user.getUserID().equalsIgnoreCase(staffId));
        if (removed) {
            Write.saveStaffList(staffList);
        }
        return removed;
    }

    public void updateStaff(String staffId, String newStaffId, String newName, Gender newGender, Role newRole, Integer newAge) {
        for (staff staff : staffList) {
            if (staff.getUserID().equals(staffId)) {
                if (newStaffId != null) {
                    staff.setUserID(newStaffId);  
                }
                if (newName != null) {
                    staff.setName(newName);  
                }
                if (newRole != null) {
                    staff.setRole(newRole);
                }
                if (newGender != null) {
                    staff.setGender(newGender);
                }
                if (newAge != null) {
                    staff.setAge(newAge);  
                }
                Write.saveStaffList(staffList);  
                System.out.println("Updated: " + staff);
                return;
            }
        }
        System.out.println("Staff member not found.");
    }



    public void displayStaff(String filterType, String value) {
        System.out.println("Staff List:");
        boolean printed = false;  // To track if any staff was printed after filtering
        for (staff staff : staffList) {
            boolean matches = true;
    
            // Handle role filter
            if (filterType.equalsIgnoreCase("role")) {
                try {
                    Role role = Role.valueOf(value);  // Convert string to Role enum
                    if (!staff.getRole().equals(role)) {
                        matches = false;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid role value: " + value);
                    matches = false;
                }
            }
    
            // Handle gender filter
            if (filterType.equalsIgnoreCase("gender")) {
                try {
                    Gender gender = Gender.valueOf(value);  // Convert string to Gender enum
                    if (!staff.getGender().equals(gender)) {
                        matches = false;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid gender value: " + value);
                    matches = false;
                }
            }
    
            // Handle age filter
            if (filterType.equalsIgnoreCase("age")) {
                try {
                    int age = Integer.parseInt(value);  // Parse age from string to integer
                    if (staff.getAge() != age) {
                        matches = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age value: " + value);
                    matches = false;
                }
            }
    
            // If all conditions match, print the staff member
            if (matches) {
                System.out.println(staff);
                printed = true;  // Indicate that at least one staff was printed
            }
        }
    
        if (!printed) {
            System.out.println("No staff members found with the given filter criteria.");
        }
    }
    
    


    
    
    


}