import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Board } from '../models/board.model';
import { ConfigService } from './config.service';
import { BackendRoutes } from '../app-main-rules/routes.enum';

@Injectable({
  providedIn: 'root'
})
export class BoardService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  createBoardAdmin(board: Board): Observable<Board> {
    return this.http.post<Board>(`${this.configService.baseApiUrl}${BackendRoutes.NEW_BOARD}`, board);
  }

  updateBoardUser(boardId: string, board: Board): Observable<Board> {
    return this.http.put<Board>(`${this.configService.baseApiUrl}${BackendRoutes.USER_BOARD.replace('{boardId}', boardId)}`, board);
  }

  addUserToBoard(boardId: string, board: Board): Observable<Board> {
    return this.http.put<Board>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_BOARD.replace('{boardId}', boardId)}`, board);
  }

  deleteBoardAdmin(boardId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_BOARD.replace('{boardId}', boardId)}`);
  }

  getAllBoardsUser(): Observable<Board[]> {
    return this.http.get<Board[]>(`${this.configService.baseApiUrl}${BackendRoutes.USER_BOARDS}`);
  }

  getAllBoardsAdmin(): Observable<Board[]> {
    return this.http.get<Board[]>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_BOARDS}`);
  }

  getBoardById(boardId: string): Observable<Board> {
    return this.http.get<Board>(`${this.configService.baseApiUrl}${BackendRoutes.ADMIN_BOARD.replace('{boardId}', boardId)}`);
  }
}
