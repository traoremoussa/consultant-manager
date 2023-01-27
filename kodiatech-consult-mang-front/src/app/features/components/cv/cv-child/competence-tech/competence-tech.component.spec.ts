import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetenceTechComponent } from './competence-tech.component';

describe('CompetenceComponent', () => {
  let component: CompetenceTechComponent;
  let fixture: ComponentFixture<CompetenceTechComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompetenceTechComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetenceTechComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
