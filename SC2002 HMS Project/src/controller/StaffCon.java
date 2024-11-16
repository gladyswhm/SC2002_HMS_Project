package controller;

import enum_class.Gender;
import enum_class.Role;

public abstract class StaffCon {

    // Abstract methods that must be implemented by subclasses
    public abstract void addStaff(String staffId, String password, String name, Gender gender, Role role, int age);

    public abstract boolean removeStaff(String staffId);

    public abstract void updateStaff(String staffId, String newStaffId, String newName, Gender newGender, Role newRole, Integer newAge);

    public abstract void displayStaff(String filterType, String value);

}