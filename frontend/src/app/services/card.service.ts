import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Card } from '../models/card.model';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  constructor(private http: HttpClient, private configService: ConfigService) {}

  createCard(groupId: string, card: Card): Observable<Card> {
    return this.http.post<Card>(`${this.configService.baseApiUrl}/groups/${groupId}/cards`, card);
  }

  editCard(cardId: string, card: Card): Observable<Card> {
    return this.http.put<Card>(`${this.configService.baseApiUrl}/cards/${cardId}`, card);
  }

  deleteCard(cardId: string): Observable<void> {
    return this.http.delete<void>(`${this.configService.baseApiUrl}/cards/${cardId}`);
  }
}
