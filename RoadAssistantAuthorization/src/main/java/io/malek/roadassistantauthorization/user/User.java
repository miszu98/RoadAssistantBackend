package io.malek.roadassistantauthorization.user;

import io.malek.roadassistantauthorization.user.dtos.Email;
import io.malek.roadassistantauthorization.user.dtos.FirstName;
import io.malek.roadassistantauthorization.user.dtos.HashPassword;
import io.malek.roadassistantauthorization.user.dtos.LastName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email"))
    private Email email;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private HashPassword password;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "first_name"))
    private FirstName firstName;

    @NotNull
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

}
