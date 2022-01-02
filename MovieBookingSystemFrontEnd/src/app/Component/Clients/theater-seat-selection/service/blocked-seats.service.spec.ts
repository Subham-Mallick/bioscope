import { TestBed } from '@angular/core/testing';

import { BlockedSeatsService } from './blocked-seats.service';

describe('BlockedSeatsService', () => {
  let service: BlockedSeatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlockedSeatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
