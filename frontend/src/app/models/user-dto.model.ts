import { Board } from './board.model';

export class UserDto {
  constructor(
    public id: number,
    public username: string,
    public password: string,
    public email: string,
    public role: string,
    public approvedByAdmin: boolean,
    public isExpired: boolean,
    public isLocked: boolean,
    public isActive: boolean,
    public boards: Board[]
  ) {}
}