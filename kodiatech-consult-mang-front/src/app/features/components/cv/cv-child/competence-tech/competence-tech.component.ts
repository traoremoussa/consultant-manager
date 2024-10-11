import { ModalFormComponent } from './../../../shared/modal-form/modal-form.component';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  action: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, action: 'H' },
  { position: 2, name: 'Helium', weight: 4.0026, action: 'He' },
  { position: 3, name: 'Lithium', weight: 6.941, action: 'Li' },
  { position: 4, name: 'Beryllium', weight: 9.0122, action: 'Be' },
];

const columns = [
  {
    columnDef: 'position',
    header: 'No.',
    cell: (element: any) => `${element.position}`,
  },
  {
    columnDef: 'name',
    header: 'Name',
    cell: (element: any) => `${element.name}`,
  },
  {
    columnDef: 'weight',
    header: 'Weight',
    cell: (element: any) => `${element.weight}`,
  },
  {
    columnDef: 'symbol',
    header: 'Symbol',
    cell: (element: any) => `${element.symbol}`,
  },
];

@Component({
  selector: 'app-competence-tech',
  templateUrl: './competence-tech.component.html',
  styleUrls: ['./competence-tech.component.scss'],
})
export class CompetenceTechComponent implements OnInit {
  entete: string[] = ['Connaissance technique', 'Description'];

  displayedColumns: string[] = ['position', 'name', 'weight', 'action'];

  dataSource = ELEMENT_DATA;
  columns: any = columns;

  messageTooltip: string = 'Ajouter une connaissance technique';
  titreTable: string = 'Connaissaces Techniques';

  constructor(private dialog: MatDialog, private snackBar: MatSnackBar) {}

  ngOnInit(): void {}

  //en fonction de parent n'est ce pas
  openModal(dataV?: any) {
    this.dialog
      .open(ModalFormComponent, {
        data: {
          title: 'modif',
          description: 'ici',
          competence: 'competence',
        },
      })
      .afterClosed()
      .subscribe((val) => {
        this.snackBar.open(
          `Closing snack bar in a few seconds ${dataV}`,
          'Valider',
          {
            duration: 2000,
          }
        );
      });
    //varible action
    //parent
    //child???
    //tu mets variable MODIF/AJOU
    const snack = this.snackBar.open('');
    console.warn('Modal class');
  }
}
