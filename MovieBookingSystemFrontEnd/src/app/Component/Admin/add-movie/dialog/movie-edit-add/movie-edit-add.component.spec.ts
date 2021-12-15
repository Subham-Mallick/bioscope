import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieEditAddComponent } from './movie-edit-add.component';

describe('MovieEditAddComponent', () => {
  let component: MovieEditAddComponent;
  let fixture: ComponentFixture<MovieEditAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieEditAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieEditAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
