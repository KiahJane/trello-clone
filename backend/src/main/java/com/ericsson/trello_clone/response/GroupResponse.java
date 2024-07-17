package com.ericsson.trello_clone.response;

import com.ericsson.trello_clone.dto.GroupDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {
    @JsonProperty("groups")
    private List<GroupDto> groups;

    public static GroupResponse build(List<GroupDto> groups) {
        return new GroupResponse(Objects.requireNonNullElseGet(groups, ArrayList::new));
    }

    public GroupResponse groups(List<GroupDto> groups) {
        if (this.groups == null) {
            this.groups = new ArrayList<>();
        }
        this.groups.addAll(groups);
        return this;
    }
}
