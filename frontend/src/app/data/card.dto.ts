import { GroupDto } from "./group.dto";

export interface CardDto {
    id: number;
    text: string;
    group: GroupDto;
}