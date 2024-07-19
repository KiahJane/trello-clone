import { BoardDto } from "./board.dto";

export class FullUserDto {
    constructor(    
        public id: number,
        public username: string,
        public firstName: string,
        public lastName: string,
        public fullName: string,
        public email: string,
        public role: string,
        public boards: BoardDto[]
    ) {}
}
  