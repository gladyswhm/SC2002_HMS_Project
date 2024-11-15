package entity;

import enum_class.*;

public class doctor extends staff {
    public doctor(String UserID, String password, String name, Gender gender, Role role, int age){
        super(UserID, password, name, gender, role, age);
    }
}