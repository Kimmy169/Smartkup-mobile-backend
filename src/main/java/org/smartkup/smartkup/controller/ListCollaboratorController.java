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

    @GetMapping("/list/{listId}")
    public List<ListCollaborator> getCollaboratorsForList(@PathVariable Long listId) {
        return repository.findByListId(listId);
    }

    @GetMapping("/user/{userId}")
    public List<ListCollaborator> getSharedListsForUser(@PathVariable Long userId) {
        return repository.findByUserId(userId);
    }

    @PostMapping
    public ListCollaborator addCollaborator(@RequestBody ListCollaborator collaborator) {
        return repository.save(collaborator);
    }
}