package org.smartkup.smartkup.repository;

import org.smartkup.smartkup.entity.ListCollaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListCollaboratorRepository extends JpaRepository<ListCollaborator, Long> {
    // See everyone who has access to a specific list
    List<ListCollaborator> findByListId(Long listId);

    // See all the lists a specific user has been invited to
    List<ListCollaborator> findByUserId(Long userId);
}