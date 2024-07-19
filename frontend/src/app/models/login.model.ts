export default class LoginModel {
    usernameOrEmail: string;
    password: string;
  
    constructor(usernameOrEmail: string, password: string) {
      this.usernameOrEmail = usernameOrEmail;
      this.password = password;
    }
  }
  
  export function getBlank() {
    return new LoginModel("", "");
  }
  