import { Component, OnInit } from '@angular/core';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  action: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, action: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, action: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, action: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, action: 'Be'},

];


@Component({
  selector: 'app-competence-tech',
  templateUrl: './competence-tech.component.html',
  styleUrls: ['./competence-tech.component.scss']
})
export class CompetenceTechComponent implements OnInit {
  entete:string[]=['Connaissance technique','Description'];
  displayedColumns: string[] = ['position', 'name', 'weight', 'action'];
  dataSource = ELEMENT_DATA;

  constructor() { }

  ngOnInit(): void {
  }

}
