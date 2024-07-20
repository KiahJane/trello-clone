package com.ericsson.trello_clone.controller.helper;

public class AvailablePaths {
    public static final String AUTH_LOGIN = "/api/auth/login";
    public static final String AUTH_REGISTER = "/api/auth/register";
    public static final String CHANGE_PASSWORD = "/api/auth/changePassword";


    public static final String ADMIN = "/api/admin/dashboard";
    public static final String ADMIN_USERS = "/api/admin/users";
    public static final String ADMIN_BOARDS = "/api/admin/boards";
    public static final String NEW_BOARD = "/api/admin/newBoard";
    public static final String BOARD = "/api/admin/board/{boardId}";


    public static final String USER = "/api/user/dashboard";
    public static final String USER_INFO = "/api/user/information";
    public static final String USER_BOARDS = "/api/user/boards";
    public static final String USER_BOARD = "/api/user/board";
    public static final String NEW_GROUP = "/api/user/newGroup";
    public static final String GROUP = "/api/user/group/{groupId}";
    public static final String NEW_CARD = "/api/user/newCard";
}

