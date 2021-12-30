import { TestBed } from '@angular/core/testing';

import { ShowDetailsClientService } from './show-details-client.service';

describe('ShowDetailsClientService', () => {
  let service: ShowDetailsClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowDetailsClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
