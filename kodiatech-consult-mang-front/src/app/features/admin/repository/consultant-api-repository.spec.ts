import { TestBed } from '@angular/core/testing';

import { ConsultantApiRepository } from './consultant-api-repository';

describe('ConsultantApiRepository', () => {
  let service: ConsultantApiRepository;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsultantApiRepository);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
