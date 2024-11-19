package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import enum_class.Gender;
import enum_class.BloodType;

/**
 * Represents a patient entity that extends the {@link user} class.
 * Stores additional details such as date of birth, blood type, email, and phone number.
 */
public class patient extends user {

    private BloodType blood;          // Patient's blood type
    private String email;             // Patient's email address
    private String phonenumber;       // Patient's phone number
    private Date dob;                 // Patient's date of birth

    /**
     * Constructs a new patient object with specified attributes.
     *
     * @param UserID       the patient's user ID
     * @param password     the patient's password
     * @param name         the patient's name
     * @param dob          the patient's date of birth
     * @param gender       the patient's gender
     * @param blood        the patient's blood type
     * @param email        the patient's email address
     * @param phonenumber  the patient's phone number
     */
    public patient(String UserID, String password, String name, Date dob, Gender gender, BloodType blood, String email, String phonenumber) {
        super(UserID, password, name, gender);
        this.dob = dob;
        this.blood = blood;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // Getter methods

    /**
     * Gets the user's ID.
     *
     * @return the user's ID
     */
    public String getUserID() {
        return super.getUserID();  
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return super.getPassword();  
    }

    /**
     * Gets the patient's name.
     *
     * @return the patient's name
     */
    public String getName() {
        return super.getName();  
    }

    /**
     * Gets the patient's gender.
     *
     * @return the patient's gender
     */
    public Gender getGender() {
        return super.getGender(); 
    }

    /**
     * Gets the patient's blood type.
     *
     * @return the patient's blood type
     */
    public BloodType getBlood() {
        return blood;
    }

    /**
     * Gets the patient's email address.
     *
     * @return the patient's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the patient's phone number.
     *
     * @return the patient's phone number
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * Gets the patient's date of birth.
     *
     * @return the patient's date of birth
     */
    public Date getDob() { 
        return dob;
    }

    /**
     * Gets the patient's date of birth as a formatted string (yyyy/MM/dd).
     *
     * @return the formatted date of birth, or "N/A" if the date is null
     */
    public String getDobAsFormattedString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return (dob != null) ? sdf.format(dob) : "N/A";
    }

    // Setter methods

    /**
     * Updates the patient's email address.
     *
     * @param email the new email address
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Updates the patient's phone number.
     *
     * @param phonenumber the new phone number
     */
    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    /**
     * Returns a string representation of the patient object.
     *
     * @return a string containing the patient's details
     */
    @Override
    public String toString() {
        return "Patient [userID=" + getUserID() + ", name=" + getName() + ", gender=" + getGender() 
                + ", bloodType=" + getBlood() + ", email=" + email + ", phone=" + phonenumber 
                + ", dateOfBirth=" + getDobAsFormattedString() + "]"; 
    }
}
