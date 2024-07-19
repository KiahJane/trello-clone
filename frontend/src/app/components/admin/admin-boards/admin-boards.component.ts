import { Component } from '@angular/core';
import { BoardService } from '../../../services/board.service';
import { BoardResponse } from '../../../models/board-response.model';

@Component({
  selector: 'app-admin-boards',
  templateUrl: './admin-boards.component.html',
  styleUrls: ['./admin-boards.component.css']
})
export class AdminBoardsComponent {
  boards!: BoardResponse;

  constructor(private boardService: BoardService) {}

  ngOnInit() {
    this.boardService.getAllBoards().subscribe(response => {
      this.boards = response;
    });
  }
}
