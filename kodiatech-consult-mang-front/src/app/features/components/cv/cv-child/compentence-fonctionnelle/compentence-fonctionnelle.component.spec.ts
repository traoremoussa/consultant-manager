import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompentenceFonctionnelleComponent } from './compentence-fonctionnelle.component';

describe('CompentenceFonctionnelleComponent', () => {
  let component: CompentenceFonctionnelleComponent;
  let fixture: ComponentFixture<CompentenceFonctionnelleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompentenceFonctionnelleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompentenceFonctionnelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
