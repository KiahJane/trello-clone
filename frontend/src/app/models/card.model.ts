import { Group } from './group.model';

export interface Card {
  id: number;
  text: string;
  group: Group;
}
