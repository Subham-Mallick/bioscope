import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowGuestListComponent } from './show-guest-list.component';

describe('ShowGuestListComponent', () => {
  let component: ShowGuestListComponent;
  let fixture: ComponentFixture<ShowGuestListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowGuestListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowGuestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
