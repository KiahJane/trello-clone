export default class PasswordChangeModel {
    email: string;
    password: string;
  
    constructor(email: string, password: string) {
      this.email = email;
      this.password = password;
    }
  }
  
  export function getBlank() {
    return new PasswordChangeModel("", "");
  }
    