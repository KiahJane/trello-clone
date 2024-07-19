import { Group } from './group.model';

export class Card {
  constructor(
    public id: number,
    public text: string,
    public group: Group
  ) {}
}