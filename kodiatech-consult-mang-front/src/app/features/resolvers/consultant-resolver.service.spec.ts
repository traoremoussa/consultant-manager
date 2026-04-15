import { TestBed } from '@angular/core/testing';

import { ConsultantResolverService } from './consultant-resolver.service';

describe('ConsultantResolverService', () => {
  let service: ConsultantResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsultantResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
