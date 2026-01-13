package io.github.codeblooded.service;

import io.github.codeblooded.model.User;
import io.github.codeblooded.model.User.Role;

/**
 * Υπηρεσία διαχείρισης χρηστών που ακολουθεί το πρότυπο σχεδίασης **Singleton**.
 *
 * <p>Η κλάση αυτή διασφαλίζει ότι υπάρχει μόνο ένα αντικείμενο διαχείρισης σε όλη την εφαρμογή.
 * Κρατάει στη μνήμη τον τρέχοντα συνδεδεμένο χρήστη ({@link User}), επιτρέποντας στους Controllers
 * να γνωρίζουν αν ο χρήστης είναι Admin ή Guest.
 *
 * @author CodeBlooded Team
 * @version 1.0
 */
public class UserService {

  /** Ο τρέχων συνδεδεμένος χρήστης. */
  private User currentUser;

  /** Το μοναδικό στατικό στιγμιότυπο (instance) της κλάσης. */
  private static UserService instance;

  /**
   * Ιδιωτικός κατασκευαστής για να αποτραπεί η δημιουργία αντικειμένων εκτός της κλάσης (Singleton
   * Pattern).
   */
  private UserService() {}

  /**
   * Επιστρέφει το μοναδικό στιγμιότυπο (Instance) της υπηρεσίας.
   *
   * <p>Αν το στιγμιότυπο δεν υπάρχει, το δημιουργεί (Lazy Initialization).
   *
   * @return Το instance του UserService.
   */
  public static UserService getInstance() {
    if (instance == null) {
      instance = new UserService();
    }
    return instance;
  }

  /**
   * Αποσυνδέει τον χρήστη από το σύστημα.
   *
   * <p>Θέτει τον τρέχοντα χρήστη σε {@code null}.
   */
  public void logout() {
    this.currentUser = null;
  }

  /**
   * Συνδέει έναν νέο χρήστη με τον καθορισμένο ρόλο.
   *
   * @param role Ο ρόλος που θα ανατεθεί (ADMIN ή GUEST).
   * @return {@code true} αν η σύνδεση ήταν επιτυχής (το role δεν ήταν null), αλλιώς {@code false}.
   */
  public boolean login(Role role) {
    if (role == null) {
      return false;
    }
    this.currentUser = new User(role);
    return true;
  }

  /**
   * Αλλάζει τον ρόλο του τρέχοντος χρήστη (Mode Switching).
   *
   * @param newRole Ο νέος ρόλος.
   */
  public void setMode(Role newRole) {
    if (newRole != null) {
      this.currentUser = new User(newRole);
    }
  }

  /**
   * Επιστρέφει τον τρέχοντα χρήστη.
   *
   * <p>Αν δεν υπάρχει συνδεδεμένος χρήστης, επιστρέφει έναν χρήστη με ρόλο {@link Role#GUEST} ως
   * ασφαλής προεπιλογή (fail-safe).
   *
   * @return Ο χρήστης (User object).
   */
  public User getCurrentUser() {
    if (currentUser == null) {
      return new User(Role.GUEST);
    }
    return currentUser;
  }

  /**
   * Ελέγχει αν ο τρέχων χρήστης έχει δικαιώματα διαχειριστή.
   *
   * @return {@code true} αν ο χρήστης υπάρχει και είναι ADMIN, αλλιώς {@code false}.
   */
  public Boolean isAdmin() {
    return currentUser != null && currentUser.getRole() == Role.ADMIN;
  }
}
