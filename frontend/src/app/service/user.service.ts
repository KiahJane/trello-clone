import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {CardDto, GroupDto, UserDto} from "../data/dataTransferObjects";


@Injectable({
  providedIn: 'root'
})
export class UserService {
    user: string = "/api/user/information";
    board: string = "/api/user/board";
    boards: string = "/api/user/boards";
    // TODO: add the remaining from AvailablePaths on BE

    constructor(private http: HttpClient) {

    }

    getMyInformation(): Observable<UserDto> {
    return this.http.get<UserDto>(this.user, {
        headers: {'Content-Type': 'application/json'}
    });
    }
    
    createGroup(group: GroupDto) : Observable<any> {
        return this.http.post<any>(this.group, group, {
          headers: {'Content-Type': 'application/json'}
        });
    }

    updateGroup(group: GroupDto) : Observable<any> {
        let id = group.id;
        return this.http.put<any>(this.group + '/' + id, group, {
          headers: {'Content-Type': 'application/json'}
        });
    }
    
    deleteGroup(group: GroupDto) : Observable<any> {
        let id = group.id;
        return this.http.delete<any>(this.group + '/' + id, {
            headers:  {'Content-Type': 'application/json'}
        });
    }
    
    // TODO: add parameter groupId to method (BE / FE)
    createCard(card: CardDto) : Observable<any> {
        return this.http.post<any>(this.card, card, {
          headers: {'Content-Type': 'application/json'}
        });
    }

    // TODO: add parameter groupId to method (BE / FE)
    updateCard(card: CardDto) : Observable<any> {
        let id = card.id;
        return this.http.put<any>(this.card + '/' + id, card, {
          headers: {'Content-Type': 'application/json'}
        });
    }
    
    deleteCard(card: CardDto) : Observable<any> {
        let id = card.id;
        return this.http.delete<any>(this.card + '/' + id, {
            headers:  {'Content-Type': 'application/json'}
        });
    }
}