package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.model.Group;
import com.ericsson.trello_clone.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping(AvailablePaths.NEW_GROUP)
    public Group createGroup(@RequestBody Group group) {
        return groupService.save(group);
    }
}
