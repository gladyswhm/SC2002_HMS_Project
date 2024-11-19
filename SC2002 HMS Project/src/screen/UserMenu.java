package screen;

import java.util.List;
import entity.staff;

/**
 * The UserMenu interface defines the common structure for user menus in the application.
 * It is implemented by different classes such as DoctorMenu, PatientMenu, and PharmacistMenu
 * to display the specific menus and handle user actions based on their roles.
 */
public interface UserMenu {

    /**
     * Displays the menu specific to the user and handles the user input to perform relevant actions.
     *
     * @param userID The unique identifier of the logged-in user.
     * @param lines The list of all staff members or other relevant entities, depending on the user role.
     */
    void showMenu(String userID, List<staff> lines);
}
