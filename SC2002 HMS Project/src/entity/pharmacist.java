package entity;

import enum_class.*;

/**
 * Represents a pharmacist entity that extends the {@link staff} class.
 * This class inherits all attributes and methods from the {@code staff} class
 * and is tailored specifically for the pharmacist role within the system.
 */
public class pharmacist extends staff {

    /**
     * Constructs a new pharmacist object with the specified attributes.
     *
     * @param UserID   the pharmacist's unique user ID
     * @param password the pharmacist's account password
     * @param name     the pharmacist's name
     * @param gender   the pharmacist's gender
     * @param role     the pharmacist's role within the system (e.g., PHARMACIST)
     * @param age      the pharmacist's age
     */
    public pharmacist(String UserID, String password, String name, Gender gender, Role role, int age) {
        super(UserID, password, name, gender, role, age);
    }
}
