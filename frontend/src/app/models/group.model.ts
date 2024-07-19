import { Board } from './board.model';
import { Card } from './card.model';
import { User } from './user.model';

export class Group {
  constructor(
    public id: number,
    public name: string,
    public board: Board,
    public owner: User,
    public cards: Card[]
  ) {}
}
