package com.ericsson.trello_clone.service.impl;

import com.ericsson.trello_clone.domain.Group;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.GroupDto;
import com.ericsson.trello_clone.exceptions.EntityNotFoundInDatabaseException;
import com.ericsson.trello_clone.repository.GroupRepository;
import com.ericsson.trello_clone.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;

    public Group getEntityFromDto(GroupDto groupDto) {
        Optional<Group> optionalGroup = repository.findById(groupDto.getId());

        return optionalGroup
                .orElseThrow(() -> new EntityNotFoundInDatabaseException("Group not found."));
    }
    @Override
    public Group create(GroupDto groupDto) {
        Group group = new Group();
        group.updateEntity(groupDto);
        return repository.save(group);
    }

    @Override
    public Group update(GroupDto groupDto) {
        Group group = getEntityFromDto(groupDto);
        group.updateEntity(groupDto);
        return repository.save(group);
    }

    public void delete(User user, Long groupId) {
        Group group = repository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundInDatabaseException("Group not found in database."));
        repository.delete(group);
    }
}
