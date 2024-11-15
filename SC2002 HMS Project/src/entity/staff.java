package entity;

import enum_class.*;

public class staff extends user{
    private Role role;
    private int age;

    public staff(String UserID, String password, String name, Gender gender, Role role, int age){
        super(UserID, password, name, gender);
        this.role = role;
        this.age = age;
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Override
    public String toString() {
        return "Staff [staffId=" + getUserID() + ", password=" + getPassword() + ", name=" + getName() + ", gender=" + getGender() + ", role=" + role + ", age=" + age + "]";
    }
}