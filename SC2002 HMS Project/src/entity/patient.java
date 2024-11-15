package entity;

import enum_class.*;

public class patient extends user {

    private BloodType blood;
    private String email;
    private String phonenumber;

    public patient(String UserID, String password, String name, Gender gender, BloodType blood, String email, String phonenumber){
        super(UserID, password, name, gender);
        this.blood = blood;
        this.email = email;
        this.phonenumber = phonenumber;
    }
}