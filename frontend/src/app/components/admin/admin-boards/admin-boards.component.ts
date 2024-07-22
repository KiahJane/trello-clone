import { Component } from '@angular/core';
import { BoardService } from '../../../services/board.service';
import { Board } from '../../../models/board.model';

@Component({
  selector: 'app-admin-boards',
  templateUrl: './admin-boards.component.html',
  styleUrls: ['./admin-boards.component.css']
})
export class AdminBoardsComponent {
  boards!: Board[];

  constructor(private boardService: BoardService) {}

  ngOnInit() {
    this.boardService.getAllBoardsUser().subscribe(response => {
      this.boards = response;
    });
  }
}
