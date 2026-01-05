package io.github.codeblooded.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.codeblooded.model.User.Role;
import io.github.codeblooded.service.UserService;

class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = UserService.getInstance();
        service.logout(); // reset λόγω singleton
    }

    @Test
    void login_whenRoleIsNull_returnsFalse() {
        assertFalse(service.login(null));
    }

    @Test
    void login_whenRoleIsAdmin_setsCurrentUserAndReturnsTrue() {
        assertTrue(service.login(Role.ADMIN));
        assertEquals(Role.ADMIN, service.getCurrentUser().getRole());
    }

    @Test
    void setMode_whenNewRoleIsNull_doesNothing() {
        service.login(Role.GUEST);

        service.setMode(null);

        assertEquals(Role.GUEST, service.getCurrentUser().getRole());
    }

    @Test
    void setMode_whenNewRoleIsAdmin_changesUserRole() {
        service.login(Role.GUEST);

        service.setMode(Role.ADMIN);

        assertEquals(Role.ADMIN, service.getCurrentUser().getRole());
    }

    @Test
    void getCurrentUser_whenNoUserLoggedIn_returnsGuestUser() {
        assertEquals(Role.GUEST, service.getCurrentUser().getRole());
    }

    @Test
    void isAdmin_whenNoUserLoggedIn_returnsFalse() {
        assertFalse(service.isAdmin());
    }

    @Test
    void isAdmin_whenAdminLoggedIn_returnsTrue() {
        service.login(Role.ADMIN);
        assertTrue(service.isAdmin());
    }
}
