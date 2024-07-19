import { Board } from "./board.model";

export interface UserDto {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  fullName: string;
  email: string;
  role: string;
  boards: Board[];
}
