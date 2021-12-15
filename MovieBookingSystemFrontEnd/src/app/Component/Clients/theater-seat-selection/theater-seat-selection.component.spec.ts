import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TheaterSeatSelectionComponent } from './theater-seat-selection.component';

describe('TheaterSeatSelectionComponent', () => {
  let component: TheaterSeatSelectionComponent;
  let fixture: ComponentFixture<TheaterSeatSelectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TheaterSeatSelectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TheaterSeatSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
