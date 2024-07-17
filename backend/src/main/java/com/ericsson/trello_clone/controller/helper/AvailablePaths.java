package com.ericsson.trello_clone.controller.helper;

public class AvailablePaths {
    public static final String REGISTER = "/api/auth/register";
    public static final String AUTH_LOG_IN = "/api/auth/logIn";
    public static final String AUTH_SIGN_IN = "/api/auth/signIn";

    public static final String ADMIN_LIST_OF_ALL_USERS = "/api/admin/user";
    public static final String ADMIN_ROLES = "/api/admin/roles";

    public static final String USER_MY_INFORMATION = "/api/user/information";
    public static final String USER_BOARD = "/api/user/board";
    public static final String USER_BOARDS = "/api/user/boards";

    public static final String NEW_BOARD = "/api/admin/newBoard";
    public static final String BOARD = "/api/admin/board/{boardId}";
    public static final String ALL_BOARDS = "/api/admin/boards";
    public static final String NEW_GROUP = "/api/user/newGroup";
    public static final String NEW_CARD = "/api/user/newCard";
    public static final String GROUP = "/api/user/group/{groupId}";
}

