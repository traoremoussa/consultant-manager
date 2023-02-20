import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjetPersonnelComponent } from './projet-personnel.component';

describe('ProjetPersonnelComponent', () => {
  let component: ProjetPersonnelComponent;
  let fixture: ComponentFixture<ProjetPersonnelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProjetPersonnelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProjetPersonnelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
