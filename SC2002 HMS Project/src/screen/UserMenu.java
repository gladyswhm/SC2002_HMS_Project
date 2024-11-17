package screen;

import java.util.List;
import entity.staff;

public interface UserMenu {
    void showMenu(String userID, List<staff> lines);
}