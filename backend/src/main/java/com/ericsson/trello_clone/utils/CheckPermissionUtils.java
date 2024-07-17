package com.ericsson.trello_clone.utils;

import com.ericsson.trello_clone.config.ApplicationRoles;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.exceptions.PermissionException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CheckPermissionUtils {

    public static void checkUserPermission(User user) {
        if (!(user.getRole().equals(ApplicationRoles.USER.getDatabaseName()))) {
            throw new PermissionException();
        }
    }

    public static void checkAdminPermission(User user) {
        if (!(user.getRole().equals(ApplicationRoles.ADMIN.getDatabaseName()))) {
            throw new PermissionException();
        }
    }
}
