import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SoumissionDossierComponent } from './soumission-dossier.component';

describe('SoumissionDossierComponent', () => {
  let component: SoumissionDossierComponent;
  let fixture: ComponentFixture<SoumissionDossierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SoumissionDossierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SoumissionDossierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
