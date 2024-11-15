package entity;

import enum_class.*;

public abstract class user{
    private String UserID;
    private String password;
    private String name;
    private Gender gender;

    public user(String UserID, String password, String name, Gender gender){
        this.UserID = UserID;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public String getUserID(){
        return this.UserID;
    }

    public void setUserID(String UserID){
        this.UserID = UserID;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public Gender getGender(){
        return this.gender;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }
}