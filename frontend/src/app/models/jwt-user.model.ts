export class JwtUser {
  constructor(
    public accessToken: string,
    public id: number,
    public name: string,
    public email: string,
    public role: string,
    public isGDPR: boolean,
    public isRegulation: boolean,
    public hasInitialQuestioner: boolean
  ) {}
}

  