package entity;

import enum_class.*;

/**
 * Represents a staff member in the system, extending the `user` class.
 * This class includes details about the staff's role and age, in addition to the user information.
 */
public class staff extends user {

    private Role role;   // The role of the staff member (e.g., doctor, nurse, pharmacist)
    private int age;     // The age of the staff member

    /**
     * Constructs a staff object with the specified user details, role, and age.
     *
     * @param UserID    the unique user ID of the staff member
     * @param password  the password for the staff member's account
     * @param name      the name of the staff member
     * @param gender    the gender of the staff member
     * @param role      the role of the staff member (e.g., doctor, pharmacist)
     * @param age       the age of the staff member
     */
    public staff(String UserID, String password, String name, Gender gender, Role role, int age) {
        super(UserID, password, name, gender);
        this.role = role;
        this.age = age;
    }

    /**
     * Gets the role of the staff member.
     *
     * @return the role of the staff member
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Sets the role of the staff member.
     *
     * @param role the new role for the staff member
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the age of the staff member.
     *
     * @return the age of the staff member
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the age of the staff member.
     *
     * @param age the new age for the staff member
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns a string representation of the staff member, including user details, role, and age.
     *
     * @return a string representation of the staff member
     */
    @Override
    public String toString() {
        return "[staffId=" + getUserID() + ", password=" + getPassword() + ", name=" + getName() 
               + ", gender=" + getGender() + ", role=" + role + ", age=" + age + "]";
    }
}
