export default class LoginModel {
    username: string;
    password: string;
  
    constructor(usernameOrEmail: string, password: string) {
      this.username = usernameOrEmail;
      this.password = password;
    }
  }
  
  export function getBlank() {
    return new LoginModel("", "");
  }
  