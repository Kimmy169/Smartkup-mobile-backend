package org.smartkup.smartkup.controller;

import org.smartkup.smartkup.entity.ListCollaborator;
import org.smartkup.smartkup.repository.ListCollaboratorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborators")
public class ListCollaboratorController {

    private final ListCollaboratorRepository repository;

    public ListCollaboratorController(ListCollaboratorRepository repository) {
        this.repository = repository;
    }

    // See who is collaborating on a list
    @GetMapping("/list/{listId}")
    public List<ListCollaborator> getCollaboratorsForList(@PathVariable Long listId) {
        return repository.findByListId(listId);
    }

    // See what lists a user is collaborating on
    @GetMapping("/user/{userId}")
    public List<ListCollaborator> getSharedListsForUser(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    // Invite a new user to a list
    @PostMapping
    public ListCollaborator addCollaborator(@RequestBody ListCollaborator collaborator) {
        return repository.save(collaborator);
    }
}