import { TestBed } from '@angular/core/testing';

import { CreateConsultantUseCase } from './create-consultant-use-case';

describe('CreateConsultantUseCase', () => {
  let service: CreateConsultantUseCase;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateConsultantUseCase);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
