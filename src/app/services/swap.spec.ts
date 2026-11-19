import { TestBed } from '@angular/core/testing';
import { Swap } from './swap.service';

describe('Swap', () => {
  let service: Swap;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Swap);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
