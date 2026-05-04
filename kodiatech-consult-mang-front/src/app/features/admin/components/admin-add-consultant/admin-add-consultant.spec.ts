import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddConsultant } from './admin-add-consultant';

describe('AdminAddConsultant', () => {
  let component: AdminAddConsultant;
  let fixture: ComponentFixture<AdminAddConsultant>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminAddConsultant]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddConsultant);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
