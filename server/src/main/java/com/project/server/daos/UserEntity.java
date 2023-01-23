package com.project.server.daos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Roles",
            joinColumns =
                    @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();
}
