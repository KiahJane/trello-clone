export enum ApplicationRoutes {
  BASE_API_URL = 'https://localhost:3000',
  APP_MAIN = 'trello-clone',
  UNAPPROVED_USER = 'webpage',
  LOGIN = 'login',
  REGISTER = 'register',
  ADMIN = 'admin',
  ADMIN_USERS = 'admin/users',
  ADMIN_BOARDS = 'admin/boards',
  USER = 'user',
  USER_INFO = 'user/my-information',
}

export enum BackendRoutes {
  AUTH_LOGIN = '/api/auth/login',
  AUTH_REGISTER = '/api/auth/register',
  CHANGE_PASSWORD = 'api/auth/changePassword',

  ADMIN = '/api/admin/dashboard',
  ADMIN_USERS = '/api/admin/users',
  NEW_USER = '/api/admin/users/newUser',
  ADMIN_USER = '/api/admin/users/{userId}',
  ADMIN_BOARDS = '/api/admin/boards',
  NEW_BOARD = '/api/admin/boards/newBoard',
  ADMIN_BOARD = '/api/admin/boards/{boardId}',

  USER = '/api/user/dashboard',
  USER_INFO = '/api/user/information',
  USER_BOARDS = '/api/user/boards',
  USER_BOARD = '/api/user/boards/{boardId}',
  NEW_GROUP = '/api/user/boards/{boardId}/newGroup',
  GROUP = '/api/user/boards/{boardId}/groups/{groupId}',
  NEW_CARD = '/api/user/boards/{boardId}/groups/{groupId}/newCard',
  CARD = '/api/user/boards/{boardId}/groups/{groupId}/cards/{cardId}'
}


