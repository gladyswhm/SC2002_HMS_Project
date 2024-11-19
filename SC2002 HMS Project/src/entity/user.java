package entity;

import enum_class.*;

/**
 * Represents a user in the system with basic information like ID, password, name, and gender.
 * This class serves as the base class for other entities such as staff and patients.
 */
public abstract class user {

    private String UserID;   // Unique identifier for the user
    private String password; // Password for the user's account
    private String name;     // Name of the user
    private Gender gender;   // Gender of the user

    /**
     * Constructs a user object with the specified user details.
     *
     * @param UserID   the unique user ID of the user
     * @param password the password for the user's account
     * @param name     the name of the user
     * @param gender   the gender of the user
     */
    public user(String UserID, String password, String name, Gender gender) {
        this.UserID = UserID;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    /**
     * Gets the user ID of the user.
     *
     * @return the unique user ID of the user
     */
    public String getUserID() {
        return this.UserID;
    }

    /**
     * Sets the user ID of the user.
     *
     * @param UserID the new user ID for the user
     */
    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password for the user's account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password for the user's account
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name for the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the user.
     *
     * @return the gender of the user
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the new gender for the user
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
