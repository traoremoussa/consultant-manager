import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvFormFieldComponent } from './cv-form-field.component';

describe('CvFormFieldComponent', () => {
  let component: CvFormFieldComponent;
  let fixture: ComponentFixture<CvFormFieldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CvFormFieldComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CvFormFieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
