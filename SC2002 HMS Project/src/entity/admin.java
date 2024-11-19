package entity;

import enum_class.*;


 /**
 * The class Admin extends staff
 */ 
public class admin extends staff {

/** 
 *
 * Admin
 *
 * @param UserID  the user identifier. 
 * @param password  the password. 
 * @param name  the name. 
 * @param gender  the gender. 
 * @param role  the role. 
 * @param age  the age. 
 * @return public
 */
    public admin(String UserID, String password, String name, Gender gender, Role role, int age){ 

        super(UserID, password, name, gender, role, age);
    }
}
