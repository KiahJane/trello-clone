import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Board } from '../models/board.model'
import { ApplicationRoutes } from '../app-main-rules/routes.enum';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class BoardService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  getAllBoards(): Observable<Board[]> {
    return this.http.get<Board[]>(`${this.configService.baseApiUrl}${ApplicationRoutes.ADMIN_BOARDS}`);
  }

  getBoardById(boardId: string): Observable<Board> {
    return this.http.get<Board>(`${this.configService.baseApiUrl}/boards/${boardId}`);
  }
}
