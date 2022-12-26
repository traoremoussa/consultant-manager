import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-stepper-overview',
  templateUrl: './stepper-overview.component.html',
  styleUrls: ['./stepper-overview.component.scss']
})
export class StepperOverviewComponent implements OnInit {

  isLinear = false;

  constructor() { }

  ngOnInit(): void {
  }

}
