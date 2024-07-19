import { Board } from './board.model';
import { Card } from './card.model';
import { User } from './user.model';

export interface Group {
  id: number;
  name: string;
  board: Board;
  owner: User;
  cards: Card[];
}
