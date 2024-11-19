package entity;

import enum_class.*;

/**
 * Represents a doctor, extending the {@link staff} class.
 * Inherits common staff attributes such as user ID, password, name, gender, role, and age.
 */
public class doctor extends staff {

    /**
     * Constructs a doctor object with the specified attributes.
     *
     * @param UserID   the unique identifier for the doctor
     * @param password the password for the doctor's account
     * @param name     the name of the doctor
     * @param gender   the gender of the doctor
     * @param role     the role of the doctor (e.g., Role.DOCTOR)
     * @param age      the age of the doctor
     */
    public doctor(String UserID, String password, String name, Gender gender, Role role, int age) {
        super(UserID, password, name, gender, role, age);
    }
}
