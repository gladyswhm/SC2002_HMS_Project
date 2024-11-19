package controller;

import enum_class.Gender;
import enum_class.Role;

/**
 * Interface representing staff management operations.
 * Provides methods to add, remove, update, and display staff details.
 */
public interface StaffCon {

    /**
     * Adds a new staff member to the system.
     *
     * @param staffId  the unique identifier for the staff member
     * @param password the password for the staff member's account
     * @param name     the name of the staff member
     * @param gender   the gender of the staff member
     * @param role     the role of the staff member
     * @param age      the age of the staff member
     */
    void addStaff(String staffId, String password, String name, Gender gender, Role role, int age);

    /**
     * Removes a staff member from the system.
     *
     * @param staffId the unique identifier of the staff member to be removed
     * @return {@code true} if the staff member was successfully removed; {@code false} otherwise
     */
    boolean removeStaff(String staffId);

    /**
     * Updates the details of an existing staff member.
     *
     * @param staffId   the unique identifier of the staff member to be updated
     * @param newStaffId the new unique identifier for the staff member
     * @param newName    the new name of the staff member
     * @param newGender  the new gender of the staff member
     * @param newRole    the new role of the staff member
     * @param newAge     the new age of the staff member
     */
    void updateStaff(String staffId, String newStaffId, String newName, Gender newGender, Role newRole, Integer newAge);

    /**
     * Displays staff members based on a specified filter.
     *
     * @param filterType the type of filter to be applied (e.g., "Role", "Gender", "Age")
     * @param value      the value to filter staff members by
     */
    void displayStaff(String filterType, String value);

}
