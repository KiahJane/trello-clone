package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.domain.Group;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.GroupDto;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    Group create(GroupDto groupDto);

    Group update(GroupDto groupDto);

    void delete(User user, Long groupId);
}
