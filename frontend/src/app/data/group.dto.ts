import { BoardDto } from "./board.dto";
import { UserDto } from "./user.dto";
import { CardDto } from "./card.dto";

export interface GroupDto {
    id: number;
    name: string;
    board: BoardDto;
    owner: UserDto;
    cards: CardDto[];
}
