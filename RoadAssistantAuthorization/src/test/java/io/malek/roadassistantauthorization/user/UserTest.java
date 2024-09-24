package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.Email;
import io.malek.roadassistantauthorization.user.enums.RoleType;
import io.malek.roadassistantauthorization.user.register.Role;
import io.malek.roadassistantauthorization.user.register.User;
import io.malek.roadassistantauthorization.user.register.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldAssignUserRole() {
        User user = new User();
        Role role = new Role();
        role.setRoleType(RoleType.USER);
        UserRole userRole = UserRole.builder().role(role).user(user).build();
        UserRole[] userRoles = new UserRole[] {userRole};

        user.assignRoles(userRoles);

        assertEquals(1, user.getRoles().size());
    }

    @Test
    void shouldChangeUserEmail() {
        String rawEmail = "user@gmail.com";
        Email email = Email.of(rawEmail);
        User user = User.builder().email(email).build();

        user.changeEmail(rawEmail,"updatedEmail@gmail.com");

        assertEquals("updatedEmail@gmail.com", user.getEmail().value());
    }

    @Test
    void shouldNotUpdateEmailWhenUserProvideWrongOldEmail() {
        String rawEmail = "user@gmail.com";
        Email email = Email.of(rawEmail);
        User user = User.builder().email(email).build();

        assertThrows(IllegalArgumentException.class, () -> user.changeEmail("wrong@gmail.com", "updatedEmail@gmail.com"));
    }
}