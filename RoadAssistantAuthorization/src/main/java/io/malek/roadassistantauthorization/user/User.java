package io.malek.roadassistantauthorization.user;

import io.malek.Email;
import io.malek.FirstName;
import io.malek.LastName;
import io.malek.Password;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private Password password;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "first_name"))
    private FirstName firstName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "last_name"))
    private LastName lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<UserRole> roles;

    public void assignRoles(UserRole... userRoles) {
        if (isNull(roles)) {
            roles = new HashSet<>();
        }
        roles.addAll(Arrays.asList(userRoles));
    }

    public void changeEmail(String oldEmail, String newEmail) {
        if (email.equals(Email.of(oldEmail))) {
            email = Email.of(newEmail);
            return;
        }
        throw new IllegalArgumentException("You have to provide correct current email to set new email");
    }

    public void changePassword(String oldPassword, String newPassword) {
        boolean currentPasswordIsEqualWithOldPassword = password.equals(Password.of(oldPassword));
        if (currentPasswordIsEqualWithOldPassword) {
            password = Password.of(newPassword);
            return;
        }
        throw new IllegalArgumentException("You have to provide correct current password to set new password");
    }

}
