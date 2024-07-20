import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Card } from '../models/card.model';
import { ConfigService } from './config.service';
import { BackendRoutes } from '../app-main-rules/routes.enum';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  createCard(groupId: string, card: Card): Observable<Card> {
    return this.http.post<Card>(`${this.configService.baseApiUrl}${BackendRoutes.NEW_CARD.replace('{groupId}', groupId)}`, card);
  }

  updateCard(cardId: string, card: Card): Observable<Card> {
    return this.http.put<Card>(`${this.configService.baseApiUrl}${BackendRoutes.CARD.replace('{cardId}', cardId)}`, card);
  }

  deleteCard(cardId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}${BackendRoutes.CARD.replace('{cardId}', cardId)}`);
  }
}
