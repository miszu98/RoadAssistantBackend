package io.malek.roadassistantauthorization;

import io.malek.Email;
import io.malek.FirstName;
import io.malek.LastName;
import io.malek.Password;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> roles;

}
