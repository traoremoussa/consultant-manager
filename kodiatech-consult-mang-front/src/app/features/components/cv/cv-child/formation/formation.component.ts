import { Component, OnInit } from '@angular/core';
import { InterfaceAction } from '../../model-cv/interface-action';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.scss']
})
export class FormationComponent implements OnInit,InterfaceAction {

  constructor() { }

  openModal(): void {
    throw new Error('Method not implemented.');
  }

  ngOnInit(): void {
  }

}
