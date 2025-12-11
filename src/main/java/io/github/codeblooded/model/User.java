package io.github.codeblooded.model;

public class User {
  public enum Role {
    ADMIN,
    GUEST
  }

  private final Role role;

  public User(Role role) {
    this.role = role;
  }

  public Role getRole() {
    return role;
  }

  public boolean isAdmin() {
    return this.role == Role.ADMIN;
  }
}
