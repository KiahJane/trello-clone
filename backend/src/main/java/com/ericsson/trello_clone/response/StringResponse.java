package com.ericsson.trello_clone.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StringResponse {
    @JsonProperty("value")
    private List<String> strings;

    public static StringResponse build(List<String> strings) {
        if (strings == null) {
            return new StringResponse();
        }
        return new StringResponse(strings);
    }
}
