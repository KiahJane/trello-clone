import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBoardsComponent } from './admin-boards.component';

describe('AdminBoardsComponent', () => {
  let component: AdminBoardsComponent;
  let fixture: ComponentFixture<AdminBoardsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminBoardsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminBoardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
