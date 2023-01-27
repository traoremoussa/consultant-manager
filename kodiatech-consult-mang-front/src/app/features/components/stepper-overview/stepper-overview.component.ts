import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-stepper-overview',
  templateUrl: './stepper-overview.component.html',
  styleUrls: ['./stepper-overview.component.scss'],
})
export class StepperOverviewComponent implements OnInit {
  isLinear = false;
  //@Input() si ja un entré de fils pere
  pre: string = 'précédent';
  suivant: string = 'suivant';
  demarer: string = 'démarer';


  constructor() {}

  ngOnInit(): void {}
}
