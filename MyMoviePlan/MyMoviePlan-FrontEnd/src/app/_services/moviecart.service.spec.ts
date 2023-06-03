import { TestBed } from '@angular/core/testing';

import { MoviecartService } from './moviecart.service';

describe('MoviecartService', () => {
  let service: MoviecartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MoviecartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
