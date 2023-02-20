import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ModalFormComponent } from '../../../shared/modal-form/modal-form.component';











export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  action: string;
}

const ELEMENT_DATA: PeriodicElement[] = [

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
  selector: 'app-langue',
  templateUrl: './langue.component.html',
  styleUrls: ['./langue.component.scss']
})
export class LangueComponent implements OnInit {


  displayedColumns: string[] = ['position', 'name', 'weight', 'action'];

  dataSource = ELEMENT_DATA;
  columns: any = columns;



  messageTooltip:string="Ajouter une langues";
  titreTable="Langues"

  constructor(  private dialog: MatDialog, private snackBar: MatSnackBar ) {}

  ngOnInit(): void {}



  //en fonction de parent n'est ce pas
  openModal(dataV?:any) {
    this.dialog
      .open(ModalFormComponent, {
        data:{
          title:'modif',
          description:'ici',
          competence:'competence'

        },
      }).afterClosed().subscribe((val)=>{

        this.snackBar.open(`Closing snack bar in a few seconds ${dataV}`, 'Valider', {
          duration: 2000,
        });
      });
    //varible action
    //parent
    //child???
    //tu mets variable MODIF/AJOU
    const snack = this.snackBar.open('Ouverture ');
    console.warn('Modal class');
  }

}
