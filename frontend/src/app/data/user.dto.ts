import { BoardDto } from "./board.dto";

export interface UserDto {
    id: number;
    username: string;
    password: string;
    email: string;
    role: string;
    approvedByAdmin: boolean;
    isExpired: boolean;
    isLocked: boolean;
    isActive: boolean;
    boards: BoardDto[];
}