package io.github.codeblooded.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testUserRoleIsStoredCorrectly() {
        User user = new User(User.Role.ADMIN);
        assertEquals(User.Role.ADMIN, user.getRole());
    }

    @Test
    void testIsAdminReturnsTrueForAdmin() {
        User user = new User(User.Role.ADMIN);
        assertTrue(user.isAdmin());
    }

    @Test
    void testIsAdminReturnsFalseForGuest() {
        User user = new User(User.Role.GUEST);
        assertFalse(user.isAdmin());
    }
}
