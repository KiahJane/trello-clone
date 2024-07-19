export default class RegisterModel {
    firstName: string;
    lastName: string;
    username: string;
    email: string;
    password: string;
  
  
    constructor(firstName: string, lastName: string, username: string, email: string, password: string) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.username = username;
      this.email = email;
      this.password = password;
    }
  }
  
  export function getBlank() {
    return new RegisterModel("", "", "", "", "");
  }
    