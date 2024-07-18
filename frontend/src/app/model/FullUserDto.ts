export class FullUserDto {
    public id: number;
    public username: string;
    public firstName: string;
    public lastName: string;
    public fullName: string;
    public email: string;
    public role: string;
    public boards: BoardDto[]; // TODO: fix to correct type -- Array / List
  }
  