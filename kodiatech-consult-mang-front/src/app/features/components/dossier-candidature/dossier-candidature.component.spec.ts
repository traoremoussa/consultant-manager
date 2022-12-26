import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DossierCandidatureComponent } from './dossier-candidature.component';

describe('DossierCandidatureComponent', () => {
  let component: DossierCandidatureComponent;
  let fixture: ComponentFixture<DossierCandidatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DossierCandidatureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DossierCandidatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
