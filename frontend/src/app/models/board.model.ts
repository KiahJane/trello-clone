import { User } from './user.model';
import { Group } from './group.model';

export class Board {
  constructor(
    public id: number,
    public name: string,
    public owner: User,
    public groups: Group[],
    public users: User[]
  ) {}
}
