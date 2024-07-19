import { UserDto } from "./user.dto";
import { GroupDto } from "./group.dto";

export interface BoardDto {
    id: number;
    name: string;
    owner: UserDto;
    groups: GroupDto[];
    users: UserDto[];
}