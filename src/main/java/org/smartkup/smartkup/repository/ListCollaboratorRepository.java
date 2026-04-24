package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.ListCollaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListCollaboratorRepository extends JpaRepository<ListCollaborator, Long> {
    List<ListCollaborator> findByListId(Long listId);

    List<ListCollaborator> findByUserId(Long userId);
}