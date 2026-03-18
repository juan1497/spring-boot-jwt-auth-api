package com.juan1497.securityjwt.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_role_assignments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRoleAssignment {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    private LocalDateTime assignedAt;
    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private User assignedBy;
    private LocalDateTime revokedAt;
    @ManyToOne
    @JoinColumn(name = "revoked_by")
    private User revokedBy;
    private boolean active;
}
