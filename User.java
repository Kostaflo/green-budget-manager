
/**
 * Data Model για την αποθήκευση στοιχείων χρήστη και ρόλου (ADMIN/GUEST).
 * Αντιστοιχεί στον πίνακα Users της SQLite.
 */
public class User {

    private int id;
    private String username;
    private String role; // ADMIN ή READONLY (GUEST)

    public User() {
        // Default constructor
    }

    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}