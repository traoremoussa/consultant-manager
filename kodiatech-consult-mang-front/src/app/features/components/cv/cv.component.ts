import { ConsultantService } from './../../services/consultant.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';




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
  selector: 'app-cv',
  templateUrl: './cv.component.html',
  styleUrls: ['./cv.component.scss']
})
export class CvComponent implements OnInit {


  displayedColumns: string[] = ['position', 'name', 'weight', 'action'];
  dataSource = ELEMENT_DATA;


  //Forme groupe
  titreFonctionFormGroup!: FormGroup;



  constructor(private service: ConsultantService,private formbuilder:FormBuilder) { }


  ngOnInit() {
   this.titreFonctionFormGroup= this.formbuilder.group({
      titreFonction:new FormControl('')
    })

  }

  onClear() {
   // this.service.form.reset();
   // this.service.initializeFormGroup();
  }

}
