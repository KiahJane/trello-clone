import { User } from './user.model';
import { Group } from './group.model';

export interface Board {
  id: number;
  name: string;
  owner: User;
  groups: Group[];
  users: User[];
}
