package io.github.codeblooded.service;

import io.github.codeblooded.model.User;
import io.github.codeblooded.model.User.Role;

public class UserService {
  private User currentUser;

  private static UserService instance;

  private UserService() {}

  public static UserService getInstance() {
    if (instance == null) {
      instance = new UserService();
    }
    return instance;
  }

  public void logout() {
    this.currentUser = null;
  }

  public boolean login(Role role) {
    if (role == null) {
      return false;
    }
    this.currentUser = new User(role);
    return true;
  }

  public void setMode(Role newRole) {
    if (newRole != null) {
      this.currentUser = new User(newRole);
    }
  }

  public User getCurrentUser() {
    if (currentUser == null) {
      return new User(Role.GUEST);
    }
    return currentUser;
  }

  public Boolean isAdmin() {
    return currentUser != null && currentUser.getRole() == Role.ADMIN;
  }
}
