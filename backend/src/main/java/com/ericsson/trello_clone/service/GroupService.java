package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.domain.Group;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    Group create(GroupDto groupDto, User owner);

    Group update(GroupDto groupDto, User owner);

    List<Group> getAllByBoardId(User owner, Long id);

    Group getById(Long id);
}
