package controller;

import enum_class.Gender;
import enum_class.Role;

public interface StaffCon {

    void addStaff(String staffId, String password, String name, Gender gender, Role role, int age);

    boolean removeStaff(String staffId);

    void updateStaff(String staffId, String newStaffId, String newName, Gender newGender, Role newRole, Integer newAge);

    void displayStaff(String filterType, String value);

}
