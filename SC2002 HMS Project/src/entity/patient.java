package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import enum_class.Gender;
import enum_class.BloodType;

public class patient extends user {

    private BloodType blood;
    private String email;
    private String phonenumber;
    private Date dob;

    public patient(String UserID, String password, String name, Date dob, Gender gender, BloodType blood, String email, String phonenumber) {
        super(UserID, password, name, gender);
        this.dob = dob;
        this.blood = blood;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // Getter methods
    public String getUserID() {
        return super.getUserID();  
    }

    public String getPassword() {
        return super.getPassword();  
    }

    public String getName() {
        return super.getName();  
    }

    public Gender getGender() {
        return super.getGender(); 
    }

    public BloodType getBlood() {
        return blood;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Date getDob() { 
        return dob;
    }

    public String getDobAsFormattedString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return (dob != null) ? sdf.format(dob) : "N/A";
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPhonenumber(String phonenumber){
        this.phonenumber=phonenumber;
    }

    @Override
     public String toString() {
        return "Patient [userID=" + getUserID() + ", name=" + getName() + ", gender=" + getGender() 
                + ", bloodType=" + getBlood() + ", email=" + email + ", phone=" + phonenumber 
                + ", dateOfBirth=" + getDobAsFormattedString() + "]"; 
    }
}

