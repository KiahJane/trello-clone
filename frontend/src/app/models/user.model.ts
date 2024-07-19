import { Board } from "./board.model";

export class User {
  constructor(
    public id: number,
    public username: string,
    public firstName: string,
    public lastName: string,
    public fullName: string,
    public email: string,
    public role: string,
    public boards: Board[]
  ) {}
}

