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

export interface BoardDto {
    id: number;
    name: string;
    owner: UserDto;
    groups: GroupDto[];
    users: UserDto[];
}

export interface GroupDto {
    id: number;
    name: string;
    board: BoardDto;
    owner: UserDto;
    cards: CardDto[];
}

export interface CardDto {
    id: number;
    text: string;
    group: GroupDto;
}