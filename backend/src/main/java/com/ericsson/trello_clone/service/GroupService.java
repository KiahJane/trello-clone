package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.model.Group;
import com.ericsson.trello_clone.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }
}
