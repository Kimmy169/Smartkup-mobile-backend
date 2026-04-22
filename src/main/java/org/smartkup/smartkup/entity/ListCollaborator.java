package org.smartkup.smartkup.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ListCollaborators")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCollaborator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collaborator_Id;

    @Column(name = "list_id")
    private Long listId;

    @Column(name = "user_id")
    private Long userId;

    @Column(length = 20)
    private String role = "editor"; // Default role

    @Column(name = "added_at", insertable = false, updatable = false)
    private LocalDateTime addedAt;
}