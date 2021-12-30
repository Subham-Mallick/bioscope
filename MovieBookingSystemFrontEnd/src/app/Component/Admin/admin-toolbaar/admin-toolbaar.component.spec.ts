import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminToolbaarComponent } from './admin-toolbaar.component';

describe('AdminToolbaarComponent', () => {
  let component: AdminToolbaarComponent;
  let fixture: ComponentFixture<AdminToolbaarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminToolbaarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminToolbaarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
