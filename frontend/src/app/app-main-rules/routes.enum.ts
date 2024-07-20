export enum ApplicationRoutes {
  BASE_API_URL = 'https://localhost:3000',
  APP_MAIN = 'trello-clone',
  UNAPPROVED_USER = 'webpage',
  ADMIN = 'admin/dashboard',
  ADMIN_USERS = 'admin/users',
  ADMIN_BOARDS = 'admin/boards',
  ADMIN_ROLES = 'admin/roles',
  USER = 'dashboard',
  USER_INFO = 'my-information',
}

export enum BackendRoutes {
  AUTH_LOGIN = '/api/auth/login',
  AUTH_REGISTER = '/api/auth/register',
  CHANGE_PASSWORD = 'api/auth/changePassword',
  ADMIN = '/api/admin/dashboard',
  ADMIN_USERS = '/api/admin/users',
  ADMIN_BOARDS = '/api/admin/boards',
  NEW_BOARD = '/api/admin/newBoard',
  BOARD = '/api/admin/board/{boardId}',
  USER = '/api/user/dashboard',
  USER_INFO = '/api/user/information',
  USER_BOARDS = '/api/user/boards',
  USER_BOARD = '/api/user/board',
  NEW_GROUP = '/api/user/newGroup',
  GROUP = '/api/user/group/{groupId}',
  NEW_CARD = '/api/user/newCard',
  CARD = '/api/user/card/{cardId}'
}


